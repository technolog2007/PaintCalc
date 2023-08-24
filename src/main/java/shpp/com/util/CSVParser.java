package shpp.com.util;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
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
    return deCodecToUtf8(parsList);
  }

  /**
   * The method decodes the values of the parsed file into UTF-8 encoding
   * @param list - parsed file
   * @return - List<String[]> with string values in UTF-8 encoding
   */
  @SneakyThrows
  public List<String[]> deCodecToUtf8(List<String[]> list) {
    for (String[] strings : list) {
      for (int j = 0; j < strings.length; j++) {
        String deCodecWord = new String(strings[j].getBytes(), StandardCharsets.UTF_8);
        strings[j] = deCodecWord;
      }
    }
    return list;
  }
}
