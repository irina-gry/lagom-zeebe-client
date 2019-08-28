package ru.atconsulting.tele2.zeebeclient.api;

import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;

import static com.lightbend.lagom.javadsl.api.Service.named;

/**
 * @author Grigoreva Irina {@literal <igrigorieva@at-consulting.ru>}
 */
public interface ZeebeClientService extends Service {

    @Override
    default Descriptor descriptor() {
        return named("zeebe-client")
                .withCalls().withAutoAcl(true);
    }
}
