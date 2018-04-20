package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class ProductDao  {
    private DataSource dataSource;
    public ProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Product get(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select * from product where id = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            product = new Product();
            product.setId(resultSet.getLong("id"));
            product.setTitle(resultSet.getString("title"));
            product.setPrice(resultSet.getInt("price"));
        } finally {
            if(resultSet!=null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(preparedStatement!=null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(connection!=null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return product;
    }

    public Long insert(Product product) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        long id;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("insert into product(title, price) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            id = resultSet.getLong(1);
        } finally {
            if(resultSet!=null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(preparedStatement!=null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(connection!=null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }


        return id;
    }


}
