package com.example.demo.dao;

import com.example.demo.entity.Product;
import com.example.demo.utils.MySqlUtils;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDao {

    public int insert(Product product) throws Exception {
        Statement statement = MySqlUtils.getConnection().createStatement();
        String sql = "INSERT INTO product (id,name,price) values ('" + product.getId() + "','"+ product.getName() +"','"+ product.getPrice().toString() +"')";
        return statement.executeUpdate(sql);
    }

    public void insertByPreparedBatch(List<Product> products) throws Exception {
        String sql = "INSERT INTO product (id,name,price) values (?,?,?)";
        PreparedStatement statement = MySqlUtils.getConnection().prepareStatement(sql);
        for (Product product : products) {
            statement.setLong(1, product.getId());
            statement.setString(2, product.getName());
            statement.setBigDecimal(3, product.getPrice());
            statement.addBatch();
        }
        statement.executeBatch();
    }

    public int delete(Long id) throws Exception {
        Statement statement = MySqlUtils.getConnection().createStatement();
        return statement.executeUpdate("DELETE FROM product WHERE id = "+ id);
    }

    public void deleteByPreparedBatch(List<Long> ids) throws Exception {
        String sql = "DELETE FROM product WHERE id = ?";
        PreparedStatement statement = MySqlUtils.getConnection().prepareStatement(sql);
        for (Long id : ids) {
            statement.setLong(1, id);
            statement.addBatch();
        }
        statement.executeBatch();
    }

    public int update(Product product) throws Exception {
        Statement statement = MySqlUtils.getConnection().createStatement();
        String sql = "UPDATE product SET name = '"+ product.getName() +"', price = '"+ product.getPrice() +"' WHERE id = " + product.getId();
        return statement.executeUpdate(sql);
    }

    public List<Product> select() throws SQLException {
        Statement statement = MySqlUtils.getConnection().createStatement();
        statement.executeQuery("SELECT * FROM product");
        final ResultSet resultSet = statement.getResultSet();
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            products.add(new Product(resultSet.getLong(1),resultSet.getString(2),resultSet.getBigDecimal(3)));
        }
        return products;
    }
}
