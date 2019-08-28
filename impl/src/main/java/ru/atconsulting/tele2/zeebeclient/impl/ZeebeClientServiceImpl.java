package ru.atconsulting.tele2.zeebeclient.impl;

import io.zeebe.client.ZeebeClient;
import ru.atconsulting.tele2.zeebeclient.api.Order;
import ru.atconsulting.tele2.zeebeclient.api.ZeebeClientService;

import javax.inject.Inject;

/**
 * @author Grigoreva Irina {@literal <igrigorieva@at-consulting.ru>}
 */
public class ZeebeClientServiceImpl implements ZeebeClientService {

    DeliveryRegistrator deliveryRegistrator;
    PickupRegistrator pickupRegistrator;
    ZeebeClient client;

    @Inject
    public ZeebeClientServiceImpl(ZeebeClient client, PickupRegistrator pickupRegistrator, DeliveryRegistrator deliveryRegistrator) {
        this.client = client;
        this.pickupRegistrator = pickupRegistrator;
        this.deliveryRegistrator = deliveryRegistrator;
        client.newDeployCommand()
                .addResourceFromClasspath("order-process.bpmn")
                .send()
                .join();
        Order order = new Order();
        order.setOrderId("12345");
        order.setDelivery(false);
        order.setStatus(Order.Status.CREATED);
        createTestOrderInstance(order);
    }

    private void createTestOrderInstance(Order order) {
        client.newCreateInstanceCommand()
                .bpmnProcessId("order-process")
                .latestVersion()
                .variables(order)
                .send()
                .join();
    }

}
