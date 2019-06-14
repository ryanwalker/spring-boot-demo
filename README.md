# Spring Boot Demo

## Run Spring Boot

```
./mvnw spring-boot:run

```

## POST Endpoint

http://localhost:8080/graphql


## Graph QL Playground Queries

https://github.com/prisma/graphql-playground

```
query {
  accountById(id: "1e7e4fd2-c3d6-4e51-8322-058268260731") {
    accountId,
    accountStatus,
    serviceAddressLine1,
    serviceAddressLine2
  }
}

query {
  accounts(pageSize: 50, pageNumber: 0) {
		count,
    total,
    accounts {
      accountId,
      serviceAddressLine1,
      serviceAddressLine2,
      created,
      updated
    }
  }
}

mutation {
  createAccount(
    account: {
      accountStatus: CONNECTED,
      externalAccountId: "my121",
      serviceAddressLine1:"4335 W. Sycamore Creek Road Ave. St.",
      serviceAddressLine2: "Blueberry, AZ 85858"
    }
  ) {
    accountId,
    created,
    updated,
    version,
    externalAccountId,
    serviceAddressLine1,
    serviceAddressLine2,
    accountType,
    accountStatus,

  }
}
```

### Curl Example
```bash
curl 'http://localhost:8080/graphql' -H 'Content-Type: application/json' -H 'Accept: application/json' \
  --data-binary '{"query":"{accounts(pageSize: 10, pageNumber: 0) {count, total, accounts { accountId, serviceAddressLine1 } } }"}' \
  --compressed

```