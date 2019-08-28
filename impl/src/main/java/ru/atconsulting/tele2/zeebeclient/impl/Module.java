package ru.atconsulting.tele2.zeebeclient.impl;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.typesafe.config.Config;
import io.zeebe.client.ZeebeClient;
import ru.atconsulting.tele2.zeebeclient.api.ZeebeClientService;

/**
 * @author Grigoreva Irina {@literal <igrigorieva@at-consulting.ru>}
 */
public class Module extends AbstractModule implements ServiceGuiceSupport {

    @Override
    protected void configure() {
        bindService(ZeebeClientService.class, ZeebeClientServiceImpl.class);
        bind(PickupRegistrator.class);
        bind(DeliveryRegistrator.class);
    }

    /**
     * Zeebe client provider.
     * @param config {@link Config}
     * @return {@link ZeebeClient}
     */
    @Provides
    public ZeebeClient provideZeebeClient(Config config) {
        return ZeebeClient.newClientBuilder()
                .brokerContactPoint(config.getString("zeebe-broker.contact-point"))
                .build();
    }
}
