package com.ledgerco.marketplace.service;

import com.ledgerco.marketplace.exception.IllegalInputException;
import com.ledgerco.marketplace.model.UserLoanDetails;

import java.util.LinkedHashMap;
import java.util.Map;

public class Loan extends Command {

  @Override
  public Map<String, UserLoanDetails> execute(Map<String, UserLoanDetails> userLoanDetailsMap, String input) throws IllegalInputException {
    String inputStrings[] = input.split("\\s+");

    String bank = inputStrings[1];
    String user = inputStrings[2];
    Integer principal = Integer.parseInt(inputStrings[3]);
    Integer term = Integer.parseInt(inputStrings[4]);
    Integer rateOfInterest = Integer.parseInt(inputStrings[5]);
    String userBankIdentifier = UserLoanDetails.getUserBankIdentifier(bank, user);

    if(userLoanDetailsMap.containsKey(userBankIdentifier)) {
        throw new IllegalInputException(String.format("user:%s has already borrowed loan from the bank:%s", user, bank));
    }

    Integer totalAmount = getTotalAmount(principal, term, rateOfInterest);
    Integer emiAmount = getEmiAmount(totalAmount, term);

    UserLoanDetails userLoanDetails = new UserLoanDetails(bank,
        user,
        principal,
        term,
        rateOfInterest,
        totalAmount,
        emiAmount,
        new LinkedHashMap<>());

    userLoanDetailsMap.put(userBankIdentifier, userLoanDetails);
    return userLoanDetailsMap;
  }

  private int getEmiAmount(Integer totalValue, Integer term) {
    return Math.toIntExact(Math.round(totalValue/(term * 12.0)));
  }

  public Integer getTotalAmount(Integer principal, Integer term, Integer rateOfInterest) {
    return Math.toIntExact(Math.round((principal*term*rateOfInterest)/100.0)) + principal;
  }
}
