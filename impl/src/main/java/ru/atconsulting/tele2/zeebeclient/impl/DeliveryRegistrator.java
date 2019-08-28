package ru.atconsulting.tele2.zeebeclient.impl;

import io.zeebe.client.ZeebeClient;
import io.zeebe.client.api.response.ActivatedJob;
import io.zeebe.client.api.worker.JobClient;
import io.zeebe.client.api.worker.JobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.atconsulting.tele2.zeebeclient.api.Order;

import java.time.Duration;
import javax.inject.Inject;

/**
 * @author Grigoreva Irina {@literal <igrigorieva@at-consulting.ru>}
 */
public class DeliveryRegistrator implements JobHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryRegistrator.class);

    ZeebeClient client;

    @Inject
    public DeliveryRegistrator(ZeebeClient client) {
        this.client = client;
        client.newWorker()
                .jobType("delivery-registration")
                .handler(this)
                .timeout(Duration.ofMinutes(1))
                .open();
    }

    @Override
    public void handle(JobClient client, ActivatedJob job) {
        Order order = job.getVariablesAsType(Order.class);
        order.setStatus(Order.Status.REGISTERED);
        LOGGER.info("Delivery registered for order: {}", order.getOrderId());
        client.newCompleteCommand(job.getKey()).variables(order).send();
    }

}
