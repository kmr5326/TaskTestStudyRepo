package com.github.prgrms.orders;

import java.time.LocalDateTime;
import java.util.Objects;

import com.github.prgrms.products.Product;
import com.github.prgrms.users.User;

import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

public class Review {
    private final Long seq;
    private final Long userId;
    private final Long productId;
    private String content;
    private final LocalDateTime createAt;

    public Review(Long seq, Long userId, Long productId, String content, LocalDateTime createAt) {
        this.seq = seq;
        this.userId = userId;
        this.productId = productId;
        this.content = content;
        this.createAt = createAt;
    }

    public Review(Long userId, Long productId, String content, LocalDateTime createAt) {
        this.seq = null;
        this.userId = userId;
        this.productId = productId;
        this.content = content;
        this.createAt = createAt;
    }

    public Long getSeq() {
        return seq;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public static class Builder {
        private Long seq;
        private Long userId;
        private Long productId;
        private String content;
        private LocalDateTime createAt;

        public Builder() {
            this.createAt = LocalDateTime.now();
        }

        public Builder seq(Long seq) {
            this.seq = seq;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder productId(Long productId) {
            this.productId = productId;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public Review build() {
            return new Review(seq, userId, productId, content, createAt);
        }
    }
}
