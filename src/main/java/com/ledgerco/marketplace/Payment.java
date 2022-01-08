package com.ledgerco.marketplace;

import java.util.LinkedHashMap;
import java.util.Map;

public class Payment extends Command {

  @Override
  public Map<String, UserLoanDetails> execute(Map<String, UserLoanDetails> userLoanDetailsMap, String input) {
    String[] inputStrings = input.split("\\s+");
    Integer lumpSum = Integer.parseInt(inputStrings[3]);
    Integer emi = Integer.parseInt(inputStrings[4]);

    String userBankIdentifier = UserLoanDetails.getUserBankIdentifier(inputStrings[1], inputStrings[2]);

    UserLoanDetails userLoanDetails = userLoanDetailsMap.get(userBankIdentifier);
    Integer remainingAmount = userLoanDetails.getRemainingAmount() - (emi * userLoanDetails.getEmiAmount() + lumpSum);
    LinkedHashMap<Integer, Integer> lumpSumPaymentTracker = userLoanDetails.getLumpSumPaymentTracker();
    userLoanDetails.setRemainingAmount(remainingAmount);
    // TODO - no need of this ah?
    lumpSumPaymentTracker.put(emi, remainingAmount);
    userLoanDetails.setLumpSumPaymentTracker(lumpSumPaymentTracker);
    // TODO - no need of this ah?
    userLoanDetailsMap.put(userBankIdentifier, userLoanDetails);
    return userLoanDetailsMap;
  }
}
