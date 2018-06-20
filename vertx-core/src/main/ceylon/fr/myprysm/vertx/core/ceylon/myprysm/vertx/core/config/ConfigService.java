package fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config;

import com.redhat.ceylon.compiler.java.metadata.Ceylon;
import com.redhat.ceylon.compiler.java.metadata.TypeInfo;
import com.redhat.ceylon.compiler.java.metadata.TypeParameter;
import com.redhat.ceylon.compiler.java.metadata.TypeParameters;
import com.redhat.ceylon.compiler.java.metadata.Variance;
import com.redhat.ceylon.compiler.java.metadata.Ignore;
import com.redhat.ceylon.compiler.java.metadata.Name;
import com.redhat.ceylon.compiler.java.runtime.model.TypeDescriptor;
import com.redhat.ceylon.compiler.java.runtime.model.ReifiedType;
import ceylon.language.Callable;
import ceylon.language.DocAnnotation$annotation$;
import io.vertx.core.json.JsonArray;
import java.util.List;
import io.vertx.core.json.JsonObject;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Ceylon(major = 8)
@DocAnnotation$annotation$(description = " Configuration Service that provides all the configuration items for the application\n")
public class ConfigService implements ReifiedType {

  @Ignore
  public static final io.vertx.lang.ceylon.ConverterFactory<fr.myprysm.vertx.core.config.ConfigService, ConfigService> TO_CEYLON = new io.vertx.lang.ceylon.ConverterFactory<fr.myprysm.vertx.core.config.ConfigService, ConfigService>() {
    public io.vertx.lang.ceylon.Converter<fr.myprysm.vertx.core.config.ConfigService, ConfigService> converter(final TypeDescriptor... descriptors) {
      return new io.vertx.lang.ceylon.Converter<fr.myprysm.vertx.core.config.ConfigService, ConfigService>() {
        public ConfigService convert(fr.myprysm.vertx.core.config.ConfigService src) {
          return new ConfigService(src);
        }
      };
    }
  };

  @Ignore
  public static final io.vertx.lang.ceylon.Converter<ConfigService, fr.myprysm.vertx.core.config.ConfigService> TO_JAVA = new io.vertx.lang.ceylon.Converter<ConfigService, fr.myprysm.vertx.core.config.ConfigService>() {
    public fr.myprysm.vertx.core.config.ConfigService convert(ConfigService src) {
      return src.delegate;
    }
  };

  @Ignore public static final TypeDescriptor $TypeDescriptor$ = new io.vertx.lang.ceylon.VertxTypeDescriptor(TypeDescriptor.klass(ConfigService.class), fr.myprysm.vertx.core.config.ConfigService.class, TO_JAVA, TO_CEYLON);
  @Ignore private final fr.myprysm.vertx.core.config.ConfigService delegate;

  public ConfigService(fr.myprysm.vertx.core.config.ConfigService delegate) {
    this.delegate = delegate;
  }

  @Ignore 
  public TypeDescriptor $getType$() {
    return $TypeDescriptor$;
  }

  @Ignore
  public Object getDelegate() {
    return delegate;
  }

