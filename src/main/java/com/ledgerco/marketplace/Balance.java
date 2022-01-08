package com.ledgerco.marketplace;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Balance extends Command {

  @Override
  public Map<String, UserLoanDetails> execute(Map<String, UserLoanDetails> userLoanDetailsMap, String input) {

    String[] inputStrings = input.split("\\s+");
    Integer requestedEmi = Integer.parseInt(inputStrings[3]);

    String userBankIdentifier = UserLoanDetails.getUserBankIdentifier(inputStrings[1], inputStrings[2]);
    UserLoanDetails userLoanDetails = userLoanDetailsMap.get(userBankIdentifier);

    Integer remainingAmount = userLoanDetails.calculateTotalAmount();
    LinkedHashMap<Integer, Integer> lumpSumPaymentTracker = userLoanDetails.getLumpSumPaymentTracker();
    int emiWhenPreviousLumpSumPaid = 0;

    for (Iterator<Integer> iterator = lumpSumPaymentTracker.keySet().iterator(); iterator.hasNext();) {
      Integer emiNumberWhenLumpSumPaid = iterator.next();
      if (emiNumberWhenLumpSumPaid > requestedEmi)
        break;
      remainingAmount = lumpSumPaymentTracker.get(emiNumberWhenLumpSumPaid);
      emiWhenPreviousLumpSumPaid = emiNumberWhenLumpSumPaid;
    }

    Integer remainingAmountAfterTheRequestedEmi = remainingAmount - (requestedEmi - emiWhenPreviousLumpSumPaid) * userLoanDetails.getEmiAmount();
    Integer remainingBalanceAfterTheRequestedEmi = userLoanDetails.calculateTotalAmount() - remainingAmountAfterTheRequestedEmi;

    Integer remainingEmisAfterTheRequestedEmi = remainingAmountAfterTheRequestedEmi/userLoanDetails.getEmiAmount();

    if (remainingAmountAfterTheRequestedEmi % userLoanDetails.getEmiAmount() != 0)
      ++remainingEmisAfterTheRequestedEmi;

    System.out.println(userLoanDetails.getBank());
    System.out.println(userLoanDetails.getUser());
    System.out.println(remainingBalanceAfterTheRequestedEmi);
    System.out.println(remainingEmisAfterTheRequestedEmi);
    return userLoanDetailsMap;
  }
}
