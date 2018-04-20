package kr.ac.jejunu;

import java.sql.*;

public class ProductDao  {
    private JdbcContext jdbcContext;

    public ProductDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public Product get(Long id) throws SQLException {
        String sql = "select * from product where id = ?";
        Object []params=new Object[]{id};
        return jdbcContext.queryForObject(sql, params);
    }

    public Long insert(Product product) throws SQLException{
        String sql = "insert into product(title, price) values(?, ?)";
        Object []params=new Object[]{product.getTitle(), product.getPrice()};
        return jdbcContext.insert(sql, params);
    }

    public void update(Product product) throws SQLException {
        String sql = "update product set title=?, price=? where id=?";
        Object []params=new Object[]{product.getTitle(), product.getPrice(), product.getId()};
        jdbcContext.update(sql, params);
    }

    public void delete(Long id) throws SQLException {
        String sql = "delete from product where id=?";
        Object []params=new Object[]{id};
        jdbcContext.update(sql, params);
    }


}
