package com.kubra.rest;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
    path = "hello",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE
)
public class HelloWorldController {


  private MessageSource messageSource;

  public HelloWorldController(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @GetMapping
  public ResponseEntity helloAnonymous() {
    Map<String, String> body = new HashMap<>();
    body.put(i18n("greeting"), i18n("world"));
    return ResponseEntity.ok(body);
  }

  private String i18n(String message) {
    return messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
  }

  @GetMapping("admin")
  public ResponseEntity helloAdmin() {
    Map<String, String> body = new HashMap<>();
    body.put(i18n("greeting"), "admin");
    return ResponseEntity.ok(body);
  }

  @GetMapping("user")
  public ResponseEntity helloUser() {
    Map<String, String> body = new HashMap<>();
    body.put(i18n("greeting"), "user");
    return ResponseEntity.ok(body);
  }

}
