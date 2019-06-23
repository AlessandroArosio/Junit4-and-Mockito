package com.mockito.exercise.order.bo;

import com.mockito.exercise.order.bo.exception.BOException;
import com.mockito.exercise.order.dao.OrderDAO;
import com.mockito.exercise.order.dto.Order;

import java.sql.SQLException;

public class OrderBOImpl implements OrderBO {
  private OrderDAO dao;

  public OrderDAO getDao() {
    return dao;
  }

  public void setDao(OrderDAO dao) {
    this.dao = dao;
  }


  @Override
  public boolean placeOrder(Order order) throws BOException {
    try {
      int result = dao.create(order);
      if (result == 0)
        return false;
    } catch (SQLException e) {
      throw new BOException(e);
    }
    return true;
  }

  @Override
  public boolean cancelOrder(int id) throws BOException {
    try {
      Order order = dao.read(id);
      order.setStatus("canceled");
      int result = dao.update(order);
      if (result == 0)
        return false;
    } catch (SQLException e) {
      throw new BOException(e);
    }
    return true;
  }

  @Override
  public boolean deleteOrder(int id) throws BOException {
    try {
      int result = dao.delete(id);
      if (result == 0)
        return false;
    } catch (SQLException e) {
      throw new BOException(e);
    }
    return true;
  }
}
