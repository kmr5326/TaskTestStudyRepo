package com.github.prgrms.orders;

import com.github.prgrms.products.Product;
import com.github.prgrms.products.ProductService;
import com.github.prgrms.users.Email;
import com.github.prgrms.users.User;
import com.github.prgrms.users.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
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
        List<Review> results = jdbcTemplate.query(
                "SELECT * FROM reviews WHERE seq=?",
                new ReviewRowMapper(),
                id
        );
        return results.isEmpty()?Optional.empty():Optional.of(results.get(0));
    }

    @Override
    public List<Review> findAll() {
        return null;
    }

    @Override
    @Transactional
    public Review save(Review review) {
        String sql="INSERT INTO reviews(user_seq, product_seq, content, create_at) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(sql,
//                review.getUserId(),
//                review.getProductId(),
//                review.getContent(),
//                review.getCreateAt(),
//                keyHolder);
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "seq" });
            ps.setLong(1, review.getUserId());
            ps.setLong(2, review.getProductId());
            ps.setString(3, review.getContent());
            ps.setObject(4, review.getCreateAt());
            return ps;
        }, keyHolder);
        Review savedReview;
        if (keyHolder.getKey() != null) {
            Long generatedSeq = keyHolder.getKey().longValue();
            savedReview=new Review(generatedSeq,
                    review.getUserId(),
                    review.getProductId(),
                    review.getContent(),
                    review.getCreateAt());
        } else {
            throw new IllegalStateException("Failed to retrieve generated key");
        }
        return savedReview;
    }

    @Override
    public Optional<Review> findByProductId(long id) {
//        String sql="SELECT * FROM reivews"
        return Optional.empty();
    }

//    static RowMapper<Review> mapper = (rs, rowNum) ->
//            new Review(
//                    rs.getLong("seq"),
//                    rs.getObject("user")),
//                    rs.getObject("product"),
//                    rs.getString("content"),
//                    dateTimeOf(rs.getTimestamp("create_at"))
//            )
}
