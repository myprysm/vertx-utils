require 'vertx-utils-elasticsearch/indices_client'
require 'vertx/vertx'
require 'vertx-utils-elasticsearch/cluster_client'
require 'vertx/util/utils.rb'
# Generated from fr.myprysm.vertx.elasticsearch.ElasticsearchClient
module VertxUtilsElasticsearch
  #  Vertx Elasticsearch client.
  #  <p>
  #  See Elasticsearch documentation for precise usage (either
  #  java client
  #  or
  #  <a href="https://www.elastic.co/guide/en/elasticsearch/client/java-rest/master/">rest client</a>
  #  ).
  class ElasticsearchClient
    # @private
    # @param j_del [::VertxUtilsElasticsearch::ElasticsearchClient] the java delegate
    def initialize(j_del)
      @j_del = j_del
    end
    # @private
    # @return [::VertxUtilsElasticsearch::ElasticsearchClient] the underlying java delegate
    def j_del
      @j_del
    end
    @@j_api_type = Object.new
    def @@j_api_type.accept?(obj)
      obj.class == ElasticsearchClient
    end
    def @@j_api_type.wrap(obj)
      ElasticsearchClient.new(obj)
    end
    def @@j_api_type.unwrap(obj)
      obj.j_del
    end
    def self.j_api_type
      @@j_api_type
    end
    def self.j_class
      Java::FrMyprysmVertxElasticsearch::ElasticsearchClient.java_class
    end
    #  Close the client.
    # @return [void]
    def close
      if !block_given?
        return @j_del.java_method(:close, []).call()
      end
      raise ArgumentError, "Invalid arguments when calling close()"
    end
    #  Provides an {::VertxUtilsElasticsearch::IndicesClient} which can be used to access the Indices API.
    #  <p>
    #  See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/indices.html">Indices API on elastic.co</a>
    # @return [::VertxUtilsElasticsearch::IndicesClient] the indices client
    def indices
      if !block_given?
        return ::Vertx::Util::Utils.safe_create(@j_del.java_method(:indices, []).call(),::VertxUtilsElasticsearch::IndicesClient)
      end
      raise ArgumentError, "Invalid arguments when calling indices()"
    end
    #  Provides a {::VertxUtilsElasticsearch::ClusterClient} which can be used to access the Cluster API.
    #  <p>
    #  See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster.html">Cluster API on elastic.co</a>
    # @return [::VertxUtilsElasticsearch::ClusterClient] the cluster client
    def cluster
      if !block_given?
        return ::Vertx::Util::Utils.safe_create(@j_del.java_method(:cluster, []).call(),::VertxUtilsElasticsearch::ClusterClient)
      end
      raise ArgumentError, "Invalid arguments when calling cluster()"
    end
    #  Asynchronously pings the remote Elasticsearch cluster and returns true if the ping succeeded, false otherwise.
    # @param [Hash] request the optional request
    # @yield the handler
    # @return [void]
    def ping(request=nil)
      if block_given? && request == nil
        return @j_del.java_method(:ping, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
      elsif request.class == Hash && block_given?
        return @j_del.java_method(:ping, [Java::FrMyprysmVertxElasticsearchAction::BaseRequest.java_class,Java::IoVertxCore::Handler.java_class]).call(Java::FrMyprysmVertxElasticsearchAction::BaseRequest.new(::Vertx::Util::Utils.to_json_object(request)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling ping(#{request})"
    end
    #  Asynchronously get the cluster info otherwise provided when sending an HTTP request to port 9200.
    # @param [Hash] request the optional request
    # @yield the handler
    # @return [void]
    def info(request=nil)
      if block_given? && request == nil
        return @j_del.java_method(:info, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      elsif request.class == Hash && block_given?
        return @j_del.java_method(:info, [Java::FrMyprysmVertxElasticsearchAction::BaseRequest.java_class,Java::IoVertxCore::Handler.java_class]).call(Java::FrMyprysmVertxElasticsearchAction::BaseRequest.new(::Vertx::Util::Utils.to_json_object(request)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling info(#{request})"
    end
    #  Asynchronously retrieves a document by id using the Get API.
    #  <p>
    #  See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html">Get API on elastic.co</a>
    # @param [Hash] request the request
    # @yield the handler
    # @return [void]
    def get(request=nil)
      if request.class == Hash && block_given?
        return @j_del.java_method(:get, [Java::FrMyprysmVertxElasticsearchActionGet::GetRequest.java_class,Java::IoVertxCore::Handler.java_class]).call(Java::FrMyprysmVertxElasticsearchActionGet::GetRequest.new(::Vertx::Util::Utils.to_json_object(request)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling get(#{request})"
    end
    #  Asynchronously checks for the existence of a document. Returns true if it exists, false otherwise.
    #  <p>
    #  See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html">Get API on elastic.co</a>
    # @param [Hash] request the request
    # @yield the handler
    # @return [void]
    def exists(request=nil)
      if request.class == Hash && block_given?
        return @j_del.java_method(:exists, [Java::FrMyprysmVertxElasticsearchActionGet::GetRequest.java_class,Java::IoVertxCore::Handler.java_class]).call(Java::FrMyprysmVertxElasticsearchActionGet::GetRequest.new(::Vertx::Util::Utils.to_json_object(request)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling exists(#{request})"
    end
    #  Asynchronously retrieves multiple documents by id using the Multi Get API.
    #  <p>
    #  See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-multi-get.html">Multi Get API on elastic.co</a>
    # @param [Hash] request the request
    # @yield the handler
    # @return [void]
    def multi_get(request=nil)
      if request.class == Hash && block_given?
        return @j_del.java_method(:multiGet, [Java::FrMyprysmVertxElasticsearchActionGet::MultiGetRequest.java_class,Java::IoVertxCore::Handler.java_class]).call(Java::FrMyprysmVertxElasticsearchActionGet::MultiGetRequest.new(::Vertx::Util::Utils.to_json_object(request)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling multi_get(#{request})"
    end
    #  Asynchronously index a document using the Index API.
    #  <p>
    #  See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-index_.html">Index API on elastic.co</a>
    # @param [Hash] request the request
    # @yield the handler
    # @return [void]
    def index(request=nil)
      if request.class == Hash && block_given?
        return @j_del.java_method(:index, [Java::FrMyprysmVertxElasticsearchActionIndex::IndexRequest.java_class,Java::IoVertxCore::Handler.java_class]).call(Java::FrMyprysmVertxElasticsearchActionIndex::IndexRequest.new(::Vertx::Util::Utils.to_json_object(request)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling index(#{request})"
    end
    #  Asynchronously updates a document using the Update API.
    #  <p>
    #  See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update.html">Update API on elastic.co</a>
    # @param [Hash] request the request
    # @yield the handler
    # @return [void]
    def update(request=nil)
      if request.class == Hash && block_given?
        return @j_del.java_method(:update, [Java::FrMyprysmVertxElasticsearchActionUpdate::UpdateRequest.java_class,Java::IoVertxCore::Handler.java_class]).call(Java::FrMyprysmVertxElasticsearchActionUpdate::UpdateRequest.new(::Vertx::Util::Utils.to_json_object(request)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling update(#{request})"
    end
    #  Asynchronously deletes a document by id using the Delete API.
    #  <p>
    #  See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-delete.html">Delete API on elastic.co</a>
    # @param [Hash] request the request
    # @yield the handler
    # @return [void]
    def delete(request=nil)
      if request.class == Hash && block_given?
        return @j_del.java_method(:delete, [Java::FrMyprysmVertxElasticsearchActionDelete::DeleteRequest.java_class,Java::IoVertxCore::Handler.java_class]).call(Java::FrMyprysmVertxElasticsearchActionDelete::DeleteRequest.new(::Vertx::Util::Utils.to_json_object(request)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling delete(#{request})"
    end
    #  Asynchronously executes a search using the Search API
    #  <p>
    #  See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/search-search.html">Search API on elastic.co</a>
    # @param [Hash] request the request
    # @yield the handler
    # @return [void]
    def search(request=nil)
      if request.class == Hash && block_given?
        return @j_del.java_method(:search, [Java::FrMyprysmVertxElasticsearchActionSearch::SearchRequest.java_class,Java::IoVertxCore::Handler.java_class]).call(Java::FrMyprysmVertxElasticsearchActionSearch::SearchRequest.new(::Vertx::Util::Utils.to_json_object(request)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling search(#{request})"
    end
    #  Asynchronously executes a multi search using the msearch API
    #  <p>
    #  See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/search-multi-search.html">Multi search API on
    #  elastic.co</a>
    # @param [Hash] request the request
    # @yield the handler
    # @return [void]
    def multi_search(request=nil)
      if request.class == Hash && block_given?
        return @j_del.java_method(:multiSearch, [Java::FrMyprysmVertxElasticsearchActionSearch::MultiSearchRequest.java_class,Java::IoVertxCore::Handler.java_class]).call(Java::FrMyprysmVertxElasticsearchActionSearch::MultiSearchRequest.new(::Vertx::Util::Utils.to_json_object(request)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling multi_search(#{request})"
    end
    #  Asynchronously executes a search using the Search Scroll API
    #  <p>
    #  See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/search-request-scroll.html">Search Scroll
    #  API on elastic.co</a>
    # @param [Hash] request the request
    # @yield the handler
    # @return [void]
    def search_scroll(request=nil)
      if request.class == Hash && block_given?
        return @j_del.java_method(:searchScroll, [Java::FrMyprysmVertxElasticsearchActionSearch::SearchScrollRequest.java_class,Java::IoVertxCore::Handler.java_class]).call(Java::FrMyprysmVertxElasticsearchActionSearch::SearchScrollRequest.new(::Vertx::Util::Utils.to_json_object(request)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling search_scroll(#{request})"
    end
    #  Asynchronously clears one or more scroll ids using the Clear Scroll API
    #  <p>
    #  See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/search-request-scroll.html#_clear_scroll_api">
    #  Clear Scroll API on elastic.co</a>
    # @param [Hash] request the request
    # @yield the handler
    # @return [void]
    def clear_scroll(request=nil)
      if request.class == Hash && block_given?
        return @j_del.java_method(:clearScroll, [Java::FrMyprysmVertxElasticsearchActionSearch::ClearScrollRequest.java_class,Java::IoVertxCore::Handler.java_class]).call(Java::FrMyprysmVertxElasticsearchActionSearch::ClearScrollRequest.new(::Vertx::Util::Utils.to_json_object(request)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling clear_scroll(#{request})"
    end
    #  Asynchronously executes a bulk request using the Bulk API
    #  <p>
    #  See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-bulk.html">Bulk API on elastic.co</a>
    # @param [Hash] request the request
    # @yield the handler
    # @return [void]
    def bulk(request=nil)
      if request.class == Hash && block_given?
        return @j_del.java_method(:bulk, [Java::FrMyprysmVertxElasticsearchActionBulk::BulkRequest.java_class,Java::IoVertxCore::Handler.java_class]).call(Java::FrMyprysmVertxElasticsearchActionBulk::BulkRequest.new(::Vertx::Util::Utils.to_json_object(request)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling bulk(#{request})"
    end
    #  Create an ElasticSearch client which maintains its own data source.
    # @param [::Vertx::Vertx] vertx the Vert.x instance
    # @param [Hash{String => Object}] config the configuration
    # @return [::VertxUtilsElasticsearch::ElasticsearchClient] the client
    def self.create_non_shared(vertx=nil,config=nil)
      if vertx.class.method_defined?(:j_del) && config.class == Hash && !block_given?
        return ::Vertx::Util::Utils.safe_create(Java::FrMyprysmVertxElasticsearch::ElasticsearchClient.java_method(:createNonShared, [Java::IoVertxCore::Vertx.java_class,Java::IoVertxCoreJson::JsonObject.java_class]).call(vertx.j_del,::Vertx::Util::Utils.to_json_object(config)),::VertxUtilsElasticsearch::ElasticsearchClient)
      end
      raise ArgumentError, "Invalid arguments when calling create_non_shared(#{vertx},#{config})"
    end
    #  Create an ElasticSearch client which shares its data source with any other Mongo clients created with the same
    #  client name.
    # @param [::Vertx::Vertx] vertx the Vert.x instance
    # @param [Hash{String => Object}] config the configuration
    # @param [String] clientName the client name
    # @return [::VertxUtilsElasticsearch::ElasticsearchClient] the client
    def self.create_shared(vertx=nil,config=nil,clientName=nil)
      if vertx.class.method_defined?(:j_del) && config.class == Hash && !block_given? && clientName == nil
        return ::Vertx::Util::Utils.safe_create(Java::FrMyprysmVertxElasticsearch::ElasticsearchClient.java_method(:createShared, [Java::IoVertxCore::Vertx.java_class,Java::IoVertxCoreJson::JsonObject.java_class]).call(vertx.j_del,::Vertx::Util::Utils.to_json_object(config)),::VertxUtilsElasticsearch::ElasticsearchClient)
      elsif vertx.class.method_defined?(:j_del) && config.class == Hash && clientName.class == String && !block_given?
        return ::Vertx::Util::Utils.safe_create(Java::FrMyprysmVertxElasticsearch::ElasticsearchClient.java_method(:createShared, [Java::IoVertxCore::Vertx.java_class,Java::IoVertxCoreJson::JsonObject.java_class,Java::java.lang.String.java_class]).call(vertx.j_del,::Vertx::Util::Utils.to_json_object(config),clientName),::VertxUtilsElasticsearch::ElasticsearchClient)
      end
      raise ArgumentError, "Invalid arguments when calling create_shared(#{vertx},#{config},#{clientName})"
    end
  end
end
