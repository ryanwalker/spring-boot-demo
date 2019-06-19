package com.kubra.config;

import com.kubra.properties.ExampleValidatedProperties;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@PropertySource(value = "classpath:example.properties")
@EnableConfigurationProperties(ExampleValidatedProperties.class)
public class ExampleCustomConfiguration {

  private static final Logger logger = LoggerFactory.getLogger(ExampleCustomConfiguration.class);

  @Autowired
  private ExampleValidatedProperties properties;

  @PostConstruct
  private void postConstruct() {
    logger.warn("String one is set to {}", properties.getStringOne());
    logger.warn("String two is set to {}", properties.getStringTwo());
    logger.warn("Email is set to {}", properties.getEmail());
    logger.warn("My number is set to {}", properties.getMyNumber());
    logger.warn("Map values set to {}", properties.getMap());
  }
}
