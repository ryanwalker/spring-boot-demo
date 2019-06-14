package com.kubra.graphql.models;

import java.time.OffsetDateTime;

public class Account {
  private String accountId;
  private OffsetDateTime created;
  private OffsetDateTime updated;
  private String version;
  private String externalAccountID;
  private String serviceAddressLine1;
  private String serviceAddressLine2;
  private AccountStatus accountStatus;


  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public OffsetDateTime getCreated() {
    return created;
  }

  public void setCreated(OffsetDateTime created) {
    this.created = created;
  }

  public OffsetDateTime getUpdated() {
    return updated;
  }

  public void setUpdated(OffsetDateTime updated) {
    this.updated = updated;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getExternalAccountID() {
    return externalAccountID;
  }

  public void setExternalAccountID(String externalAccountID) {
    this.externalAccountID = externalAccountID;
  }

  public String getServiceAddressLine1() {
    return serviceAddressLine1;
  }

  public void setServiceAddressLine1(String serviceAddressLine1) {
    this.serviceAddressLine1 = serviceAddressLine1;
  }

  public String getServiceAddressLine2() {
    return serviceAddressLine2;
  }

  public void setServiceAddressLine2(String serviceAddressLine2) {
    this.serviceAddressLine2 = serviceAddressLine2;
  }

  public AccountStatus getAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(AccountStatus accountStatus) {
    this.accountStatus = accountStatus;
  }
}
