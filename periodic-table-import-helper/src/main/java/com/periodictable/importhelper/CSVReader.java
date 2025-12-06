package com.periodictable.importhelper;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CSVReader {

  private static final String FILE = "/PeriodicTableCSV.csv";

  public List<Element> parseFile() throws IOException, CsvValidationException {
    var fileAsStream = CSVReader.class.getResource(FILE).getFile();
    var reader = new CSVReaderHeaderAware(new FileReader(fileAsStream));

    List<Element> result = new ArrayList<>();
    Map<String, String> next = reader.readMap();
    while (next != null) {
      result.add(new Element(
          Integer.parseInt(StringUtils.defaultIfBlank(next.get("number"), "0")),
          next.get("name"),
          next.get("appearance"),
          next.get("category"),
          Double.parseDouble(StringUtils.defaultIfBlank(next.get("density"), "0.0")),
          next.get("discovered_by"),
          next.get("named_by"),
          next.get("symbol"),
          next.get("summary")
      ));
      next = reader.readMap();
    }

    return result;
  }

}
