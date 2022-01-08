package com.ledgerco.marketplace.exception;

public class IllegalInputException extends RuntimeException {

  public String message;

  public IllegalInputException(String message) {
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }
}
