package com.github.prgrms.orders;

import com.github.prgrms.products.Product;
import com.github.prgrms.products.ProductService;
import com.github.prgrms.users.Email;
import com.github.prgrms.users.User;
import com.github.prgrms.users.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.github.prgrms.utils.DateTimeUtils.dateTimeOf;
import static java.util.Optional.ofNullable;

@Repository
public class JdbcReviewRepository implements ReviewRepository{
    private final JdbcTemplate jdbcTemplate;
    private final UserService userService;
    private final ProductService productService;

    public JdbcReviewRepository(JdbcTemplate jdbcTemplate,UserService userService,ProductService productService){
        this.jdbcTemplate=jdbcTemplate;
        this.userService=userService;
        this.productService=productService;
    }

    @Override
    public Optional<Review> findById(long id) {
        List<User> results = jdbcTemplate.query(
                "SELECT * FROM users WHERE seq=?",
                mapper,
                id
        );
        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    @Override
    public List<Review> findAll() {
        return null;
    }

    @Override
    public Review save(Review review) {
        String sql="INSERT INTO reviews(user_seq, product_seq, content, create_at) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                review.getUser().getSeq(),
                review.getProduct().getSeq(),
                review.getContent(),
                review.getCreateAt());
        return review;
    }

    @Override
    public Optional<Review> findByProductId(long id) {
        String sql="SELECT * FROM reivews"
        return Optional.empty();
    }

    static RowMapper<Review> mapper = (rs, rowNum) ->
            new Review(
                    rs.getLong("seq"),
                    rs.getObject("user")),
                    rs.getObject("product"),
                    rs.getString("content"),
                    dateTimeOf(rs.getTimestamp("create_at"))
            )
}
