package com.github.prgrms.orders;

import com.github.prgrms.security.JwtAuthentication;
import com.github.prgrms.utils.ApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.github.prgrms.utils.ApiUtils.*;

@RestController
@RequestMapping("api/orders")
public class ReviewRestController {
    // TODO review 메소드 구현이 필요합니다.
    private final AuthenticationManager authenticationManager;
    private final ReviewService reviewService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    public ReviewRestController(AuthenticationManager authenticationManager, ReviewService reviewService) {
        this.authenticationManager = authenticationManager;
        this.reviewService = reviewService;
    }

    @PostMapping("/{id}/review")
    public ApiResult<ReviewDto> review(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @PathVariable("id") Long orderId,
            @RequestBody ReviewRequest reviewRequest
    ) {
        log.info("orderId: {}, content: {}",orderId,reviewRequest.getContent());
        ReviewDto reviewDto = reviewService.createReview(reviewRequest, authentication.id, orderId);
        return success(reviewDto);
    }
}