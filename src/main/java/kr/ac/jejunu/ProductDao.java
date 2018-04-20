package kr.ac.jejunu;

import java.sql.*;

public class ProductDao  {
    private ConnectionMaker connectionMaker=new JejuConnectionMaker();
    public Product get(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?");
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setTitle(resultSet.getString("title"));
        product.setPrice(resultSet.getInt("price"));

        //자원을 해지한다.
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return product;
    }

    public Long insert(Product product) throws SQLException, ClassNotFoundException {
        Connection connection = connectionMaker.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("insert into product(title, price) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, product.getTitle());
        preparedStatement.setInt(2, product.getPrice());

         preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        long id = resultSet.getLong(1);

        //자원을 해지한다.
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return id;
    }


}
