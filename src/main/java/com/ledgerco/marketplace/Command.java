package com.ledgerco.marketplace;

import java.util.Map;

public abstract class Command {
  abstract Map<String, UserLoanDetails> execute(Map<String, UserLoanDetails> userLoanDetails, String input);
}
