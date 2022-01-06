package com.ledgerco.marketplace;

import java.util.LinkedHashMap;
import java.util.Map;

public class Payment extends Command {

  @Override
  public Map<String, UserLoanDetails> handle(Map<String, UserLoanDetails> userLoanDetails, String input) {
    String bank =  input.split("\\s+")[1];
    String user =  input.split("\\s+")[2];
    Integer lumpsum = Integer.parseInt(input.split("\\s+")[3]);
    Integer emi = Integer.parseInt(input.split("\\s+")[4]);

    String userBankIdentifier = bank + "_" + user;

    UserLoanDetails userLoanDetails1 = userLoanDetails.get(userBankIdentifier);
    Integer remainingTotal = userLoanDetails1.getRemainingTotalValue() - (emi * userLoanDetails1.getEmi() + lumpsum);
    LinkedHashMap<Integer, Integer> lumpSumPaymentTracker = userLoanDetails1.getLumpSumPaymentTracker();
    userLoanDetails1.setRemainingTotalValue(remainingTotal);
    // TODO - no need of this ah?
    lumpSumPaymentTracker.put(emi, remainingTotal);
    userLoanDetails1.setLumpSumPaymentTracker(lumpSumPaymentTracker);
    // TODO - no need of this ah?
    userLoanDetails.put(userBankIdentifier, userLoanDetails1);
    System.out.println("userLoanDetails map is ---- " + userLoanDetails.entrySet());
    return userLoanDetails;
  }
}
