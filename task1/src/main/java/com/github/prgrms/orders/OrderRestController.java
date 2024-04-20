package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import com.github.prgrms.errors.NotFoundException;
import com.github.prgrms.products.ProductDto;
import com.github.prgrms.utils.ApiUtils;
import com.github.prgrms.utils.ApiUtils.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.prgrms.utils.ApiUtils.success;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api/orders")
public class OrderRestController {
    // TODO findAll, findById, accept, reject, shipping, complete 메소드 구현이 필요합니다.
    private final OrderService orderService;
    private final ReviewService reviewService;

    public OrderRestController(OrderService orderService,ReviewService reviewService) {
        this.reviewService= reviewService;
        this.orderService = orderService;
    }

    @GetMapping("")
    public ApiResult<List<OrderDto>> findAll(Pageable pageable) {
        List<Orders> orders=orderService.findAll(pageable);
        List<OrderDto> orderDtos=new ArrayList<>();
        for(Orders order:orders){
            Optional<Review> review=reviewService.findById(order.getReviewId());
//            if(!review.isPresent())throw new NotFoundException("Could not found review for " + order.getReviewId());
            if(!review.isPresent()){
                orderDtos.add(new OrderDto(order));
                continue;
            }
            orderDtos.add(new OrderDto(order,new ReviewDto(review.get())));
        }
        return success(orderDtos);
//        return success(orderService.findAll(pageable).stream()
//                .map(OrderDto::new)
//                .collect(toList()));
    }

    @GetMapping("/{id}")
    public ApiResult<OrderDto> findById(@PathVariable("id") Long orderId){
        Optional<Orders> orders=orderService.findById(orderId);
        if(orders.isPresent()){
            Optional<Review> review=reviewService.findById(orders.get().getReviewId());
            if(!review.isPresent()){
                return success(new OrderDto(orders.get()));
            }
            return success(new OrderDto(orders.get(),new ReviewDto(review.get())));
        }
        else throw new NotFoundException("Could not found order for " + orderId);
    }

}