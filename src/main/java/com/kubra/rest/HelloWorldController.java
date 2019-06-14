package com.kubra.rest;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
    path = "/api/hello",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE
)
public class HelloWorldController {

  @GetMapping
  public ResponseEntity hello() {
    Map<String, String> body = new HashMap<>();
    body.put("hello", "world");
    return ResponseEntity.ok(body);
  }

}
