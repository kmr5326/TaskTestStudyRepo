package com.github.prgrms.orders;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRowMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
        Review review=new Review.Builder()
                .seq(rs.getLong("seq"))
                .userId(rs.getLong("user_seq"))
                .productId(rs.getLong("product_seq"))
                .content(rs.getString("content"))
                .createAt(rs.getTimestamp("create_at").toLocalDateTime())
                .build();
        return review;
    }
}
