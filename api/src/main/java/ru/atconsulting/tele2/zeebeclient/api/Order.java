package ru.atconsulting.tele2.zeebeclient.api;

/**
 * @author Grigoreva Irina {@literal <igrigorieva@at-consulting.ru>}
 */

public class Order {
    private String orderId;
    private Boolean delivery;
    private Status status;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }

    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(final Boolean delivery) {
        this.delivery = delivery;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Order status.
     */
    public enum Status {
        CREATED, REGISTERED
    }
}
