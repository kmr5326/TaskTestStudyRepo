package com.github.prgrms.orders;

import java.time.LocalDateTime;

public class Orders {
    private final Long seq;
    private Long userId;
    private Long productId;
    private Long reviewId;
    private OrderState state;
    private String requestMsg;
    private String rejectMsg;
    private LocalDateTime completedAt;
    private LocalDateTime rejectedAt;
    private final LocalDateTime createAt;

    public Orders(){
        this.seq=null;
        this.createAt=null;
    }
    public Orders(Long seq, Long userId, Long productId, Long reviewId, OrderState state, String requestMsg, String rejectMsg, LocalDateTime completedAt, LocalDateTime rejectedAt, LocalDateTime createAt) {
        this.seq = seq;
        this.userId = userId;
        this.productId = productId;
        this.reviewId = reviewId;
        this.state = state;
        this.requestMsg = requestMsg;
        this.rejectMsg = rejectMsg;
        this.completedAt = completedAt;
        this.rejectedAt = rejectedAt;
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

    public Long getReviewId() {
        return reviewId;
    }

    public OrderState getState() {
        return state;
    }

    public String getRequestMsg() {
        return requestMsg;
    }

    public String getRejectMsg() {
        return rejectMsg;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public LocalDateTime getRejectedAt() {
        return rejectedAt;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    static public class Builder{
        private Long seq;
        private Long userId;
        private Long productId;
        private Long reviewId;
        private OrderState state;
        private String requestMsg;
        private String rejectMsg;
        private LocalDateTime completedAt;
        private LocalDateTime rejectedAt;
        private LocalDateTime createAt;

        public Builder() {
        }

        public Builder seq(Long seq){
            this.seq=seq;
            return this;
        }

        public Builder userId(Long userId){
            this.userId = userId;
            return this;
        }

        public Builder productId(Long productId){
            this.productId = productId;
            return this;
        }

        public Builder reviewId(Long reviewId){
            this.reviewId = reviewId;
            return this;
        }

        public Builder state(OrderState state){
            this.state = state;
            return this;
        }

        public Builder requestMsg(String requestMsg){
            this.requestMsg = requestMsg;
            return this;
        }

        public Builder rejectMsg(String rejectMsg){
            this.rejectMsg = rejectMsg;
            return this;
        }

        public Builder completedAt(LocalDateTime completedAt){
            this.completedAt = completedAt;
            return this;
        }

        public Builder rejectedAt(LocalDateTime rejectedAt){
            this.rejectedAt = rejectedAt;
            return this;
        }

        public Builder createAt(LocalDateTime createAt){
            this.createAt = createAt;
            return this;
        }

        public Orders build(){
            return new Orders(
                    seq,
                    userId,
                    productId,
                    reviewId,
                    state,
                    requestMsg,
                    rejectMsg,
                    completedAt,
                    rejectedAt,
                    createAt
            );
        }
    }
}
