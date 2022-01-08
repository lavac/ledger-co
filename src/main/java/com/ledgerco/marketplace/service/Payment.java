package com.ledgerco.marketplace.service;

import com.ledgerco.marketplace.exception.IllegalInputException;
import com.ledgerco.marketplace.model.UserLoanDetails;

import java.util.Map;

public class Payment extends Command {

  @Override
  public Map<String, UserLoanDetails> execute(Map<String, UserLoanDetails> userLoanDetailsMap, String input) throws IllegalInputException {
    String[] inputStrings = input.split("\\s+");
    Integer lumpSumAmount = Integer.parseInt(inputStrings[3]);
    Integer requestedEmi = Integer.parseInt(inputStrings[4]);

    String userBankIdentifier = UserLoanDetails.getUserBankIdentifier(inputStrings[1], inputStrings[2]);

    UserLoanDetails userLoanDetails = userLoanDetailsMap.get(userBankIdentifier);

    if (userLoanDetails == null)
      throw new IllegalInputException(String.format("user:%s does not have account in bank:%s",
          inputStrings[2], inputStrings[1]));

    userLoanDetails.update(requestedEmi, lumpSumAmount);
    return userLoanDetailsMap;
  }
}
