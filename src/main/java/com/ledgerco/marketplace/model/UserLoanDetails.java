package com.ledgerco.marketplace.model;

import lombok.Data;
import lombok.Getter;

import java.util.Iterator;
import java.util.LinkedHashMap;

@Data
@Getter
public class UserLoanDetails {
  private String bank;
  private String user;
  private Integer principal;
  private Integer term;
  private Integer rateOfInterest;
  private Integer remainingAmount;
  private Integer emiAmount;
  private LinkedHashMap<Integer, Integer> remainingAmountTrackerAfterLumpSumPayment;

  public UserLoanDetails (String bank,
                         String user,
                         Integer principal,
                         Integer term,
                         Integer rateOfInterest,
                         Integer totalValue,
                         Integer emiAmount,
                         LinkedHashMap<Integer, Integer> remainingAmountTrackerAfterLumpSumPayment) {
    this.bank = bank;
    this.user = user;
    this.principal = principal;
    this.term = term;
    this.rateOfInterest = rateOfInterest;
    this.remainingAmount = totalValue;
    this.emiAmount = emiAmount;
    this.remainingAmountTrackerAfterLumpSumPayment = remainingAmountTrackerAfterLumpSumPayment;
  }

  public Integer calculateTotalAmount() {
    return Math.toIntExact(Math.round((principal*term*rateOfInterest)/100.0)) + principal;
  }

  public static String getUserBankIdentifier(String bank, String user) {
    return bank + "_" + user;
  }

  public Integer getRemainingAmountAfterTheRequestedEmi(Integer requestedEmi) {
    Integer remainingAmountWhenPreviousLumpSumPaid = calculateTotalAmount();
    int emiWhenPreviousLumpSumPaid = 0;

    for (Iterator<Integer> iterator = remainingAmountTrackerAfterLumpSumPayment.keySet().iterator(); iterator.hasNext();) {
      Integer emiNumberWhenLumpSumPaid = iterator.next();
      if (emiNumberWhenLumpSumPaid > requestedEmi)
        break;
      remainingAmountWhenPreviousLumpSumPaid = remainingAmountTrackerAfterLumpSumPayment.get(emiNumberWhenLumpSumPaid);
      emiWhenPreviousLumpSumPaid = emiNumberWhenLumpSumPaid;
    }
    return remainingAmountWhenPreviousLumpSumPaid - (requestedEmi - emiWhenPreviousLumpSumPaid) * emiAmount;
  }

  public void update(Integer requestedEmi, Integer lumpSumAmount) {
    this.remainingAmount = this.remainingAmount - (requestedEmi * emiAmount + lumpSumAmount);
    remainingAmountTrackerAfterLumpSumPayment.put(requestedEmi, remainingAmount);
  }
}
