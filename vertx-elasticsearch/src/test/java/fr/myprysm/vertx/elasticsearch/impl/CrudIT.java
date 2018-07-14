/*
 * Copyright 2018 the original author or the original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.myprysm.vertx.elasticsearch.impl;

import fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexRequest;
import fr.myprysm.vertx.elasticsearch.action.bulk.BulkRequest;
import fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponse;
import fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponseItem;
import fr.myprysm.vertx.elasticsearch.action.delete.DeleteRequest;
import fr.myprysm.vertx.elasticsearch.action.get.GetRequest;
import fr.myprysm.vertx.elasticsearch.action.get.GetRequestItem;
import fr.myprysm.vertx.elasticsearch.action.get.GetResponse;
import fr.myprysm.vertx.elasticsearch.action.get.MultiGetRequest;
import fr.myprysm.vertx.elasticsearch.action.index.IndexRequest;
import fr.myprysm.vertx.elasticsearch.action.index.IndexResponse;
import fr.myprysm.vertx.elasticsearch.action.support.FetchSourceContext;
import fr.myprysm.vertx.elasticsearch.action.support.Script;
import fr.myprysm.vertx.elasticsearch.action.update.UpdateRequest;
import io.vertx.core.json.JsonObject;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.common.Strings;
import org.elasticsearch.index.VersionType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.script.ScriptType;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy.WAIT_UNTIL;
import static org.elasticsearch.rest.RestStatus.CREATED;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("Duplicates")
abstract class CrudIT extends VertxESRestTestCase {

    @Test
    void testDelete() throws InterruptedException {
        {
            // Testing deletion
            String docId = "id";
            rxClient().rxIndex(new IndexRequest("index", "type", docId).setSource(new JsonObject().put("foo", "bar")))
                    .test()
                    .await()
                    .assertNoErrors();
            DeleteRequest deleteRequest = new DeleteRequest("index", "type", docId);
            rxClient().rxDelete(deleteRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(deleteResponse -> {
                        assertEquals("index", deleteResponse.getIndex());
                        assertEquals("type", deleteResponse.getType());
                        assertEquals(docId, deleteResponse.getId());
                        assertEquals(DocWriteResponse.Result.DELETED, deleteResponse.getResult());
                        return true;
                    });

        }
        {
            // Testing non existing document
            String docId = "does_not_exist";
            DeleteRequest deleteRequest = new DeleteRequest("index", "type", docId);
            rxClient().rxDelete(deleteRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(deleteResponse -> {
                        assertEquals("index", deleteResponse.getIndex());
                        assertEquals("type", deleteResponse.getType());
                        assertEquals(docId, deleteResponse.getId());
                        assertEquals(DocWriteResponse.Result.NOT_FOUND, deleteResponse.getResult());
                        return true;
                    });

        }
        {
            // Testing version conflict
            String docId = "version_conflict";
            rxClient().rxIndex(new IndexRequest("index", "type", docId).setSource(new JsonObject().put("foo", "bar")))
                    .test()
                    .await()
                    .assertNoErrors();

            DeleteRequest deleteRequest = new DeleteRequest("index", "type", docId).setVersion(2);
            rxClient().rxDelete(deleteRequest)
                    .test()
                    .await()
                    .assertError(error -> {
                        assertThat(error).isInstanceOf(ElasticsearchException.class);
                        ElasticsearchException exception = (ElasticsearchException) error;

                        assertEquals(RestStatus.CONFLICT, exception.status());
                        assertEquals("Elasticsearch exception [type=version_conflict_engine_exception, reason=[type][" + docId + "]: " +
                                "version conflict, current version [1] is different than the one provided [2]]", exception.getMessage());
                        assertEquals("index", exception.getMetadata("es.index").get(0));
                        return true;
                    });

        }
        {
            // Testing version type
            String docId = "version_type";
            IndexRequest indexRequest = new IndexRequest("index", "type", docId)
                    .setSource(new JsonObject().put("foo", "bar"))
                    .setVersionType(VersionType.EXTERNAL)
                    .setVersion(12);

            rxClient().rxIndex(indexRequest).test().await().assertNoErrors();
            DeleteRequest deleteRequest = new DeleteRequest("index", "type", docId).setVersionType(VersionType.EXTERNAL).setVersion(13);
            rxClient().rxDelete(deleteRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(deleteResponse -> {
                        assertEquals("index", deleteResponse.getIndex());
                        assertEquals("type", deleteResponse.getType());
                        assertEquals(docId, deleteResponse.getId());
                        assertEquals(DocWriteResponse.Result.DELETED, deleteResponse.getResult());
                        return true;
                    });

        }
        {
            // Testing version type with a wrong version
            String docId = "wrong_version";
            IndexRequest indexRequest = new IndexRequest("index", "type", docId)
                    .setSource(new JsonObject().put("foo", "bar"))
                    .setVersionType(VersionType.EXTERNAL)
                    .setVersion(12);
            rxClient().rxIndex(indexRequest)
                    .test()
                    .await()
                    .assertNoErrors();

            DeleteRequest deleteRequest = new DeleteRequest("index", "type", docId)
                    .setVersionType(VersionType.EXTERNAL)
                    .setVersion(10);

            rxClient().rxDelete(deleteRequest)
                    .test()
                    .await()
                    .assertError(error -> {
                        assertThat(error).isInstanceOf(ElasticsearchException.class);
                        ElasticsearchException exception = (ElasticsearchException) error;

                        assertEquals(RestStatus.CONFLICT, exception.status());
                        assertEquals("Elasticsearch exception [type=version_conflict_engine_exception, reason=[type][" +
                                docId + "]: version conflict, current version [12] is higher or equal to the one provided [10]]", exception.getMessage());
                        assertEquals("index", exception.getMetadata("es.index").get(0));
                        return true;
                    });
        }
        {
            // Testing routing
            String docId = "routing";
            IndexRequest indexRequest = new IndexRequest("index", "type", docId)
                    .setSource(new JsonObject().put("foo", "bar"))
                    .setRouting("foo");
            rxClient().rxIndex(indexRequest)
                    .test()
                    .await()
                    .assertNoErrors();

            DeleteRequest deleteRequest = new DeleteRequest("index", "type", docId).setRouting("foo");
            rxClient().rxDelete(deleteRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(deleteResponse -> {
                        assertEquals("index", deleteResponse.getIndex());
                        assertEquals("type", deleteResponse.getType());
                        assertEquals(docId, deleteResponse.getId());
                        assertEquals(DocWriteResponse.Result.DELETED, deleteResponse.getResult());
                        return true;
                    });

        }
    }

    @Test
    void testExists() throws InterruptedException, IOException {
        CreateIndexResponse create = esClient().indices().create(new CreateIndexRequest("index"));
        assertThat(create.isAcknowledged()).isTrue();
        {
            rxClient().rxGet(new GetRequest("index", "type", "id"))
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(getResponse -> {
                        assertFalse(getResponse.isExists());
                        return true;
                    });
        }
        String document = "{\"field1\":\"value1\",\"field2\":\"value2\"}";
        rxClient().rxIndex(new IndexRequest("index", "type", "id").setSource(new JsonObject(document)).setRefreshPolicy(WAIT_UNTIL))
                .test()
                .await()
                .assertValue(indexResponse -> {
                    assertEquals(DocWriteResponse.Result.CREATED, indexResponse.getResult());
                    return true;
                });

        {
            rxClient().rxGet(new GetRequest("index", "type", "id"))
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(getResponse -> {
                        assertTrue(getResponse.isExists());
                        return true;
                    });
        }
        {

            rxClient().rxGet(new GetRequest("index", "type", "does_not_exist"))
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(getResponse -> {
                        assertFalse(getResponse.isExists());
                        return true;
                    });
        }
        {

            rxClient().rxGet(new GetRequest("index", "type", "does_not_exist").setVersion(1))
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(getResponse -> {
                        assertFalse(getResponse.isExists());
                        return true;
                    });
        }
    }

    @Test
    void testGet() throws InterruptedException {
        {
            GetRequest getRequest = new GetRequest("index", "type", "id");
            rxClient().rxGet(getRequest)
                    .test()
                    .await()
                    .assertError(error -> {
                        assertThat(error).isInstanceOf(ElasticsearchException.class);
                        ElasticsearchException exception = (ElasticsearchException) error;

                        assertThat(exception.status()).isEqualTo(RestStatus.NOT_FOUND);
                        assertThat(exception.getMessage()).isEqualTo("Elasticsearch exception [type=index_not_found_exception, reason=no such index]");
                        assertThat(exception.getMetadata("es.index").get(0)).isEqualTo("index");
                        return true;
                    });

        }

        String document = "{\"field1\":\"value1\",\"field2\":\"value2\"}";
        rxClient().rxIndex(new IndexRequest("index", "type", "id").setSource(new JsonObject(document)).setRefreshPolicy(WAIT_UNTIL))
                .test()
                .await()
                .assertValue(indexResponse -> {
                    assertThat(indexResponse.getResult()).isEqualTo(DocWriteResponse.Result.CREATED);
                    return true;
                });
        {
            GetRequest getRequest = new GetRequest("index", "type", "id").setVersion(2);
            rxClient().rxGet(getRequest)
                    .test()
                    .await()
                    .assertError(error -> {
                        assertThat(error).isInstanceOf(ElasticsearchException.class);
                        ElasticsearchException exception = (ElasticsearchException) error;

                        assertEquals(RestStatus.CONFLICT, exception.status());
                        assertEquals("Elasticsearch exception [type=version_conflict_engine_exception, " + "reason=[type][id]: " +
                                "version conflict, current version [1] is different than the one provided [2]]", exception.getMessage());
                        assertEquals("index", exception.getMetadata("es.index").get(0));
                        return true;
                    });

        }
        {
            GetRequest getRequest = new GetRequest("index", "type", "id");
            rxClient().rxGet(getRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(getResponse -> {
                        assertEquals("index", getResponse.getIndex());
                        assertEquals("type", getResponse.getType());
                        assertEquals("id", getResponse.getId());
                        assertTrue(getResponse.isExists());
                        assertNotNull(getResponse.getSource());
                        assertEquals(1L, getResponse.getVersion());
                        assertEquals(document, getResponse.getSource().toString());
                        return true;
                    });

        }
        {
            GetRequest getRequest = new GetRequest("index", "type", "does_not_exist");
            rxClient().rxGet(getRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(getResponse -> {
                        assertEquals("index", getResponse.getIndex());
                        assertEquals("type", getResponse.getType());
                        assertEquals("does_not_exist", getResponse.getId());
                        assertFalse(getResponse.isExists());
                        assertEquals(-1, getResponse.getVersion());
                        assertNull(getResponse.getSource());
                        return true;
                    });
        }
        {
            GetRequest getRequest = new GetRequest("index", "type", "id");
            getRequest.setFetchSourceContext(new FetchSourceContext(false, Collections.emptyList(), Collections.emptyList()));
            rxClient().rxGet(getRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(getResponse -> {
                        assertEquals("index", getResponse.getIndex());
                        assertEquals("type", getResponse.getType());
                        assertEquals("id", getResponse.getId());
                        assertTrue(getResponse.isExists());
                        assertEquals(1L, getResponse.getVersion());
                        assertNull(getResponse.getSource());
                        return true;
                    });

        }
        {
            GetRequest getRequest = new GetRequest("index", "type", "id");
            getRequest.setFetchSourceContext(new FetchSourceContext(true, singletonList("field1"), Collections.emptyList()));
            rxClient().rxGet(getRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(getResponse -> {
                        assertEquals("index", getResponse.getIndex());
                        assertEquals("type", getResponse.getType());
                        assertEquals("id", getResponse.getId());
                        assertTrue(getResponse.isExists());
                        assertNotNull(getResponse.getSource());
                        assertEquals(1L, getResponse.getVersion());
                        JsonObject source = getResponse.getSource();
                        assertEquals(1, source.size());
                        assertEquals("value1", source.getString("field1"));
                        return true;
                    });
        }
        {
            GetRequest getRequest = new GetRequest("index", "type", "id");
            getRequest.setFetchSourceContext(new FetchSourceContext(true, Collections.emptyList(), singletonList("field2")));
            rxClient().rxGet(getRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(getResponse -> {
                        assertEquals("index", getResponse.getIndex());
                        assertEquals("type", getResponse.getType());
                        assertEquals("id", getResponse.getId());
                        assertTrue(getResponse.isExists());
                        assertNotNull(getResponse.getSource());
                        assertEquals(1L, getResponse.getVersion());
                        JsonObject source = getResponse.getSource();
                        assertEquals(1, source.size());
                        assertEquals("value1", source.getString("field1"));
                        return true;
                    });
        }
    }

    @Test
    void testMultiGet() throws InterruptedException {
        {
            MultiGetRequest multiGetRequest = new MultiGetRequest();
            multiGetRequest.addItem(new GetRequestItem("index", "type", "id1"));
            multiGetRequest.addItem(new GetRequestItem("index", "type", "id2"));
            rxClient().rxMultiGet(multiGetRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(response -> {
                        assertEquals(2, response.getFailures().size());
                        assertEquals(0, response.getResponses().size());

                        assertNotNull(response.getFailures().get(0));
                        assertEquals("id1", response.getFailures().get(0).getId());
                        assertEquals("type", response.getFailures().get(0).getType());
                        assertEquals("index", response.getFailures().get(0).getIndex());
                        assertEquals("Elasticsearch exception [type=index_not_found_exception, reason=no such index]",
                                response.getFailures().get(0).getCause());

                        assertNotNull(response.getFailures().get(1));
                        assertEquals("id2", response.getFailures().get(1).getId());
                        assertEquals("type", response.getFailures().get(1).getType());
                        assertEquals("index", response.getFailures().get(1).getIndex());
                        assertEquals("Elasticsearch exception [type=index_not_found_exception, reason=no such index]",
                                response.getFailures().get(1).getCause());
                        return true;
                    });
        }

        String document = "{\"field\":\"value1\"}";
        rxClient().rxIndex(new IndexRequest("index", "type", "id1").setSource(new JsonObject(document)).setRefreshPolicy(WAIT_UNTIL))
                .test()
                .await()
                .assertValue(indexResponse -> {
                    assertThat(indexResponse.getResult()).isEqualTo(DocWriteResponse.Result.CREATED);
                    return true;
                });

        document = "{\"field\":\"value2\"}";
        rxClient().rxIndex(new IndexRequest("index", "type", "id2").setSource(new JsonObject(document)).setRefreshPolicy(WAIT_UNTIL))
                .test()
                .await()
                .assertValue(indexResponse -> {
                    assertThat(indexResponse.getResult()).isEqualTo(DocWriteResponse.Result.CREATED);
                    return true;
                });

        {
            MultiGetRequest multiGetRequest = new MultiGetRequest();
            multiGetRequest.addItem(new GetRequestItem("index", "type", "id1"));
            multiGetRequest.addItem(new GetRequestItem("index", "type", "id2"));
            rxClient().rxMultiGet(multiGetRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(response -> {
                        assertEquals(2, response.getResponses().size());

                        assertNotNull(response.getResponses().get(0));
                        assertEquals("id1", response.getResponses().get(0).getId());
                        assertEquals("type", response.getResponses().get(0).getType());
                        assertEquals("index", response.getResponses().get(0).getIndex());
                        assertEquals(new JsonObject().put("field", "value1"), response.getResponses().get(0).getSource());

                        assertNotNull(response.getResponses().get(1));
                        assertEquals("id2", response.getResponses().get(1).getId());
                        assertEquals("type", response.getResponses().get(1).getType());
                        assertEquals("index", response.getResponses().get(1).getIndex());
                        assertEquals(new JsonObject().put("field", "value2"), response.getResponses().get(1).getSource());
                        return true;
                    });
        }
    }

    @Test
    void testIndex() throws InterruptedException {
        {
            IndexRequest indexRequest = new IndexRequest("index", "type");
            indexRequest.setSource(new JsonObject().put("test", "test"));

            rxClient().rxIndex(indexRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(indexResponse -> {
                        assertEquals(CREATED, indexResponse.getStatus());
                        assertEquals(DocWriteResponse.Result.CREATED, indexResponse.getResult());
                        assertEquals("index", indexResponse.getIndex());
                        assertEquals("type", indexResponse.getType());
                        assertTrue(Strings.hasLength(indexResponse.getId()));
                        assertEquals(1L, indexResponse.getVersion());
                        assertNotNull(indexResponse.getShardId());
                        assertEquals(-1, indexResponse.getShardId().getId());
                        assertEquals("index", indexResponse.getShardId().getIndex());
                        assertNotNull(indexResponse.getShardInfo());
                        assertEquals(0, indexResponse.getShardInfo().getFailed());
                        assertTrue(indexResponse.getShardInfo().getSuccessful() > 0);
                        assertTrue(indexResponse.getShardInfo().getTotal() > 0);
                        return true;
                    });

        }
        {
            IndexRequest indexRequest = new IndexRequest("index", "type", "id");
            indexRequest.setSource(new JsonObject().put("version", 1));

            rxClient().rxIndex(indexRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(indexResponse -> {
                        assertEquals(CREATED, indexResponse.getStatus());
                        assertEquals("index", indexResponse.getIndex());
                        assertEquals("type", indexResponse.getType());
                        assertEquals("id", indexResponse.getId());
                        assertEquals(1L, indexResponse.getVersion());
                        return true;
                    });


            indexRequest = new IndexRequest("index", "type", "id");
            indexRequest.setSource(new JsonObject().put("version", 2));

            rxClient().rxIndex(indexRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(indexResponse -> {
                        assertEquals(RestStatus.OK, indexResponse.getStatus());
                        assertEquals("index", indexResponse.getIndex());
                        assertEquals("type", indexResponse.getType());
                        assertEquals("id", indexResponse.getId());
                        assertEquals(2L, indexResponse.getVersion());
                        return true;
                    });

            IndexRequest wrongRequest = new IndexRequest("index", "type", "id");
            wrongRequest.setSource(new JsonObject().put("field", "test"));
            wrongRequest.setVersion(5L);
            rxClient().rxIndex(wrongRequest)
                    .test()
                    .await()
                    .assertError(error -> {
                        assertThat(error).isInstanceOf(ElasticsearchException.class);
                        ElasticsearchException exception = (ElasticsearchException) error;

                        assertEquals(RestStatus.CONFLICT, exception.status());
                        assertEquals("Elasticsearch exception [type=version_conflict_engine_exception, reason=[type][id]: " +
                                "version conflict, current version [2] is different than the one provided [5]]", exception.getMessage());
                        assertEquals("index", exception.getMetadata("es.index").get(0));
                        return true;
                    });
        }
        {
            IndexRequest indexRequest = new IndexRequest("index", "type", "missing_parent");
            indexRequest.setSource(new JsonObject().put("field", "test"));
            indexRequest.setParent("missing");
            rxClient().rxIndex(indexRequest)
                    .test()
                    .await()
                    .assertError(error -> {
                        assertThat(error).isInstanceOf(ElasticsearchException.class);
                        ElasticsearchException exception = (ElasticsearchException) error;

                        assertEquals(RestStatus.BAD_REQUEST, exception.status());
                        assertEquals("Elasticsearch exception [type=illegal_argument_exception, " +
                                "reason=can't specify parent if no parent field has been configured]", exception.getMessage());
                        return true;
                    });


        }
        {
            IndexRequest indexRequest = new IndexRequest("index", "type", "missing_pipeline");
            indexRequest.setSource(new JsonObject().put("field", "test"));
            indexRequest.setPipeline("missing");

            rxClient().rxIndex(indexRequest)
                    .test()
                    .await()
                    .assertError(error -> {
                        assertThat(error).isInstanceOf(ElasticsearchException.class);
                        ElasticsearchException exception = (ElasticsearchException) error;

                        assertEquals(RestStatus.BAD_REQUEST, exception.status());
                        assertEquals("Elasticsearch exception [type=illegal_argument_exception, " +
                                "reason=pipeline with id [missing] does not exist]", exception.getMessage());
                        return true;
                    });


        }
        {
            IndexRequest indexRequest = new IndexRequest("index", "type", "external_version_type");
            indexRequest.setSource(new JsonObject().put("field", "test"));
            indexRequest.setVersion(12L);
            indexRequest.setVersionType(VersionType.EXTERNAL);

            rxClient().rxIndex(indexRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(indexResponse -> {
                        assertEquals(CREATED, indexResponse.getStatus());
                        assertEquals("index", indexResponse.getIndex());
                        assertEquals("type", indexResponse.getType());
                        assertEquals("external_version_type", indexResponse.getId());
                        assertEquals(12L, indexResponse.getVersion());
                        return true;
                    });

        }
        {
            final IndexRequest indexRequest = new IndexRequest("index", "type", "with_create_op_type");
            indexRequest.setSource(new JsonObject().put("field", "test"));
            indexRequest.setOpType(DocWriteRequest.OpType.CREATE);

            rxClient().rxIndex(indexRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(indexResponse -> {
                        assertEquals(CREATED, indexResponse.getStatus());
                        assertEquals("index", indexResponse.getIndex());
                        assertEquals("type", indexResponse.getType());
                        assertEquals("with_create_op_type", indexResponse.getId());
                        return true;
                    });
            rxClient().rxIndex(indexRequest)
                    .test()
                    .await()
                    .assertError(error -> {
                        assertThat(error).isInstanceOf(ElasticsearchException.class);
                        ElasticsearchException exception = (ElasticsearchException) error;

                        assertEquals(RestStatus.CONFLICT, exception.status());
                        assertEquals("Elasticsearch exception [type=version_conflict_engine_exception, reason=[type][with_create_op_type]: " +
                                "version conflict, document already exists (current version [1])]", exception.getMessage());
                        return true;
                    });


        }
    }

    @Test
    void testUpdate() throws InterruptedException {
        {
            UpdateRequest updateRequest = new UpdateRequest("index", "type", "does_not_exist");
            updateRequest.setDoc(new JsonObject().put("field", "value"));

            rxClient().rxUpdate(updateRequest)
                    .test()
                    .await()
                    .assertError(error -> {
                        assertThat(error).isInstanceOf(ElasticsearchException.class);
                        ElasticsearchException exception = (ElasticsearchException) error;

                        assertEquals(RestStatus.NOT_FOUND, exception.status());
                        assertEquals("Elasticsearch exception [type=document_missing_exception, reason=[type][does_not_exist]: document missing]",
                                exception.getMessage());
                        return true;
                    });
        }
        {
            IndexRequest indexRequest = new IndexRequest("index", "type", "id");
            indexRequest.setSource(new JsonObject().put("field", "value"));
            AtomicReference<IndexResponse> indexResponse = new AtomicReference<>();
            rxClient().rxIndex(indexRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(indexResponseAsync -> {
                        indexResponse.set(indexResponseAsync);
                        assertEquals(CREATED, indexResponseAsync.getStatus());
                        return true;
                    });


            UpdateRequest updateRequest = new UpdateRequest("index", "type", "id");
            updateRequest.setDoc(new JsonObject().put("field", "updated"));

            rxClient().rxUpdate(updateRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(updateResponse -> {
                        assertEquals(RestStatus.OK, updateResponse.getStatus());
                        assertEquals(indexResponse.get().getVersion() + 1, updateResponse.getVersion());
                        return true;
                    });


            UpdateRequest updateRequestConflict = new UpdateRequest("index", "type", "id");
            updateRequestConflict.setDoc(new JsonObject().put("field", "with_version_conflict"));
            updateRequestConflict.setVersion(indexResponse.get().getVersion());

            rxClient().rxUpdate(updateRequestConflict)
                    .test()
                    .await()
                    .assertError(error -> {
                        assertThat(error).isInstanceOf(ElasticsearchException.class);
                        ElasticsearchException exception = (ElasticsearchException) error;

                        assertEquals(RestStatus.CONFLICT, exception.status());
                        assertEquals("Elasticsearch exception [type=version_conflict_engine_exception, reason=[type][id]: version conflict, " +
                                "current version [2] is different than the one provided [1]]", exception.getMessage());
                        return true;
                    });

        }
        {
            UpdateRequest updateRequest = new UpdateRequest("index", "type", "id");
            updateRequest.setDoc(new JsonObject().put("field", "updated"));
            updateRequest.setParent("missing");

            rxClient().rxUpdate(updateRequest)
                    .test()
                    .await()
                    .assertError(error -> {
                        assertThat(error).isInstanceOf(ElasticsearchException.class);
                        ElasticsearchException exception = (ElasticsearchException) error;

                        assertEquals(RestStatus.NOT_FOUND, exception.status());
                        assertEquals("Elasticsearch exception [type=document_missing_exception, reason=[type][id]: document missing]",
                                exception.getMessage());
                        return true;
                    });
        }
        {
            UpdateRequest updateRequest = new UpdateRequest("index", "type", "id");
            updateRequest.setDoc(new JsonObject().put("field", "updated"));
            updateRequest.setRouting("missing");

            rxClient().rxUpdate(updateRequest)
                    .test()
                    .await()
                    .assertError(error -> {
                        assertThat(error).isInstanceOf(ElasticsearchException.class);
                        ElasticsearchException exception = (ElasticsearchException) error;

                        assertEquals(RestStatus.NOT_FOUND, exception.status());
                        assertEquals("Elasticsearch exception [type=document_missing_exception, reason=[type][id]: document missing]",
                                exception.getMessage());
                        return true;
                    });
        }
        {
            IndexRequest indexRequest = new IndexRequest("index", "type", "with_script");
            indexRequest.setSource(new JsonObject().put("counter", 12));
            rxClient().rxIndex(indexRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(indexResponse -> {
                        assertEquals(CREATED, indexResponse.getStatus());
                        return true;
                    });


            UpdateRequest updateRequest = new UpdateRequest("index", "type", "with_script");
            Script script = new Script(ScriptType.INLINE, "ctx._source.counter += params.count", "painless", null, new JsonObject().put("count", 8));
            updateRequest.setScript(script);
            updateRequest.setFetchSourceContext(new FetchSourceContext().setFetchSource(true));

            rxClient().rxUpdate(updateRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(updateResponse -> {
                        assertEquals(RestStatus.OK, updateResponse.getStatus());
                        assertEquals(DocWriteResponse.Result.UPDATED, updateResponse.getResult());
                        assertEquals(2L, updateResponse.getVersion());
                        assertEquals(20, updateResponse.getGetResult().getSource().getInteger("counter").intValue());
                        return true;
                    });


        }
        {
            IndexRequest indexRequest = new IndexRequest("index", "type", "with_doc");
            indexRequest.setSource(new JsonObject().put("field_1", "one").put("field_3", "three"));
            indexRequest.setVersion(12L);
            indexRequest.setVersionType(VersionType.EXTERNAL);
            rxClient().rxIndex(indexRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(indexResponse -> {
                        assertEquals(CREATED, indexResponse.getStatus());
                        assertEquals(12L, indexResponse.getVersion());
                        return true;
                    });


            UpdateRequest updateRequest = new UpdateRequest("index", "type", "with_doc");
            updateRequest.setDoc(new JsonObject().put("field_2", "two"));
            updateRequest.setFetchSourceContext(new FetchSourceContext(true, singletonList("field_*"), singletonList("field_3")));

            rxClient().rxUpdate(updateRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(updateResponse -> {
                        assertEquals(RestStatus.OK, updateResponse.getStatus());
                        assertEquals(DocWriteResponse.Result.UPDATED, updateResponse.getResult());
                        assertEquals(13L, updateResponse.getVersion());
                        assertEquals(13L, updateResponse.getVersion());
                        JsonObject sourceAsMap = updateResponse.getGetResult().getSource();
                        assertEquals("one", sourceAsMap.getString("field_1"));
                        assertEquals("two", sourceAsMap.getString("field_2"));
                        assertFalse(sourceAsMap.containsKey("field_3"));
                        return true;
                    });

        }
        {
            IndexRequest indexRequest = new IndexRequest("index", "type", "noop");
            indexRequest.setSource(new JsonObject().put("field", "value"));
            rxClient().rxIndex(indexRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(indexResponse -> {
                        assertEquals(CREATED, indexResponse.getStatus());
                        assertEquals(1L, indexResponse.getVersion());
                        return true;
                    });


            UpdateRequest updateRequest = new UpdateRequest("index", "type", "noop");
            updateRequest.setDoc(new JsonObject().put("field", "value"));

            rxClient().rxUpdate(updateRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(updateResponse -> {
                        assertEquals(RestStatus.OK, updateResponse.getStatus());
                        assertEquals(DocWriteResponse.Result.NOOP, updateResponse.getResult());
                        assertEquals(1L, updateResponse.getVersion());
                        return true;
                    });


            updateRequest.setDetectNoop(false);

            rxClient().rxUpdate(updateRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(updateResponse -> {
                        assertEquals(RestStatus.OK, updateResponse.getStatus());
                        assertEquals(DocWriteResponse.Result.UPDATED, updateResponse.getResult());
                        assertEquals(2L, updateResponse.getVersion());
                        return true;
                    });

        }
        {
            UpdateRequest updateRequest = new UpdateRequest("index", "type", "with_upsert");
            updateRequest.setUpsert(new JsonObject().put("doc_status", "created"));
            updateRequest.setDoc(new JsonObject().put("doc_status", "updated"));
            updateRequest.setFetchSourceContext(new FetchSourceContext().setFetchSource(true));

            rxClient().rxUpdate(updateRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(updateResponse -> {
                        assertEquals(CREATED, updateResponse.getStatus());
                        assertEquals("index", updateResponse.getIndex());
                        assertEquals("type", updateResponse.getType());
                        assertEquals("with_upsert", updateResponse.getId());
                        GetResponse getResult = updateResponse.getGetResult();
                        assertEquals(1L, updateResponse.getVersion());
                        assertEquals("created", getResult.getSource().getString("doc_status"));
                        return true;
                    });

        }
        {
            UpdateRequest updateRequest = new UpdateRequest("index", "type", "with_doc_as_upsert");
            updateRequest.setDoc(new JsonObject().put("field", "initialized"));
            updateRequest.setFetchSourceContext(new FetchSourceContext().setFetchSource(true));
            updateRequest.setDocAsUpsert(true);

            rxClient().rxUpdate(updateRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(updateResponse -> {
                        assertEquals(CREATED, updateResponse.getStatus());
                        assertEquals("index", updateResponse.getIndex());
                        assertEquals("type", updateResponse.getType());
                        assertEquals("with_doc_as_upsert", updateResponse.getId());
                        GetResponse getResult = updateResponse.getGetResult();
                        assertEquals(1L, updateResponse.getVersion());
                        assertEquals("initialized", getResult.getSource().getString("field"));
                        return true;
                    });

        }
        {
            UpdateRequest updateRequest = new UpdateRequest("index", "type", "with_scripted_upsert");
            updateRequest.setFetchSourceContext(new FetchSourceContext().setFetchSource(true));
            updateRequest.setScript(new Script(ScriptType.INLINE, "ctx._source.level = params.test", "painless", null, new JsonObject().put("test", "C")));
            updateRequest.setScriptedUpsert(true);
            updateRequest.setUpsert(new JsonObject().put("level", "A"));

            rxClient().rxUpdate(updateRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(updateResponse -> {
                        assertEquals(CREATED, updateResponse.getStatus());
                        assertEquals("index", updateResponse.getIndex());
                        assertEquals("type", updateResponse.getType());
                        assertEquals("with_scripted_upsert", updateResponse.getId());

                        GetResponse getResult = updateResponse.getGetResult();
                        assertEquals(1L, updateResponse.getVersion());
                        assertEquals("C", getResult.getSource().getString("level"));
                        return true;
                    });

        }
    }

    @Test
    void testBulk() throws InterruptedException {
        int nbItems = randomIntBetween(10, 100);
        boolean[] errors = new boolean[nbItems];
        BulkRequest bulkRequest = new BulkRequest();

        for (int i = 0; i < nbItems; i++) {
            String id = String.valueOf(i);
            boolean erroneous = randomBoolean();
            errors[i] = erroneous;

            DocWriteRequest.OpType opType = randomFrom(DocWriteRequest.OpType.values());
            if (opType == DocWriteRequest.OpType.DELETE) {
                if (!erroneous) {
                    IndexRequest indexRequest = new IndexRequest("index", "test", id)
                            .setSource(new JsonObject().put("field", -1))
                            .setRefreshPolicy(WAIT_UNTIL);

                    rxClient().rxIndex(indexRequest)
                            .test()
                            .await()
                            .assertNoErrors()
                            .assertValue(indexResponse -> {
                                assertEquals(CREATED, indexResponse.getStatus());
                                return true;
                            });
                }
                DeleteRequest deleteRequest = new DeleteRequest("index", "test", id);
                bulkRequest.add(deleteRequest);

            } else {
                JsonObject source = new JsonObject().put("id", i);
                if (opType == DocWriteRequest.OpType.INDEX) {
                    IndexRequest indexRequest = new IndexRequest("index", "test", id).setSource(source);
                    if (erroneous) {
                        indexRequest.setVersion(12L);
                    }
                    bulkRequest.add(indexRequest);

                } else if (opType == DocWriteRequest.OpType.CREATE) {
                    IndexRequest createRequest = new IndexRequest("index", "test", id)
                            .setSource(source)
                            .setOpType(DocWriteRequest.OpType.CREATE)
                            .setRefreshPolicy(WAIT_UNTIL);

                    if (erroneous) {
                        rxClient().rxIndex(createRequest)
                                .test()
                                .await()
                                .assertNoErrors()
                                .assertValue(indexResponse -> {
                                    assertEquals(CREATED, indexResponse.getStatus());
                                    return true;
                                });
                    }

                    bulkRequest.add(createRequest);

                } else if (opType == DocWriteRequest.OpType.UPDATE) {
                    UpdateRequest updateRequest = new UpdateRequest("index", "test", id).setDoc(source);
                    if (!erroneous) {
                        IndexRequest createRequest = new IndexRequest("index", "test", id)
                                .setSource(new JsonObject().put("field", -1))
                                .setRefreshPolicy(WAIT_UNTIL);

                        rxClient().rxIndex(createRequest)
                                .test()
                                .await()
                                .assertNoErrors()
                                .assertValue(indexResponse -> {
                                    assertEquals(CREATED, indexResponse.getStatus());
                                    return true;
                                });
                    }
                    bulkRequest.add(updateRequest);
                }
            }
        }

        rxClient().rxBulk(bulkRequest)
                .test()
                .await()
                .assertNoErrors()
                .assertValue(bulkResponse -> {
                    assertEquals(RestStatus.OK, bulkResponse.getStatus());
                    assertTrue(bulkResponse.getTook() > 0);
                    assertEquals(nbItems, bulkResponse.getItems().size());

                    validateBulkResponses(nbItems, errors, bulkResponse, bulkRequest);
                    return true;
                });


    }


    private void validateBulkResponses(int nbItems, boolean[] errors, BulkResponse bulkResponse, BulkRequest bulkRequest) {
        for (int i = 0; i < nbItems; i++) {
            BulkResponseItem bulkItemResponse = bulkResponse.getItems().get(i);

            assertEquals("index", bulkItemResponse.getIndex());
            assertEquals("test", bulkItemResponse.getType());
            assertEquals(String.valueOf(i), bulkItemResponse.getId());

            DocWriteRequest.OpType requestOpType = bulkRequest.getRequests().get(i).getOpType();
            if (requestOpType == DocWriteRequest.OpType.INDEX || requestOpType == DocWriteRequest.OpType.CREATE) {
                assertEquals(errors[i], bulkItemResponse.isFailed());
                assertEquals(errors[i] ? RestStatus.CONFLICT : CREATED, bulkItemResponse.getStatus());
            } else if (requestOpType == DocWriteRequest.OpType.UPDATE) {
                assertEquals(errors[i], bulkItemResponse.isFailed());
                assertEquals(errors[i] ? RestStatus.NOT_FOUND : RestStatus.OK, bulkItemResponse.getStatus());
            } else if (requestOpType == DocWriteRequest.OpType.DELETE) {
                assertFalse(bulkItemResponse.isFailed());
                assertEquals(errors[i] ? RestStatus.NOT_FOUND : RestStatus.OK, bulkItemResponse.getStatus());
            }
        }
    }

    @Test
    void testUrlEncode() throws InterruptedException {
        String indexPattern = "<logstash-{now/M}>";
        String expectedIndex = "logstash-" +
                DateTimeFormat.forPattern("YYYY.MM.dd").print(new DateTime(DateTimeZone.UTC).monthOfYear().roundFloorCopy());
        {
            IndexRequest indexRequest = new IndexRequest(indexPattern, "type", "id#1");
            indexRequest.setSource(new JsonObject().put("field", "value"));
            rxClient().rxIndex(indexRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(indexResponse -> {
                        assertEquals(expectedIndex, indexResponse.getIndex());
                        assertEquals("type", indexResponse.getType());
                        assertEquals("id#1", indexResponse.getId());
                        return true;
                    });

        }
        {
            GetRequest getRequest = new GetRequest(indexPattern, "type", "id#1");
            rxClient().rxGet(getRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(getResponse -> {
                        assertTrue(getResponse.isExists());
                        assertEquals(expectedIndex, getResponse.getIndex());
                        assertEquals("type", getResponse.getType());
                        assertEquals("id#1", getResponse.getId());
                        return true;
                    });

        }

        String docId = "this/is/the/id/中文";
        {
            IndexRequest indexRequest = new IndexRequest("index", "type", docId);
            indexRequest.setSource(new JsonObject().put("field", "value"));
            rxClient().rxIndex(indexRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(indexResponse -> {
                        assertEquals("index", indexResponse.getIndex());
                        assertEquals("type", indexResponse.getType());
                        assertEquals(docId, indexResponse.getId());
                        return true;
                    });

        }
        {
            GetRequest getRequest = new GetRequest("index", "type", docId);
            rxClient().rxGet(getRequest)
                    .test()
                    .await()
                    .assertNoErrors()
                    .assertValue(getResponse -> {
                        assertTrue(getResponse.isExists());
                        assertEquals("index", getResponse.getIndex());
                        assertEquals("type", getResponse.getType());
                        assertEquals(docId, getResponse.getId());
                        return true;
                    });

        }

        rxClient().indices().rxExists(new GetIndexRequest().setIndices(Arrays.asList(indexPattern, "index")))
                .test()
                .await()
                .assertValue(true);
    }

    @Test
    void testParamsEncode() throws InterruptedException {
        //parameters are encoded by the low-level client but let's test that everything works the same when we use the high-level one
        String routing = "routing/中文value#1?";
        {
            IndexRequest indexRequest = new IndexRequest("index", "type", "id");
            indexRequest.setSource(new JsonObject().put("field", "value"));
            indexRequest.setRouting(routing);
            rxClient().rxIndex(indexRequest).test()
                    .await()
                    .assertNoErrors()
                    .assertValue(indexResponse -> {
                        assertEquals("index", indexResponse.getIndex());
                        assertEquals("type", indexResponse.getType());
                        assertEquals("id", indexResponse.getId());
                        return true;
                    });

        }
        {
            GetRequest getRequest = new GetRequest("index", "type", "id").setRouting(routing);
            rxClient().rxGet(getRequest).test()
                    .await()
                    .assertNoErrors()
                    .assertValue(getResponse -> {
                        assertEquals("index", getResponse.getIndex());
                        assertEquals("type", getResponse.getType());
                        assertEquals("id", getResponse.getId());
                        assertEquals(routing, getResponse.getField("_routing").getValue());
                        return true;
                    });

        }
    }
}
