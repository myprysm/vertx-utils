package fr.myprysm.vertx.service.ceylon.myprysm.vertx.service;

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
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Ceylon(major = 8)
public class SimpleService implements ReifiedType {

  @Ignore
  public static final io.vertx.lang.ceylon.ConverterFactory<fr.myprysm.vertx.service.SimpleService, SimpleService> TO_CEYLON = new io.vertx.lang.ceylon.ConverterFactory<fr.myprysm.vertx.service.SimpleService, SimpleService>() {
    public io.vertx.lang.ceylon.Converter<fr.myprysm.vertx.service.SimpleService, SimpleService> converter(final TypeDescriptor... descriptors) {
      return new io.vertx.lang.ceylon.Converter<fr.myprysm.vertx.service.SimpleService, SimpleService>() {
        public SimpleService convert(fr.myprysm.vertx.service.SimpleService src) {
          return new SimpleService(src);
        }
      };
    }
  };

  @Ignore
  public static final io.vertx.lang.ceylon.Converter<SimpleService, fr.myprysm.vertx.service.SimpleService> TO_JAVA = new io.vertx.lang.ceylon.Converter<SimpleService, fr.myprysm.vertx.service.SimpleService>() {
    public fr.myprysm.vertx.service.SimpleService convert(SimpleService src) {
      return src.delegate;
    }
  };

  @Ignore public static final TypeDescriptor $TypeDescriptor$ = new io.vertx.lang.ceylon.VertxTypeDescriptor(TypeDescriptor.klass(SimpleService.class), fr.myprysm.vertx.service.SimpleService.class, TO_JAVA, TO_CEYLON);
  @Ignore private final fr.myprysm.vertx.service.SimpleService delegate;

  public SimpleService(fr.myprysm.vertx.service.SimpleService delegate) {
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

  @TypeInfo("fr.myprysm.vertx.service.ceylon.myprysm.vertx.service::SimpleService")
  public SimpleService asyncOperation(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler") Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    SimpleService ret = fr.myprysm.vertx.service.ceylon.myprysm.vertx.service.SimpleService.TO_CEYLON.converter().safeConvert(delegate.asyncOperation(arg_0));
    return this;
  }

}
