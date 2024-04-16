package com.github.prgrms.orders;

import java.time.LocalDateTime;

public class ReviewDto {
    private Long seq;
    private Long productId;
    private String content;
    private LocalDateTime createAt;

    public ReviewDto(long seq, long productId, String content, LocalDateTime createAt) {
        this.seq = seq;
        this.productId = productId;
        this.content = content;
        this.createAt = createAt;
    }
}
