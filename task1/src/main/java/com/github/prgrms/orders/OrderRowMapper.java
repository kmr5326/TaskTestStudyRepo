package com.github.prgrms.orders;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setSeq(rs.getLong("seq"));
        order.setUserId(rs.getLong("user_seq"));
        order.setProductId(rs.getLong("product_seq"));
        order.setReviewId(rs.getLong("review_seq"));
        order.setState(OrderState.valueOf(rs.getString("state")));
        order.setRequestMsg(rs.getString("request_msg"));
        order.setRejectMsg(rs.getString("reject_msg"));
        order.setCompletedAt(rs.getTimestamp("completed_at") != null ? rs.getTimestamp("completed_at").toLocalDateTime() : null);
        order.setRejectedAt(rs.getTimestamp("rejected_at") != null ? rs.getTimestamp("rejected_at").toLocalDateTime() : null);
        order.setCreateAt(rs.getTimestamp("create_at").toLocalDateTime());
        return order;
    }
}
