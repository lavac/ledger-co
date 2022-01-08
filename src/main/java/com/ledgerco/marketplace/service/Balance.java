package com.ledgerco.marketplace.service;

import com.ledgerco.marketplace.exception.IllegalInputException;
import com.ledgerco.marketplace.model.UserLoanDetails;

import java.util.Map;

public class Balance extends Command {

  @Override
  public Map<String, UserLoanDetails> execute(Map<String, UserLoanDetails> userLoanDetailsMap, String input) throws IllegalInputException {
    String[] inputStrings = input.split("\\s+");
    Integer requestedEmi = Integer.parseInt(inputStrings[3]);

    String userBankIdentifier = UserLoanDetails.getUserBankIdentifier(inputStrings[1], inputStrings[2]);
    UserLoanDetails userLoanDetails = userLoanDetailsMap.get(userBankIdentifier);

    if (userLoanDetails == null)
      throw new IllegalInputException(String.format("user:%s does not have account in bank:%s",
          inputStrings[2], inputStrings[1]));

    Integer remainingAmountAfterTheRequestedEmi = userLoanDetails.getRemainingAmountAfterTheRequestedEmi(requestedEmi);
    Integer remainingBalanceAfterTheRequestedEmi = userLoanDetails.calculateTotalAmount() - remainingAmountAfterTheRequestedEmi;

    Integer remainingEmisAfterTheRequestedEmi = remainingAmountAfterTheRequestedEmi/userLoanDetails.getEmiAmount();

    if (remainingAmountAfterTheRequestedEmi % userLoanDetails.getEmiAmount() != 0)
      ++remainingEmisAfterTheRequestedEmi;

    System.out.println(userLoanDetails.getBank() + " " + userLoanDetails.getUser() + " "
        + remainingBalanceAfterTheRequestedEmi + " " + remainingEmisAfterTheRequestedEmi);
    return userLoanDetailsMap;
  }
}
