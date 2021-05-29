package com.jed.jeep.dao;

import com.jed.jeep.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class DefaultJeepOrderDao implements JeepOrderDao {

  @Autowired private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Jeep> fetchJeeps(JeepModel model, String trim) {
    log.debug("DAO: model={}, trim={}", model, trim);

    String sql = "SELECT * FROM models WHERE model_id = :model_id AND trim_level= :trim_level";

    Map<String, Object> params = new HashMap<>();
    params.put("model_id", model.toString());
    params.put("trim_level", trim);

    return jdbcTemplate.query(
        sql,
        params,
        new RowMapper<Jeep>() {
          @Override
          public Jeep mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Jeep.builder()
                .basePrice(new BigDecimal(rs.getString("base_price")))
                .modelId(JeepModel.valueOf(rs.getString("model_id")))
                .modelPK(rs.getLong("model_pk"))
                .numDoors(rs.getInt("num_doors"))
                .trimLevel(rs.getString("trim_level"))
                .wheelSize(rs.getInt("wheel_size"))
                .build();
          }
        });
  }

  @Override
  public Customer fetchCustomer(String customer_id) {
    String sql = "SELECT * FROM customers WHERE customer_id=:customer_id";

    Map<String, Object> params = new HashMap<>();
    params.put("customer_id", customer_id);

    return jdbcTemplate.query(sql, params, new CustomerResultSetExtractor());
  }

  static class CustomerResultSetExtractor implements ResultSetExtractor<Customer> {
    public Customer extractData(ResultSet rs) throws SQLException, DataAccessException {
      rs.next();

      return Customer.builder()
          .customerId(rs.getString("customer_id"))
          .customerPK(rs.getLong("customer_pk"))
          .firstName(rs.getString("first_name"))
          .lastName(rs.getString("last_name"))
          .phone(rs.getString("phone"))
          .build();
    }
  }

  //    @Override
  //    public Order createOrder(OrderRequest orderRequest) {
  //      log.debug("Order={}", orderRequest);
  //        return null;
  //    }
}
