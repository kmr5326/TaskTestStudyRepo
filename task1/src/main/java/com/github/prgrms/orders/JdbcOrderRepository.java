package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Orders> findById(long id) {
        List<Orders> orders = jdbcTemplate.query(
                "SELECT * FROM orders WHERE seq = ?",
                new OrderRowMapper(),
                id
        );
        return orders.isEmpty() ? Optional.empty() : Optional.of(orders.get(0));
    }

    @Override
    public List<Orders> findAll(Pageable pageable) {
        String sql = "SELECT * FROM orders LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new Object[]{pageable.getSize(), pageable.getOffset()},
                new OrderRowMapper());

    }
}
