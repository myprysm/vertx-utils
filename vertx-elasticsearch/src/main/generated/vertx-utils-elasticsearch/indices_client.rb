require 'vertx/util/utils.rb'
# Generated from fr.myprysm.vertx.elasticsearch.IndicesClient
module VertxUtilsElasticsearch
  #  Vertx Elasticsearch indices client.
  #  <p>
  #  See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/indices.html">Indices API on elastic.co</a>
  class IndicesClient
    # @private
    # @param j_del [::VertxUtilsElasticsearch::IndicesClient] the java delegate
    def initialize(j_del)
      @j_del = j_del
    end
    # @private
    # @return [::VertxUtilsElasticsearch::IndicesClient] the underlying java delegate
    def j_del
      @j_del
    end
    @@j_api_type = Object.new
    def @@j_api_type.accept?(obj)
      obj.class == IndicesClient
    end
    def @@j_api_type.wrap(obj)
      IndicesClient.new(obj)
    end
    def @@j_api_type.unwrap(obj)
      obj.j_del
    end
    def self.j_api_type
      @@j_api_type
    end
    def self.j_class
      Java::FrMyprysmVertxElasticsearch::IndicesClient.java_class
    end
    #  Asynchronously deletes an index using the Delete Index API.
    #  <p>
    #  See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-delete-index.html">
    #  Delete Index API on elastic.co</a>
    # @param [Hash] request the request
    # @yield the handler
    # @return [void]
    def delete(request=nil)
      if request.class == Hash && block_given?
        return @j_del.java_method(:delete, [Java::FrMyprysmVertxElasticsearchActionAdminIndicesDelete::DeleteIndexRequest.java_class,Java::IoVertxCore::Handler.java_class]).call(Java::FrMyprysmVertxElasticsearchActionAdminIndicesDelete::DeleteIndexRequest.new(::Vertx::Util::Utils.to_json_object(request)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling delete(#{request})"
    end
    # @param [Hash] request 
    # @yield 
    # @return [void]
    def create(request=nil)
      if request.class == Hash && block_given?
        return @j_del.java_method(:create, [Java::FrMyprysmVertxElasticsearchActionAdminIndicesCreate::CreateIndexRequest.java_class,Java::IoVertxCore::Handler.java_class]).call(Java::FrMyprysmVertxElasticsearchActionAdminIndicesCreate::CreateIndexRequest.new(::Vertx::Util::Utils.to_json_object(request)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling create(#{request})"
    end
    # @param [Hash] request 
    # @yield 
    # @return [void]
    def put_mapping(request=nil)
      if request.class == Hash && block_given?
        return @j_del.java_method(:putMapping, [Java::FrMyprysmVertxElasticsearchActionAdminIndicesMappingPut::PutMappingRequest.java_class,Java::IoVertxCore::Handler.java_class]).call(Java::FrMyprysmVertxElasticsearchActionAdminIndicesMappingPut::PutMappingRequest.new(::Vertx::Util::Utils.to_json_object(request)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling put_mapping(#{request})"
    end
    # @param [Hash] request 
    # @yield 
    # @return [void]
    def refresh(request=nil)
      if request.class == Hash && block_given?
        return @j_del.java_method(:refresh, [Java::FrMyprysmVertxElasticsearchActionAdminRefresh::RefreshRequest.java_class,Java::IoVertxCore::Handler.java_class]).call(Java::FrMyprysmVertxElasticsearchActionAdminRefresh::RefreshRequest.new(::Vertx::Util::Utils.to_json_object(request)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling refresh(#{request})"
    end
    #  Asynchronously checks if one or more aliases exist using the Aliases Exist API.
    #  <p>
    #  See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-aliases.html">
    #  Indices Aliases API on elastic.co</a>
    # @param [Hash] request the request
    # @yield the handler
    # @return [void]
    def exists(request=nil)
      if request.class == Hash && block_given?
        return @j_del.java_method(:exists, [Java::FrMyprysmVertxElasticsearchActionAdminIndicesGet::GetIndexRequest.java_class,Java::IoVertxCore::Handler.java_class]).call(Java::FrMyprysmVertxElasticsearchActionAdminIndicesGet::GetIndexRequest.new(::Vertx::Util::Utils.to_json_object(request)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling exists(#{request})"
    end
  end
end
