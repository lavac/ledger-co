package com.ledgerco.marketplace.service;

import com.ledgerco.marketplace.exception.IllegalInputException;
import com.ledgerco.marketplace.model.UserLoanDetails;

import java.util.Map;

public abstract class Command {
  public abstract Map<String, UserLoanDetails> execute(Map<String, UserLoanDetails> userLoanDetails, String input) throws IllegalInputException;
}
