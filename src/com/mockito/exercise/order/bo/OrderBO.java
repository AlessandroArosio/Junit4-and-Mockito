package com.mockito.exercise.order.bo;

import com.mockito.exercise.order.bo.exception.BOException;
import com.mockito.exercise.order.dto.Order;

public interface OrderBO {
  boolean placeOrder(Order order) throws BOException;
  boolean cancelOrder(int id) throws BOException;
  boolean deleteOrder(int id) throws BOException;
}
