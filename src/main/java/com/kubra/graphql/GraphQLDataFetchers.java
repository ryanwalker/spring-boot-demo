package com.kubra.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kubra.graphql.models.Account;
import com.kubra.graphql.models.AccountPage;
import graphql.schema.DataFetcher;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class GraphQLDataFetchers {

  private static final Map<String, Account> accountRepository = new HashMap<>();

  private ObjectMapper mapper;

  public GraphQLDataFetchers(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  public DataFetcher getAccounts() {
    return environment -> {
      int pageSize = environment.getArgument("pageSize");
      int pageNumber = environment.getArgument("pageNumber");

      AccountPage accountList = new AccountPage();
      Collection<Account> accounts = accountRepository.values();
      if (accounts != null) {
        accountList.setCount(accounts.size());
        accountList.setTotal(accounts.size());
        accountList.setAccounts(new ArrayList(accounts));
      }

      return accountList;
    };
  }

  public DataFetcher getAccountById() {
    return environment -> {
      String accountId = environment.getArgument("id");
      return accountRepository.get(accountId);
    };
  }

  public DataFetcher createAccount() {
    return environment ->
    {
      Account account = mapper.convertValue(environment.getArgument("account"), Account.class);
      account.setCreated(OffsetDateTime.now());
      account.setAccountId(UUID.randomUUID().toString());
      String version = new String(Base64.getEncoder().encode(account.getAccountId().getBytes()));
      account.setVersion(version);
      accountRepository.put(account.getAccountId(), account);
      return account;
    };
  }
}
