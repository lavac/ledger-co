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
  private Integer numberOfEMIs;
  private Integer remainingTotalValue;
  private Integer emi;
  private LinkedHashMap<Integer, Integer> lumpSumPaymentTracker;

  public UserLoanDetails(String bank,
                         String user,
                         Integer principal,
                         Integer term,
                         Integer rateOfInterest,
                         Integer totalValue,
                         Integer emi,
                         LinkedHashMap<Integer, Integer> lumpSumPaymentTracker
                         ) {
    this.bank = bank;
    this.user = user;
    this.principal = principal;
    this.term = term;
    this.rateOfInterest = rateOfInterest;
    this.remainingTotalValue = totalValue;
    this.emi = emi;
    this.lumpSumPaymentTracker = lumpSumPaymentTracker;
  }

  public Integer calculateTotalValue() {
    return Math.toIntExact(Math.round((principal*term*rateOfInterest)/100.0)) + principal;
  }
}
