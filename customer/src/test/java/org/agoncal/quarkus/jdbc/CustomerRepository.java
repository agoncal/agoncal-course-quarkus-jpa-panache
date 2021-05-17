package org.agoncal.quarkus.jdbc;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;

@ApplicationScoped
public class CustomerRepository {

  @Inject
  DataSource dataSource;

  public void persist(Customer customer) throws SQLException {
    Connection conn = dataSource.getConnection();
    String sql = "INSERT INTO T_CUSTOMER (id, first_name, last_name, email, created_date) VALUES (?, ?, ?, ?, ?)";
    customer.setId((long) Math.abs(new Random().nextInt()));

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setLong(1, customer.getId());
      ps.setString(2, customer.getFirstName());
      ps.setString(3, customer.getLastName());
      ps.setString(4, customer.getEmail());
      ps.setTimestamp(5, Timestamp.from(customer.getCreatedDate()));
      ps.executeUpdate();
    }
    conn.commit();
    conn.close();
  }

  public Customer findById(Long id) throws SQLException {
    Connection conn = dataSource.getConnection();
    String sql = "SELECT id, first_name, last_name, email, created_date FROM T_CUSTOMER WHERE id = ?";
    Customer customer = new Customer();

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setLong(1, id);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        customer.setId(rs.getLong(1));
        customer.setFirstName(rs.getString(2));
        customer.setLastName(rs.getString(3));
        customer.setEmail(rs.getString(4));
        customer.setCreatedDate(rs.getTimestamp(5).toInstant());
      }
    }
    conn.close();
    return customer;
  }
}
