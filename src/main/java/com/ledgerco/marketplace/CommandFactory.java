package com.ledgerco.marketplace;

public class CommandFactory {
  static Command getCommand(String command) {
    if ("LOAN".equalsIgnoreCase(command)) {
      return new Loan();
    } else if ("BALANCE".equalsIgnoreCase(command)) {
      return new Balance();
    }

    else if ("PAYMENT".equalsIgnoreCase(command)) {
      return new Payment();
    }
    else return new Payment();
  }
}