  @DocAnnotation$annotation$(description = " Get the configured verticles as a <code>JsonArray</code>.\n")
  @TypeInfo("fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config::ConfigService")
  public ConfigService getVerticles(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::List<fr.myprysm.vertx.core.ceylon.myprysm.vertx.core::VerticleOptions>)") @Name("handler")@DocAnnotation$annotation$(description = "the result handler\n") Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.List<fr.myprysm.vertx.core.VerticleOptions>>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.util.List<fr.myprysm.vertx.core.VerticleOptions>>(handler) {
      public Object toCeylon(java.util.List<fr.myprysm.vertx.core.VerticleOptions> event) {
        return io.vertx.lang.ceylon.ToCeylon.convertList(fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.VerticleOptions.$TypeDescriptor$, event, fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.verticleOptions_.get_().getToCeylon());
      }
    };
    ConfigService ret = fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config.ConfigService.TO_CEYLON.converter().safeConvert(delegate.getVerticles(arg_0));
    return this;
  }

  @DocAnnotation$annotation$(description = " Get the whole configuration as a <code>JsonObject</code>.\n")
  @TypeInfo("fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config::ConfigService")
  public ConfigService getConfig(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.json::Object)") @Name("handler")@DocAnnotation$annotation$(description = "the result handler\n") Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.json.JsonObject>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.json.JsonObject>(handler) {
      public Object toCeylon(io.vertx.core.json.JsonObject event) {
        return io.vertx.lang.ceylon.ToCeylon.JsonObject.safeConvert(event);
      }
    };
    ConfigService ret = fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config.ConfigService.TO_CEYLON.converter().safeConvert(delegate.getConfig(arg_0));
    return this;
  }

  @DocAnnotation$annotation$(description = " Get the configuration item identified by the <code>key</code> as a <code>String</code>.\n")
  @TypeInfo("fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config::ConfigService")
  public ConfigService getString(
    final @TypeInfo("ceylon.language::String") @Name("key")@DocAnnotation$annotation$(description = "the key to search\n") ceylon.language.String key, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::String)") @Name("handler")@DocAnnotation$annotation$(description = "the result handler\n") Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(key);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.String>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.String>(handler) {
      public Object toCeylon(java.lang.String event) {
        return io.vertx.lang.ceylon.ToCeylon.String.safeConvert(event);
      }
    };
    ConfigService ret = fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config.ConfigService.TO_CEYLON.converter().safeConvert(delegate.getString(arg_0, arg_1));
    return this;
  }

  @DocAnnotation$annotation$(description = " Get the configuration item identified by the <code>key</code> as a <code>Boolean</code>.\n")
  @TypeInfo("fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config::ConfigService")
  public ConfigService getBoolean(
    final @TypeInfo("ceylon.language::String") @Name("key")@DocAnnotation$annotation$(description = "the key to search\n") ceylon.language.String key, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::Boolean)") @Name("handler")@DocAnnotation$annotation$(description = "the result handler\n") Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(key);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Boolean>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Boolean>(handler) {
      public Object toCeylon(java.lang.Boolean event) {
        return io.vertx.lang.ceylon.ToCeylon.Boolean.safeConvert(event);
      }
    };
    ConfigService ret = fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config.ConfigService.TO_CEYLON.converter().safeConvert(delegate.getBoolean(arg_0, arg_1));
    return this;
  }

  @DocAnnotation$annotation$(description = " Get the configuration item identified by the <code>key</code> as an <code>Integer</code>.\n")
  @TypeInfo("fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config::ConfigService")
  public ConfigService getInteger(
    final @TypeInfo("ceylon.language::String") @Name("key")@DocAnnotation$annotation$(description = "the key to search\n") ceylon.language.String key, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::Integer)") @Name("handler")@DocAnnotation$annotation$(description = "the result handler\n") Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(key);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Integer>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Integer>(handler) {
      public Object toCeylon(java.lang.Integer event) {
        return io.vertx.lang.ceylon.ToCeylon.Integer.safeConvert(event);
      }
    };
    ConfigService ret = fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config.ConfigService.TO_CEYLON.converter().safeConvert(delegate.getInteger(arg_0, arg_1));
    return this;
  }

  @DocAnnotation$annotation$(description = " Get the configuration item identified by the <code>key</code> as a <code>Long</code>.\n")
  @TypeInfo("fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config::ConfigService")
  public ConfigService getLong(
    final @TypeInfo("ceylon.language::String") @Name("key")@DocAnnotation$annotation$(description = "the key to search\n") ceylon.language.String key, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::Integer)") @Name("handler")@DocAnnotation$annotation$(description = "the result handler\n") Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(key);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Long>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Long>(handler) {
      public Object toCeylon(java.lang.Long event) {
        return io.vertx.lang.ceylon.ToCeylon.Long.safeConvert(event);
      }
    };
    ConfigService ret = fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config.ConfigService.TO_CEYLON.converter().safeConvert(delegate.getLong(arg_0, arg_1));
    return this;
  }

  @DocAnnotation$annotation$(description = " Get the configuration item identified by the <code>key</code> as a <code>Float</code>.\n")
  @TypeInfo("fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config::ConfigService")
  public ConfigService getFloat(
    final @TypeInfo("ceylon.language::String") @Name("key")@DocAnnotation$annotation$(description = "the key to search\n") ceylon.language.String key, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::Float)") @Name("handler")@DocAnnotation$annotation$(description = "the result handler\n") Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(key);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Float>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Float>(handler) {
      public Object toCeylon(java.lang.Float event) {
        return io.vertx.lang.ceylon.ToCeylon.Float.safeConvert(event);
      }
    };
    ConfigService ret = fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config.ConfigService.TO_CEYLON.converter().safeConvert(delegate.getFloat(arg_0, arg_1));
    return this;
  }

  @DocAnnotation$annotation$(description = " Get the configuration item identified by the <code>key</code> as a <code>Double</code>.\n")
  @TypeInfo("fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config::ConfigService")
  public ConfigService getDouble(
    final @TypeInfo("ceylon.language::String") @Name("key")@DocAnnotation$annotation$(description = "the key to search\n") ceylon.language.String key, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::Float)") @Name("handler")@DocAnnotation$annotation$(description = "the result handler\n") Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(key);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Double>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Double>(handler) {
      public Object toCeylon(java.lang.Double event) {
        return io.vertx.lang.ceylon.ToCeylon.Double.safeConvert(event);
      }
    };
    ConfigService ret = fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config.ConfigService.TO_CEYLON.converter().safeConvert(delegate.getDouble(arg_0, arg_1));
    return this;
  }

  @DocAnnotation$annotation$(description = " Get the configuration item identified by the <code>key</code> as a <code>JsonArray</code>.\n")
  @TypeInfo("fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config::ConfigService")
  public ConfigService getArray(
    final @TypeInfo("ceylon.language::String") @Name("key")@DocAnnotation$annotation$(description = "the key to search\n") ceylon.language.String key, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.json::Array)") @Name("handler")@DocAnnotation$annotation$(description = "the result handler\n") Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(key);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.json.JsonArray>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.json.JsonArray>(handler) {
      public Object toCeylon(io.vertx.core.json.JsonArray event) {
        return io.vertx.lang.ceylon.ToCeylon.JsonArray.safeConvert(event);
      }
    };
    ConfigService ret = fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config.ConfigService.TO_CEYLON.converter().safeConvert(delegate.getArray(arg_0, arg_1));
    return this;
  }

  @DocAnnotation$annotation$(description = " Get the configuration item identified by the <code>key</code> as a <code>JsonObject</code>.\n")
  @TypeInfo("fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config::ConfigService")
  public ConfigService getObject(
    final @TypeInfo("ceylon.language::String") @Name("key")@DocAnnotation$annotation$(description = "the key to search\n") ceylon.language.String key, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.json::Object)") @Name("handler")@DocAnnotation$annotation$(description = "the result handler\n") Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(key);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.json.JsonObject>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.json.JsonObject>(handler) {
      public Object toCeylon(io.vertx.core.json.JsonObject event) {
        return io.vertx.lang.ceylon.ToCeylon.JsonObject.safeConvert(event);
      }
    };
    ConfigService ret = fr.myprysm.vertx.core.ceylon.myprysm.vertx.core.config.ConfigService.TO_CEYLON.converter().safeConvert(delegate.getObject(arg_0, arg_1));
    return this;
  }

}
