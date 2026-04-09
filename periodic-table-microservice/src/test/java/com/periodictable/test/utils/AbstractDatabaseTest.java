package com.periodictable.test.utils;

import org.apache.commons.lang3.Strings;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

@SpringBootTest
@Testcontainers
public abstract class AbstractDatabaseTest {

  @Container
  static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:18")
      .withDatabaseName("periodic-table");

  @DynamicPropertySource
  static void databaseProperties(DynamicPropertyRegistry registry) {
    String rdbcUrl = Strings.CI.replace(postgres.getJdbcUrl(), "jdbc:", "r2dbc:");
    registry.add("flyway.enabled", () -> true);
    registry.add("flyway.url", postgres::getJdbcUrl);
    registry.add("spring.r2dbc.url", () -> rdbcUrl);
    registry.add("spring.r2dbc.username", postgres::getUsername);
    registry.add("spring.r2dbc.password", postgres::getPassword);
  }

}
