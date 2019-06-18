package com.kubra.properties;

import java.util.Map;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
    prefix = "example",
    ignoreUnknownFields = false
)
public class ExampleValidatedProperties {

  @NotBlank
  @Length(max = 10, min = 1)
  private String stringOne;

  @NotBlank
  @Length(max = 5, min = 2)
  private String stringTwo;

  @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")
  private String email;

  private Integer myNumber;

  private Map<String, String> map;


  public String getStringOne() {
    return stringOne;
  }

  public void setStringOne(String stringOne) {
    this.stringOne = stringOne;
  }

  public String getStringTwo() {
    return stringTwo;
  }

  public void setStringTwo(String stringTwo) {
    this.stringTwo = stringTwo;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getMyNumber() {
    return myNumber;
  }

  public void setMyNumber(Integer myNumber) {
    this.myNumber = myNumber;
  }

  public Map<String, String> getMap() {
    return map;
  }

  public void setMap(Map<String, String> map) {
    this.map = map;
  }
}
