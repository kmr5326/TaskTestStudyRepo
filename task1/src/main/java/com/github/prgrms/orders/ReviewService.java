package com.github.prgrms.orders;

import com.github.prgrms.errors.DuplicateReviewException;
import com.github.prgrms.errors.NotCompletedOrderException;
import com.github.prgrms.errors.NotFoundException;
import com.github.prgrms.products.Product;
import com.github.prgrms.products.ProductRepository;
import com.github.prgrms.products.ProductService;
import com.github.prgrms.users.User;
import com.github.prgrms.users.UserRepository;
import com.github.prgrms.users.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final OrderService orderService;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public ReviewService(ReviewRepository reviewRepository, UserService userService, OrderService orderService) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.orderService = orderService;
    }

    public ReviewDto createReview(ReviewRequest reviewRequest, Long userId, Long orderId) {
        Optional<User> user = userService.findById(userId);
        Optional<Orders> order = orderService.findById(orderId);
        Orders orders;
        if (!order.isPresent()) throw new NotFoundException("Order Not found");
        else orders = order.get();
        log.info("orders: {}",orders.toString());
        if (orders.getReviewId() != 0) {
            throw new DuplicateReviewException(String.format("Could not write review for order %d because have already written",orderId));
        }
        if (orders.getState() != OrderState.COMPLETED) {
            throw new NotCompletedOrderException(String.format("Could not write review for order %d because state(%s) is not allowed", orderId,orders.getState().toString()));
        }
        Review review = new Review(
                userId,
                orders.getProductId(),
                reviewRequest.getContent(),
                LocalDateTime.now()
        );
        Review savedReview=reviewRepository.save(review);
        orderService.saveReview(orders.getSeq(),savedReview.getSeq());
        return new ReviewDto(savedReview);
    }

    @Transactional(readOnly = true)
    public Optional<Review> findById(Long reviewId) {
        checkNotNull(reviewId, "reviewId must be provided");

        return reviewRepository.findById(reviewId);
    }
}
