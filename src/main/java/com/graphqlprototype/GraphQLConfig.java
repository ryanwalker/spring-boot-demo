package com.graphqlprototype;

import com.google.common.io.Resources;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

  private GraphQLDataFetchers graphQLDataFetchers;

  public GraphQLConfig(GraphQLDataFetchers dataFetchers) {
    this.graphQLDataFetchers = dataFetchers;
  }

  @Bean
  public GraphQLSchema graphQLSchema() throws IOException {
    URL url = Resources.getResource("graphql/schema.graphqls");
    String schemaDefinitionLanguage = Resources.toString(url, Charset.forName("UTF-8"));
    return buildSchema(schemaDefinitionLanguage);
  }

  private GraphQLSchema buildSchema(String schemaDefinitionLanguage) {
    TypeDefinitionRegistry typeRegistery = new SchemaParser().parse(schemaDefinitionLanguage);
    RuntimeWiring runtimeWiring = buildWiring();
    SchemaGenerator schemaGenerator = new SchemaGenerator();
    return schemaGenerator.makeExecutableSchema(typeRegistery, runtimeWiring);
  }

  private RuntimeWiring buildWiring() {
    return RuntimeWiring.newRuntimeWiring()
        .type(TypeRuntimeWiring.newTypeWiring("Query")
            .dataFetcher("accounts", graphQLDataFetchers.getAccounts()))
        .type(TypeRuntimeWiring.newTypeWiring("Query")
            .dataFetcher("accountById", graphQLDataFetchers.getAccountById()))
        .type(TypeRuntimeWiring.newTypeWiring("Mutation")
            .dataFetcher("createAccount", graphQLDataFetchers.createAccount()))
        .build();
  }

}
