package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import com.github.prgrms.errors.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Orders> findById(Long orderId) {
        checkNotNull(orderId, "orderId must be provided");

        return orderRepository.findById(orderId);
    }

    @Transactional(readOnly = true)
    public List<Orders> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public void saveReview(Long id,Long reviewId){
        orderRepository.saveReview(id,reviewId);
    }

    public Boolean accept(Long orderId){
        Optional<Orders> orders=orderRepository.findById(orderId);
        if(orders.isPresent()){
            if(orders.get().getState()!=OrderState.REQUESTED)return Boolean.FALSE;
            else{
                orderRepository.acceptState(orderId);
                return Boolean.TRUE;
            }
        }
        else throw new NotFoundException("Could not found order for " + orderId);
    }

    public Boolean reject(Long orderId, OrderRejectRequest orderRejectRequest){
        Optional<Orders> orders=orderRepository.findById(orderId);
        if(orders.isPresent()){
            if(orders.get().getState()!=OrderState.REQUESTED)return Boolean.FALSE;
            else{
                orderRepository.rejectState(orderId,orderRejectRequest.getMessage());
                return Boolean.TRUE;
            }
        }
        else throw new NotFoundException("Could not found order for " + orderId);
    }
}
