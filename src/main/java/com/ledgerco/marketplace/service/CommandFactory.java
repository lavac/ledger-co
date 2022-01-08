package com.ledgerco.marketplace.service;

import com.ledgerco.marketplace.exception.InvalidInputCommandException;
import com.ledgerco.marketplace.model.CommandType;

public class CommandFactory {
  public static Command getCommand(String command) throws InvalidInputCommandException {
    if (CommandType.LOAN.name().equalsIgnoreCase(command)) {
      return new Loan();
    } else if (CommandType.BALANCE.name().equalsIgnoreCase(command)) {
      return new Balance();
    }
    else if (CommandType.PAYMENT.name().equalsIgnoreCase(command)) {
      return new Payment();
    }
    else throw new InvalidInputCommandException(command);
  }
}
