package com.kubra.rest;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

  @GetMapping
  public ResponseEntity helloAnonymous() {
    Map<String, String> body = new HashMap<>();
    body.put("hello", "...whoever you are ");
    return ResponseEntity.ok(body);
  }

  @GetMapping("admin")
  public ResponseEntity helloAdmin() {
    Map<String, String> body = new HashMap<>();
    body.put("hello", "admin");
    return ResponseEntity.ok(body);
  }

  @GetMapping("user")
  public ResponseEntity helloUser() {
    Map<String, String> body = new HashMap<>();
    body.put("hello", "user");
    return ResponseEntity.ok(body);
  }

}
