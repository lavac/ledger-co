package com.ledgerco.marketplace;

import com.ledgerco.marketplace.exception.IllegalInputException;
import com.ledgerco.marketplace.exception.InvalidInputCommandException;
import com.ledgerco.marketplace.model.UserLoanDetails;
import com.ledgerco.marketplace.service.Command;
import com.ledgerco.marketplace.service.CommandFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TheLedgerCoApplication {
  public static void main(String[] args) {
    Map<String, UserLoanDetails> userLoanDetailsMap = new HashMap<>();

    String filePath = args[0];
    File file = new File(filePath);
    try {
      Scanner sc = new Scanner(file);
      while (sc.hasNext()) {
        String input = sc.nextLine();
        String commandInString = input.split("\\s+")[0];
        Command command = CommandFactory.getCommand(commandInString);
        userLoanDetailsMap = command.execute(userLoanDetailsMap, input);
      }
    } catch (FileNotFoundException e) {
      System.out.println("Input file not found");
    } catch (IllegalInputException | InvalidInputCommandException exception) {
      System.out.println(exception.getMessage());
    } catch (Exception exception) {
      System.out.println("Unexpected error occurred with message: " + exception.getMessage());
    }
  }
}
