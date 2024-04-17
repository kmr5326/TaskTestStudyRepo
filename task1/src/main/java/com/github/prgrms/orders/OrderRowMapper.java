package com.github.prgrms.orders;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Orders> {
    @Override
    public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
        Orders order= new Orders.Builder()
                .seq(rs.getLong("seq"))
                .userId(rs.getLong("user_seq"))
                .productId(rs.getLong("product_seq"))
                .reviewId(rs.getLong("review_seq"))
                .state(OrderState.valueOf(rs.getString("state")))
                .requestMsg(rs.getString("request_msg"))
                .rejectMsg(rs.getString("reject_msg"))
                .completedAt(rs.getTimestamp("completed_at") != null ? rs.getTimestamp("completed_at").toLocalDateTime() : null)
                .rejectedAt(rs.getTimestamp("rejected_at") != null ? rs.getTimestamp("rejected_at").toLocalDateTime() : null)
                .completedAt(rs.getTimestamp("create_at").toLocalDateTime())
                .build();
        return order;
    }
}
