package com.github.prgrms.orders;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(long id);
    List<Order> findAll();
}
