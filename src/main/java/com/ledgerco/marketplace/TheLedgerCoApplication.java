package com.ledgerco.marketplace;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TheLedgerCoApplication {
  public static void main(String[] args) {
    Map<String, UserLoanDetails> userLoanDetails = new HashMap<>();

    File file = new File("src/main/java/com/ledgerco/marketplace/inputData.csv");
    try {
      Scanner sc = new Scanner(file);
      while (sc.hasNext()) {
        String input = sc.nextLine();
        String operation = input.split("\\s+")[0];
        Command command = CommandFactory.getCommand(operation);
        userLoanDetails = command.handle(userLoanDetails, input);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
