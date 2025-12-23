package com.periodictable.flyway;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "flyway")
public class FlywayProperties {
  private String[] locations;

  public String[] getLocations() {
    return locations;
  }

  public void setLocations(String[] locations) {
    this.locations = locations;
  }
}
