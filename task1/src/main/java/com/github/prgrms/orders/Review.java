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
}
