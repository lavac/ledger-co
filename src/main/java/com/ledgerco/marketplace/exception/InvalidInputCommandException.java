package com.ledgerco.marketplace.exception;

public class InvalidInputCommandException extends RuntimeException {

  public String message;

  public InvalidInputCommandException(String command) {
    this.message = String.format("%s command is not valid: valid commands are LOAN, BALANCE and PAYMENT", command);
  }

  public String getMessage() {
    return this.message;
  }
}
