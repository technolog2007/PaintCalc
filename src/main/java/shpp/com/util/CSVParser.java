package shpp.com.util;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CSVParser {

  public List<String[]> parseFile(String fileName) {
    List<String[]> list = new CSVLoader().getLoadList(new PropertiesLoader().loadProperties()
        .getProperty(fileName));
    List<String[]> parsList = new ArrayList<>();
    for (int i = 1; i < list.size(); i++) {
      parsList.add(list.get(i)[0].split(";"));
    }
    return parsList;
  }

  public void printParseFile(List<String[]> parseList) {
    for (int i = 0; i < parseList.size(); i++) {
      log.info("Element # {} is {}", i, parseList.get(i));
    }
  }

}
