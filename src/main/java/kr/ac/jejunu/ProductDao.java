package kr.ac.jejunu;

import java.sql.*;

public class ProductDao  {
    private JdbcContext jdbcContext;
    private String sql;

    public ProductDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public Product get(Long id) throws SQLException {
        StatementStrategy statementStrategy= connection -> {
            sql = "select * from product where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            Object []params=new Object[]{id};
            for(int i=0; i<params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            return preparedStatement;
        };
        return jdbcContext.jdbcContextForGet(statementStrategy);
    }

    public Long insert(Product product) throws SQLException{
        StatementStrategy statementStrategy=connection -> {
            sql = "insert into product(title, price) values(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(this.sql, Statement.RETURN_GENERATED_KEYS);
            Object []params=new Object[]{product.getTitle(), product.getPrice()};
            for(int i=0; i<params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            return preparedStatement;
        };
        return jdbcContext.jdbcContextForInsert(statementStrategy);
    }

    public void update(Product product) throws SQLException {
        StatementStrategy statementStrategy= connection -> {
            sql = "update product set title=?, price=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(this.sql);
            Object []params=new Object[]{product.getTitle(), product.getPrice(), product.getId()};
            for(int i=0; i<params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            return preparedStatement;
        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Long id) throws SQLException {
        StatementStrategy statementStrategy= connection -> {
            sql = "delete from product where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            Object []params=new Object[]{id};
            for(int i=0; i<params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            return preparedStatement;
        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }


}
