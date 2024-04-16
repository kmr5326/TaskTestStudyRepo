package com.github.prgrms.orders;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    Optional<Review> findById(long id);
    List<Review> findAll();
    Review save(Review review);

    Optional<Review> findByProductId(long id);
}
