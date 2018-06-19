require 'vertx/util/utils.rb'
# Generated from fr.myprysm.vertx.core.config.ConfigService
module MyprysmVertxCore
  #  Configuration Service that provides all the configuration items for the application
  class ConfigService
    # @private
    # @param j_del [::MyprysmVertxCore::ConfigService] the java delegate
    def initialize(j_del)
      @j_del = j_del
    end
    # @private
    # @return [::MyprysmVertxCore::ConfigService] the underlying java delegate
    def j_del
      @j_del
    end
    @@j_api_type = Object.new
    def @@j_api_type.accept?(obj)
      obj.class == ConfigService
    end
    def @@j_api_type.wrap(obj)
      ConfigService.new(obj)
    end
    def @@j_api_type.unwrap(obj)
      obj.j_del
    end
    def self.j_api_type
      @@j_api_type
    end
    def self.j_class
      Java::FrMyprysmVertxCoreConfig::ConfigService.java_class
    end
    #  Get the configured verticles as a <code>JsonArray</code>.
    # @yield the result handler
    # @return [self]
    def get_verticles
      if block_given?
        @j_del.java_method(:getVerticles, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result.to_a.map { |elt| elt != nil ? JSON.parse(elt.toJson.encode) : nil } : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_verticles()"
    end
    #  Get the whole configuration as a <code>JsonObject</code>.
    # @yield the result handler
    # @return [self]
    def get_config
      if block_given?
        @j_del.java_method(:getConfig, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.encode) : nil : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_config()"
    end
    #  Get the configuration item identified by the <code>key</code> as a <code>String</code>.
    # @param [String] key the key to search
    # @yield the result handler
    # @return [self]
    def get_string(key=nil)
      if key.class == String && block_given?
        @j_del.java_method(:getString, [Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(key,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_string(#{key})"
    end
    #  Get the configuration item identified by the <code>key</code> as a <code>Boolean</code>.
    # @param [String] key the key to search
    # @yield the result handler
    # @return [self]
    def get_boolean(key=nil)
      if key.class == String && block_given?
        @j_del.java_method(:getBoolean, [Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(key,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_boolean(#{key})"
    end
    #  Get the configuration item identified by the <code>key</code> as an <code>Integer</code>.
    # @param [String] key the key to search
    # @yield the result handler
    # @return [self]
    def get_integer(key=nil)
      if key.class == String && block_given?
        @j_del.java_method(:getInteger, [Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(key,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_integer(#{key})"
    end
    #  Get the configuration item identified by the <code>key</code> as a <code>Long</code>.
    # @param [String] key the key to search
    # @yield the result handler
    # @return [self]
    def get_long(key=nil)
      if key.class == String && block_given?
        @j_del.java_method(:getLong, [Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(key,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_long(#{key})"
    end
    #  Get the configuration item identified by the <code>key</code> as a <code>Float</code>.
    # @param [String] key the key to search
    # @yield the result handler
    # @return [self]
    def get_float(key=nil)
      if key.class == String && block_given?
        @j_del.java_method(:getFloat, [Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(key,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_float(#{key})"
    end
    #  Get the configuration item identified by the <code>key</code> as a <code>Double</code>.
    # @param [String] key the key to search
    # @yield the result handler
    # @return [self]
    def get_double(key=nil)
      if key.class == String && block_given?
        @j_del.java_method(:getDouble, [Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(key,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_double(#{key})"
    end
    #  Get the configuration item identified by the <code>key</code> as a <code>JsonArray</code>.
    # @param [String] key the key to search
    # @yield the result handler
    # @return [self]
    def get_array(key=nil)
      if key.class == String && block_given?
        @j_del.java_method(:getArray, [Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(key,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.encode) : nil : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_array(#{key})"
    end
    #  Get the configuration item identified by the <code>key</code> as a <code>JsonObject</code>.
    # @param [String] key the key to search
    # @yield the result handler
    # @return [self]
    def get_object(key=nil)
      if key.class == String && block_given?
        @j_del.java_method(:getObject, [Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(key,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.encode) : nil : nil) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling get_object(#{key})"
    end
  end
end
