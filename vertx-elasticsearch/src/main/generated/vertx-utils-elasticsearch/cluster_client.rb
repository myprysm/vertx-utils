require 'vertx/util/utils.rb'
# Generated from fr.myprysm.vertx.elasticsearch.ClusterClient
module VertxUtilsElasticsearch
  #  Vertx Elasticsearch cluster client.
  #  <p>
  #  See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster.html">Cluster API on elastic.co</a>
  class ClusterClient
    # @private
    # @param j_del [::VertxUtilsElasticsearch::ClusterClient] the java delegate
    def initialize(j_del)
      @j_del = j_del
    end
    # @private
    # @return [::VertxUtilsElasticsearch::ClusterClient] the underlying java delegate
    def j_del
      @j_del
    end
    @@j_api_type = Object.new
    def @@j_api_type.accept?(obj)
      obj.class == ClusterClient
    end
    def @@j_api_type.wrap(obj)
      ClusterClient.new(obj)
    end
    def @@j_api_type.unwrap(obj)
      obj.j_del
    end
    def self.j_api_type
      @@j_api_type
    end
    def self.j_class
      Java::FrMyprysmVertxElasticsearch::ClusterClient.java_class
    end
    #  Asynchronously updates cluster wide specific settings using the Cluster Update Settings API.
    #  <p>
    #  See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-update-settings.html"> Cluster Update Settings
    #  API on elastic.co</a>
    # @param [Hash] request the request
    # @yield the handler
    # @return [void]
    def put_settings(request=nil)
      if request.class == Hash && block_given?
        return @j_del.java_method(:putSettings, [Java::FrMyprysmVertxElasticsearchActionAdminCluster::ClusterUpdateSettingsRequest.java_class,Java::IoVertxCore::Handler.java_class]).call(Java::FrMyprysmVertxElasticsearchActionAdminCluster::ClusterUpdateSettingsRequest.new(::Vertx::Util::Utils.to_json_object(request)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling put_settings(#{request})"
    end
    #  Asynchronously get cluster wide specific settings using the Cluster Update Settings API.
    #  <p>
    #  See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-update-settings.html"> Cluster Update Settings
    #  API on elastic.co</a>
    # @param [Hash] request the request
    # @yield the handler
    # @return [void]
    def get_settings(request=nil)
      if block_given? && request == nil
        return @j_del.java_method(:getSettings, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      elsif request.class == Hash && block_given?
        return @j_del.java_method(:getSettings, [Java::FrMyprysmVertxElasticsearchAction::BaseRequest.java_class,Java::IoVertxCore::Handler.java_class]).call(Java::FrMyprysmVertxElasticsearchAction::BaseRequest.new(::Vertx::Util::Utils.to_json_object(request)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling get_settings(#{request})"
    end
  end
end
