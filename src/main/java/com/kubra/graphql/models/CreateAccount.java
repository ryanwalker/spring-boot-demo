package com.kubra.graphql.models;


public class CreateAccount {
  private String externalAccountId;
  private String serviceAddressLine1;
  private String serviceAddressLine2;
  private AccountStatus accountStatus;


  public String getExternalAccountId() {
    return externalAccountId;
  }

  public void setExternalAccountId(String externalAccountId) {
    this.externalAccountId = externalAccountId;
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
