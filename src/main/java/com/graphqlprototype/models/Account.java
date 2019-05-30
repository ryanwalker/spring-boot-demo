package com.graphqlprototype.models;

import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class Account {
  private String accountId;
  private OffsetDateTime created;
  private OffsetDateTime updated;
  private String version;
  private String externalAccountID;
  private String serviceAddressLine1;
  private String serviceAddressLine2;
  private AccountStatus accountStatus;
}
