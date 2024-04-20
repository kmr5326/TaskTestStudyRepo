package com.github.prgrms.orders;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class OrderDto {
    private Long seq;
    private Long productId;
    private ReviewDto review;
    private OrderState state;
    private String requestMessage;
    private String rejectMessage;
    private LocalDateTime completedAt;
    private LocalDateTime rejectedAt;
    private LocalDateTime createAt;

    public OrderDto(Long seq, Long productId, ReviewDto review, OrderState state, String requestMessage, String rejectMessage, LocalDateTime completedAt, LocalDateTime rejectedAt, LocalDateTime createAt) {
        this.seq = seq;
        this.productId = productId;
        this.review = review;
        this.state = state;
        this.requestMessage = requestMessage;
        this.rejectMessage = rejectMessage;
        this.completedAt = completedAt;
        this.rejectedAt = rejectedAt;
        this.createAt = createAt;
    }

    public OrderDto(Orders order) {
        this(
                order.getSeq(),
                order.getProductId(),
                null,
                order.getState(),
                order.getRequestMsg(),
                order.getRejectMsg(),
                order.getCompletedAt(),
                order.getRejectedAt(),
                order.getCompletedAt()
        );
    }

    public OrderDto(Orders order, ReviewDto reviewDto){
        this(
                order.getSeq(),
                order.getProductId(),
                reviewDto,
                order.getState(),
                order.getRequestMsg(),
                order.getRejectMsg(),
                order.getCompletedAt(),
                order.getRejectedAt(),
                order.getCompletedAt()
        );
    }

    public void setReview(ReviewDto review) {
        this.review = review;
    }
}
