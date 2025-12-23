package com.periodictable.flyway;

import org.flywaydb.core.Flyway;
import org.springframework.boot.r2dbc.autoconfigure.R2dbcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Workaround for Flyway not supporting R2DBC.
 * @author Baeldung
 * @see <a href="https://www.baeldung.com/spring-r2dbc-flyway">Spring R2DBC Migrations Using Flyway</a>
 */
@Configuration
public class FlywayConfiguration {

  @Bean(initMethod = "migrate")
  public Flyway flyway(R2dbcProperties r2dbcProperties, FlywayProperties flywayProperties) {
    if (r2dbcProperties.getUrl() == null) {
      throw new IllegalArgumentException("Cannot set up migration without a DB URL");
    }

    return Flyway.configure()
        .dataSource(
            r2dbcProperties.getUrl().replace("r2dbc:", "jdbc:"),
            r2dbcProperties.getUsername(),
            r2dbcProperties.getPassword()
        )
        .locations(flywayProperties.getLocations())
        .baselineOnMigrate(true)
        .load();
  }
}