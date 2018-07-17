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

package fr.myprysm.vertx.elasticsearch.support;

import org.elasticsearch.join.aggregations.ChildrenAggregationBuilder;
import org.elasticsearch.join.aggregations.InternalChildren;
import org.elasticsearch.plugins.SearchPlugin;

import java.util.ArrayList;
import java.util.List;

public class SupportSearchPlugin implements SearchPlugin {

    private List<AggregationSpec> aggregations;

    public SupportSearchPlugin() {
        prepareAggregations();
    }

    private void prepareAggregations() {
        aggregations = new ArrayList<>();
        aggregations.add(
                new AggregationSpec(
                        ChildrenAggregationBuilder.NAME,
                        ChildrenAggregationBuilder::new,
                        ChildrenAggregationBuilder::parse
                ).addResultReader(InternalChildren::new)
        );
    }

    @Override
    public List<AggregationSpec> getAggregations() {
        return aggregations;
    }
}
