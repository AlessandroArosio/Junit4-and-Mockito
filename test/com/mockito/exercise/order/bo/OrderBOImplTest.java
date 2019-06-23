package com.mockito.exercise.order.bo;

import com.mockito.exercise.order.bo.exception.BOException;
import com.mockito.exercise.order.dao.OrderDAO;
import com.mockito.exercise.order.dto.Order;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Or;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OrderBOImplTest {

  @Mock
  OrderDAO dao;
  private OrderBOImpl bo;
  private Order order;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    bo = new OrderBOImpl();
    bo.setDao(dao);
    order = new Order();
  }

  @Test
  public void placeOrderTestCreateOrder() throws SQLException, BOException {
    when(dao.create(any(Order.class))).thenReturn(1);
    boolean result = bo.placeOrder(order);

    assertTrue(result);
    verify(dao, atLeastOnce()).create(order);
  }

  @Test
  public void placeOrderTestNotCreateOrder() throws SQLException, BOException {
    when(dao.create(order)).thenReturn(0);
    boolean result = bo.placeOrder(order);

    assertFalse(result);
    verify(dao).create(order);
  }

  @Test(expected = BOException.class)
  public void placeOrderTestThrowException() throws SQLException, BOException {
    when(dao.create(order)).thenThrow(SQLException.class);
    bo.placeOrder(order);
  }

  @Test
  public void cancelOrderTest() throws SQLException, BOException {
    Order order = new Order();
    when(dao.read(123)).thenReturn(order);
    when(dao.update(order)).thenReturn(1);
    boolean result = bo.cancelOrder(123);

    assertTrue(result);
    verify(dao).read(123);
    verify(dao).update(order);
  }

  @Test
  public void cancelOrderTestNotCancelled() throws SQLException, BOException {
    Order order = new Order();
    when(dao.read(123)).thenReturn(order);
    when(dao.update(order)).thenReturn(0);
    boolean result = bo.cancelOrder(123);

    assertFalse(result);
    verify(dao).read(123);
    verify(dao).update(order);
  }

  @Test(expected = BOException.class)
  public void cancelOrderTestThrowException() throws SQLException, BOException {
    when(dao.read(123)).thenThrow(SQLException.class);
    boolean result = bo.cancelOrder(123);
  }

  @Test(expected = BOException.class)
  public void cancelOrderTestThrowExceptionOnUpdate() throws SQLException, BOException {
    Order order = new Order();
    when(dao.read(123)).thenReturn(order);
    when(dao.update(order)).thenThrow(SQLException.class);
    boolean result = bo.cancelOrder(123);
  }

  @Test
  public void deleteOrderTest() throws SQLException, BOException {
    when(dao.delete(anyInt())).thenReturn(1);

    boolean result = bo.deleteOrder(123);

    assertTrue(result);
    verify(dao).delete(123);
  }

}