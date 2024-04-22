package com.github.prgrms.orders;

public class OrderRejectRequest {
    private String message;

    public OrderRejectRequest() {
    }

    public String getMessage() {
        return message;
    }

    public OrderRejectRequest(String message) {
        this.message = message;
    }
}
