package com.graphqlprototype.models;

import java.util.List;
import lombok.Data;

@Data
public class AccountPage {
  private int count;
  private int total;
  private List<Account> accounts;
}
