package com.mockito.exercise.order.bo.exception;

import java.sql.SQLException;

public class BOException extends Exception {

  public BOException(SQLException e) {
    super(e);
  }
}
