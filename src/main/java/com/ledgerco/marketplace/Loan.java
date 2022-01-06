package com.ledgerco.marketplace;

import java.util.LinkedHashMap;
import java.util.Map;

public class Loan extends Command {

  @Override
  public Map<String, UserLoanDetails> handle(Map<String, UserLoanDetails> userLoanDetailsMap, String input) {
    String inputString[] = input.split("\\s+");
    String bank = inputString[1];
    String user = inputString[2];
    Integer principal = Integer.parseInt(inputString[3]);
    Integer term = Integer.parseInt(inputString[4]);
    Integer rateOfInterest = Integer.parseInt(inputString[5]);
    String userBankIdentifier = bank + "_" + user;
    if(userLoanDetailsMap.containsKey(userBankIdentifier)) {
      System.out.println("Throw exception");
    }

    Integer totalValue = calculateTotalValue(principal, term, rateOfInterest);
    Integer emi = Math.toIntExact(Math.round(totalValue/(term*12.0)));
    LinkedHashMap<Integer, Integer> lumpSumTracker = new LinkedHashMap<>();
    UserLoanDetails userLoanDetails = new UserLoanDetails(bank,
        user,
        principal,
        term,
        rateOfInterest,
        totalValue,
        emi,
        lumpSumTracker
    );

    userLoanDetailsMap.put(userBankIdentifier, userLoanDetails);

    return userLoanDetailsMap;
  }

  public Integer calculateTotalValue(Integer principal, Integer term, Integer rateOfInterest) {
    return Math.toIntExact(Math.round((principal*term*rateOfInterest)/100.0)) + principal;
  }
}
