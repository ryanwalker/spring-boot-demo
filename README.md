# Spring Boot Demo

## Run Spring Boot

```
./mvnw spring-boot:run

```

## Security
Security configuration can be found in [SecurityConfiguration.java](src/main/java/com/kubra/)
```bash
curl --silent --header 'Content-Type: application/json' localhost:8080/hello
curl --silent --header 'Content-Type: application/json' --user user:pass localhost:8080/hello/user
curl --silent --header 'Content-Type: application/json' --user admin:pass localhost:8080/hello/admin
```

## Actuator
[Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.0.x/actuator-api/html/) is a 
library/framework that brings production-ready features to your app: metrics gathering, 
monitoring, database, health info and much more. It provides a collection of endpoints that can be 
queried to gather info or run actions. Here's a 
[list of available endpoints](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html#production-ready-endpoints-enabling-endpoints).

#####Actuator Config
All actuator endpoints are enabled for this demo. See [application.yml](src/main/resources/application.yml).

Some of the more common endpoints:
```bash
http://localhost:8080/actuator/conditions
http://localhost:8080/actuator/beans
http://localhost:8080/actuator/conditions
http://localhost:8080/actuator/health
```

## REST Web Services
REST controllers are annotated with `@RestController`. 
See [HelloWorldController.java](src/main/java/com/kubra/rest/HelloWorldController.java).

```bash
curl --silent --header 'Content-Type: application/json' localhost:8080/hello
curl --silent --header 'Content-Type: application/json' --user user:pass localhost:8080/hello/user
curl --silent --header 'Content-Type: application/json' --user admin:pass localhost:8080/hello/admin
```
HTTP requests/responses are converted to/from Objects using `Jackson`'s `ObjectMapper`. 
[Object Mapping](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-spring-mvc.html#howto-customize-the-jackson-objectmapper) 

## i18n
There is a message file for each desired language. See [messages_fr.properties](src/main/resources/messages_fr.properties), 
for example. The locale config can be found in [InternationalizationConfig](src/main/java/com/kubra/config/InternationalizationConfig.java).
An example of retrieving an internationalized message can be seen in [HelloWorldControlelr.java](src/main/java/com/kubra/rest/HelloWorldController.java)
`Accept-language` is used to specify the locale:
```bash
curl --silent --header 'Content-Type: application/json' --header 'Accept-Language: en' 'localhost:8080/hello'
curl --silent --header 'Content-Type: application/json' --header 'Accept-Language: es' 'localhost:8080/hello'
curl --silent --header 'Content-Type: application/json' --header 'Accept-Language: fr' 'localhost:8080/hello'

```

## GraphQL 
##### POST endpoint
http://localhost:8080/graphql


##### Graph QL Playground Queries

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