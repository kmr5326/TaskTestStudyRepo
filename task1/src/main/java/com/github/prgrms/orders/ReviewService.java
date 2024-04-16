package com.github.prgrms.orders;

import com.github.prgrms.errors.NotFoundException;
import com.github.prgrms.products.Product;
import com.github.prgrms.products.ProductRepository;
import com.github.prgrms.products.ProductService;
import com.github.prgrms.users.User;
import com.github.prgrms.users.UserRepository;
import com.github.prgrms.users.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final OrderService orderService;

    public ReviewService(ReviewRepository reviewRepository, UserService userService, OrderService orderService) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.orderService = orderService;
    }

    public ReviewDto createReview(ReviewRequest reviewRequest, Long userId, Long orderId) {
        Optional<User> user = userService.findById(userId);
        Optional<Order>
        Review review = new Review(user.get(),
                product.get(),
                reviewRequest.getContent(),
                LocalDateTime.now());

        Review savedReview = reviewRepository.save(review);

    }
}
