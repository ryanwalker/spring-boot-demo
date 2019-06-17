package com.kubra.rest;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity illegalArgument(IllegalArgumentException e) {
    Map<String, String> errorBody = errorBody("You cannot do this!!! " + e.getMessage());
    return ResponseEntity.badRequest().body(errorBody);
  }

  /**
   * Returns a map with the error message, easily converted to json
   */
  private Map<String, String> errorBody(String message) {
    Map<String, String> errorMap = new HashMap<>();
    errorMap.put("message", message);
    return errorMap;
  }

}

