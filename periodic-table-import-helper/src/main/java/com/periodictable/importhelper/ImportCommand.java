package com.periodictable.importhelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class ImportCommand {

  private static final Logger log = LoggerFactory.getLogger(ImportCommand.class);
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
      log.error("Error in executing import:", e);
      return "Errors in executing import";
    }
  }

}