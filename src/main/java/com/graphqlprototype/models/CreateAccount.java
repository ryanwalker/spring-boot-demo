package com.graphqlprototype.models;

import lombok.Data;

@Data
public class CreateAccount {
  private String externalAccountId;
  private String serviceAddressLine1;
  private String serviceAddressLine2;
  private AccountStatus accountStatus;
}
