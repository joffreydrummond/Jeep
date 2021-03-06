package com.jed.jeep.service;

import com.jed.jeep.dao.JeepOrderDao;
import com.jed.jeep.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class DefaultJeepOrderService implements JeepOrderService {
  @Autowired private JeepOrderDao jeepOrderDao;



  /**
   * @param orderRequest
   * @return
   */
  private List<Option> getOption(OrderRequest orderRequest) {
    return jeepOrderDao.fetchOptions(orderRequest.getOptions());
  }

  /**
   * @param orderRequest
   * @return
   */
  protected Tire getTire(OrderRequest orderRequest) {
    return jeepOrderDao
        .fetchTire(orderRequest.getTire())
        .orElseThrow(
            () ->
                new NoSuchElementException(
                    "Tire with ID=" + orderRequest.getTire() + " was not found"));
  }

  /**
   * @param orderRequest
   * @return
   */
  protected Engine getEngine(OrderRequest orderRequest) {
    return jeepOrderDao
        .fetchEngine(orderRequest.getEngine())
        .orElseThrow(
            () ->
                new NoSuchElementException(
                    "Engine with ID=" + orderRequest.getEngine() + " was not found"));
  }

  /**
   * @param orderRequest
   * @return
   */
  protected Color getColor(OrderRequest orderRequest) {
    return jeepOrderDao
        .fetchColor(orderRequest.getColor())
        .orElseThrow(
            () ->
                new NoSuchElementException(
                    "Color with ID=" + orderRequest.getColor() + " was not found"));
  }

  /**
   * @param orderRequest
   * @return
   */
  protected Jeep getModel(OrderRequest orderRequest) {
    return jeepOrderDao
        .fetchModel(orderRequest.getModel(), orderRequest.getTrim(), orderRequest.getDoors())
        .orElseThrow(
            () ->
                new NoSuchElementException(
                    "Model with ID="
                        + orderRequest.getModel()
                        + ", trim="
                        + orderRequest.getTrim()
                        + orderRequest.getDoors()
                        + " was not found"));
  }

  /**
   * @param orderRequest
   * @return
   */
  protected Customer getCustomer(OrderRequest orderRequest) {
    return jeepOrderDao
        .fetchCustomer(orderRequest.getCustomer())
        .orElseThrow(
            () ->
                new NoSuchElementException(
                    "Customer with ID=" + orderRequest.getCustomer() + " was not found"));
  }

  @Transactional
  @Override
  public Order createOrder(OrderRequest orderRequest) {
    log.info("I am in createOrder() in the service");
    Customer customer = getCustomer(orderRequest);
    Jeep jeep = getModel(orderRequest);
    Color color = getColor(orderRequest);
    Engine engine = getEngine(orderRequest);
    Tire tire = getTire(orderRequest);

    List<Option> options = getOption(orderRequest);

    BigDecimal price =
        jeep.getBasePrice().add(color.getPrice()).add(engine.getPrice()).add(tire.getPrice());

    for (Option option : options) {
      price.add(option.getPrice());
    }

    return jeepOrderDao.saveOrder(customer, jeep, color, engine, tire, price, options);
  }
}
