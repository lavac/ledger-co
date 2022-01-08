package com.ledgerco.marketplace;

import java.util.LinkedHashMap;
import java.util.Map;

public class Loan extends Command {

  @Override
  public Map<String, UserLoanDetails> execute(Map<String, UserLoanDetails> userLoanDetailsMap, String input) {
    String inputStrings[] = input.split("\\s+");
    String bank = inputStrings[1];
    String user = inputStrings[2];
    Integer principal = Integer.parseInt(inputStrings[3]);
    Integer term = Integer.parseInt(inputStrings[4]);
    Integer rateOfInterest = Integer.parseInt(inputStrings[5]);
    String userBankIdentifier = bank + "_" + user;
    if(userLoanDetailsMap.containsKey(userBankIdentifier)) {
      System.out.println("Throw exception");
    }

    Integer totalValue = getTotalValue(principal, term, rateOfInterest);
    Integer emiAmount = getEmiAmount(totalValue, term);
    LinkedHashMap<Integer, Integer> lumpSumTracker = new LinkedHashMap<>();
    UserLoanDetails userLoanDetails = new UserLoanDetails(bank,
        user,
        principal,
        term,
        rateOfInterest,
        totalValue,
        emiAmount,
        lumpSumTracker
    );

    userLoanDetailsMap.put(userBankIdentifier, userLoanDetails);

    return userLoanDetailsMap;
  }

  private int getEmiAmount(Integer totalValue, Integer term) {
    return Math.toIntExact(Math.round(totalValue / (term*12.0)));
  }

  public Integer getTotalValue(Integer principal, Integer term, Integer rateOfInterest) {
    return Math.toIntExact(Math.round((principal*term*rateOfInterest)/100.0)) + principal;
  }
}
