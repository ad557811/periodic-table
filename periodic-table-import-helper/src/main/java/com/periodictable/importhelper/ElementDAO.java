package com.periodictable.importhelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class ElementDAO {

  private static final Logger log = LoggerFactory.getLogger(ElementDAO.class);

  private final String postgresConnectionString;
  private final String postgresUser;
  private final String postgresPassword;

  private static final String INSERT_QUERY = "INSERT INTO ELEMENT" +
      "(NUMBER,NAME,APPEARANCE,CATEGORY,DENSITY,DISCOVERED_BY,NAMED_BY,SYMBOL,SUMMARY) " +
      "VALUES(?,?,?,?,?,?,?,?,?)";
  private static final String CLEAR_TABLE_QUERY = "TRUNCATE ELEMENT RESTART IDENTITY";

  public ElementDAO() {
    postgresConnectionString = "jdbc:postgresql://localhost:5432/periodic-table";
    postgresUser = "periodic-table";
    postgresPassword = System.getenv("PT_PASSWORD");
  }

  public void runMigration(List<Element> valuesToInsert) throws SQLException {
    try (Connection connection = DriverManager.getConnection(postgresConnectionString, postgresUser, postgresPassword)) {
      connection.setAutoCommit(false);
      try (PreparedStatement clearTable = connection.prepareStatement(CLEAR_TABLE_QUERY)) {
        clearTable.executeUpdate();
      }

      for (Element element : valuesToInsert) {
        try (PreparedStatement update = connection.prepareStatement(INSERT_QUERY)) {
          log.info("Inserting {}", element);
          update.setInt(1, element.number());
          update.setString(2, element.name());
          update.setString(3, element.appearance());
          update.setString(4, element.category());
          update.setDouble(5, element.density());
          update.setString(6, element.discoveredBy());
          update.setString(7, element.namedBy());
          update.setString(8, element.symbol());
          update.setString(9, element.summary());
          update.execute();
        }
      }
      try {
        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        throw e;
      }
    }
  }

}
