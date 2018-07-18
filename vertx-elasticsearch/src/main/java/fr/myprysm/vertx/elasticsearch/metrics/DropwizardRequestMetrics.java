package fr.myprysm.vertx.elasticsearch.metrics;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import fr.myprysm.vertx.elasticsearch.action.BaseRequest;
import io.vertx.ext.dropwizard.ThroughputTimer;

import static com.codahale.metrics.MetricRegistry.name;

public class DropwizardRequestMetrics implements RequestMetrics {
    private final MetricRegistry registry;
    private final ThroughputTimer convertRequest;
    private Timer.Context convertRequestCtx;
    private final Counter convertRequestErrors;

    private final ThroughputTimer executeRequest;
    private Timer.Context executeRequestCtx;
    private final Counter executeRequestErrors;

    private final ThroughputTimer convertResponse;
    private Timer.Context convertResponseCtx;
    private final Counter convertResponseErrors;


    public <T extends BaseRequest> DropwizardRequestMetrics(MetricRegistry registry, Class<T> requestClass) {
        this.registry = registry;
        convertRequest = throughputTimer(name(requestClass, "convert", "request"));
        convertRequestErrors = registry.counter(name(requestClass, "convert", "request", "errors"));

        executeRequest = throughputTimer(name(requestClass, "execute", "request"));
        executeRequestErrors = registry.counter(name(requestClass, "execute", "request", "errors"));

        convertResponse = throughputTimer(name(requestClass, "convert", "response"));
        convertResponseErrors = registry.counter(name(requestClass, "convert", "response", "errors"));
    }

    @Override
    public void startConvertRequestToES() {
        convertRequestCtx = convertRequest.time();
    }

    @Override
    public void endConvertRequestToES() {
        if (convertRequestCtx != null) {
            convertRequestCtx.close();
            convertRequestCtx = null;
        }
    }

    @Override
    public void errorConvertRequestToES() {
        convertRequestErrors.inc();
    }

    @Override
    public void startRequest() {
        executeRequestCtx = executeRequest.time();
    }

    @Override
    public void endRequest() {
        if (executeRequestCtx != null) {
            executeRequestCtx.close();
            executeRequestCtx = null;
        }
    }

    @Override
    public void errorRequest() {
        executeRequestErrors.inc();
    }

    @Override
    public void startConvertResponseToDataObject() {
        convertResponseCtx = convertResponse.time();
    }

    @Override
    public void endConvertResponseToDataObject() {
        if (convertResponseCtx != null) {
            convertResponseCtx.close();
            convertResponseCtx = null;
        }
    }

    @Override
    public void errorConvertResponseToDataObject() {
        convertRequestErrors.inc();
    }

    /**
     * Provides a new {@link ThroughputTimer} when requested, fallbacks to an unregistered timer when some error occurs
     *
     * @param name the name of the timer
     * @return the timer
     */
    private ThroughputTimer throughputTimer(String name) {
        Metric metric = registry.getMetrics().get(name);
        if (metric == null) {
            return registry.register(name, new ThroughputTimer());
        } else if (metric instanceof ThroughputTimer) {
            return (ThroughputTimer) metric;
        } else {
            return new ThroughputTimer();
        }
    }

}
