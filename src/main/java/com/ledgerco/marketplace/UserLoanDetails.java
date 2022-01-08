package com.ledgerco.marketplace;

import lombok.Data;
import lombok.Getter;

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
  private LinkedHashMap<Integer, Integer> lumpSumPaymentTracker;

  public UserLoanDetails(String bank,
                         String user,
                         Integer principal,
                         Integer term,
                         Integer rateOfInterest,
                         Integer totalValue,
                         Integer emiAmount,
                         LinkedHashMap<Integer, Integer> lumpSumPaymentTracker) {
    this.bank = bank;
    this.user = user;
    this.principal = principal;
    this.term = term;
    this.rateOfInterest = rateOfInterest;
    this.remainingAmount = totalValue;
    this.emiAmount = emiAmount;
    this.lumpSumPaymentTracker = lumpSumPaymentTracker;
  }

  public Integer calculateTotalAmount() {
    return Math.toIntExact(Math.round((principal*term*rateOfInterest)/100.0)) + principal;
  }

  public static String getUserBankIdentifier(String inputString1, String inputString2) {
    return inputString1 + "_" + inputString2;
  }
}
