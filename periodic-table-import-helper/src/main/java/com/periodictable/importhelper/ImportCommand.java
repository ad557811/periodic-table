package com.periodictable.importhelper;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Map;

@ShellComponent
public class ImportCommand {

  private final ElementDAO dao;
  private final CSVReader reader;

  public ImportCommand(ElementDAO dao, CSVReader reader) {
    this.dao = dao;
    this.reader = reader;
  }

  @ShellMethod(key = "import")
  public String importData() {
    try {
      List<Element> parsedData = reader.parseFile();
      dao.runMigration(parsedData);
      return "All done!";
    } catch (Exception e) {
      e.printStackTrace();
      return "Errors in executing import";
    }
  }

}