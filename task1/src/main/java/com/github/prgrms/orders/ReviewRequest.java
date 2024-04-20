package com.github.prgrms.orders;

public class ReviewRequest {
    private String content;

    public ReviewRequest() {
    }

    public ReviewRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
