package shpp.com.util;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import shpp.com.app.App;

@Slf4j
public class CSVLoader {

  private static final String IDEA_FILE_PATH = "src/main/resources/";

  @SneakyThrows
  private List<String[]> load(String fileName) {
    log.info("Start load csv file with name {}!", fileName);
    try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
      return reader.readAll();
    }
  }

  public List<String[]> getLoadList(String fileName){
    try {
      return load(IDEA_FILE_PATH + fileName);
    } catch (Exception e) {
      log.warn("Start load ... ");
      File jarPath = new File(App.class
          .getProtectionDomain()
          .getCodeSource()
          .getLocation()
          .getPath());
      String filePath = jarPath.getParentFile().getPath();
      log.warn("*****JAR PATH : {}", filePath + "/config/" + fileName);
      return load(filePath + "/config/" + fileName);
    }
  }


}
