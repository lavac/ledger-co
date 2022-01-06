package com.ledgerco.marketplace;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Balance extends Command {

  @Override
  public Map<String, UserLoanDetails> handle(Map<String, UserLoanDetails> userLoanDetails, String input) {

    String bank =  input.split("\\s+")[1];
    String user =  input.split("\\s+")[2];
    Integer emi = Integer.parseInt( input.split("\\s+")[3]);

    String userBankIdentifier = bank + "_" + user;
    UserLoanDetails userLoanDetails1 = userLoanDetails.get(userBankIdentifier);

    System.out.println("User loan details --- " + userLoanDetails1.getLumpSumPaymentTracker());
    LinkedHashMap<Integer, Integer> lumpSumPaymentTracker = userLoanDetails1.getLumpSumPaymentTracker();
    Iterator itr = lumpSumPaymentTracker.keySet().iterator();
    Integer remainingAmount = userLoanDetails1.calculateTotalValue();
    Integer emiWhenPreviousLumpsumPaid = 0;
    while(itr.hasNext()) {
        Integer emiNumber = (Integer) itr.next();
        if (emiNumber > emi)
          break;
      remainingAmount = lumpSumPaymentTracker.get(emiNumber);
      emiWhenPreviousLumpsumPaid = emiNumber;
    }

    Integer balance = remainingAmount - (emi - emiWhenPreviousLumpsumPaid) * userLoanDetails1.getEmi();
    // TODO check the small amount, take it as double may be
    Integer remainingEmis = balance/userLoanDetails1.getEmi();
    if (balance%userLoanDetails1.getEmi() != 0)
      ++remainingEmis;

    System.out.println(bank);
    System.out.println(user);
    System.out.println(userLoanDetails1.calculateTotalValue() - balance);
    System.out.println(remainingEmis);
    return userLoanDetails;
  }
}
