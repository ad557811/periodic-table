package com.periodictable.importhelper;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class ElementDAO {

  private final String postgresConnectionString = "jdbc:postgresql://localhost:5432/periodic-table";
  private final String postgresUser = "periodic-table";
  private final String postgresPassword;

  private final String query = "INSERT INTO ELEMENT(NUMBER,NAME,APPEARANCE,CATEGORY,DENSITY,DISCOVERED_BY,NAMED_BY,SYMBOL) " +
      "VALUES(?,?,?,?,?,?,?,?)";

  public ElementDAO() {
    postgresPassword = System.getenv("PT_PASSWORD");
  }

  public void runMigration(List<Element> valuesToInsert) throws SQLException, ClassNotFoundException {
    Class.forName("org.postgresql.Driver");
    try (Connection connection = DriverManager.getConnection(postgresConnectionString, postgresUser, postgresPassword)) {
      connection.setAutoCommit(false);
      for (Element element : valuesToInsert) {
        try (PreparedStatement update = connection.prepareStatement(query)) {
          update.setInt(1, element.number());
          update.setString(2, element.name());
          update.setString(3, element.appearance());
          update.setString(4, element.category());
          update.setDouble(5, element.density());
          update.setString(6, element.discoveredBy());
          update.setString(7, element.namedBy());
          update.setString(8, element.symbol());
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
