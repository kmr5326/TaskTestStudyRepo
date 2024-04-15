package com.github.prgrms.orders;

import java.time.LocalDateTime;
import java.util.Objects;

import com.github.prgrms.products.Product;
import com.github.prgrms.users.User;

import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

public class Review {
    private final Long seq;
    private final User user;
    private final Product product;
    private String content;
    private final LocalDateTime createAt; 

    public Review(Long seq,User user,Product product,String content, LocalDateTime createAt){
        this.seq=seq;
        this.user=user;
        this.product=product;
        this.content=content;
        this.createAt = defaultIfNull(createAt, now());
    }
}
