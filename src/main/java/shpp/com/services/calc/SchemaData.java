package shpp.com.services.calc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import shpp.com.models.materials.PaintData;
import shpp.com.models.materials.PrimerData;
import shpp.com.models.workpiece.Mark;
import shpp.com.models.workpiece.Ral;
import shpp.com.models.workpiece.Workpiece;
import shpp.com.util.CSVParser;

/**
 * Створює набір грунтових і лакофарбових матеріалів, які відповідають схемі покриття
 */
@Slf4j
public class SchemaData {

  private List<PrimerData> primersData;
  private PaintData paintData;

  private final List<String[]> parsePrimerData;
  private final List<String[]> parsePaintData;
  private final HashMap<Mark,Ral[]> map;

  private static final String FILE_PRIMER = "primer";
  private static final String FILE_PAINT = "paint";


  public SchemaData() {
    this.parsePrimerData = getParsFile(FILE_PRIMER);
    this.parsePaintData = getParsFile(FILE_PAINT);
    this.map = createMap(this.parsePaintData);
  }

  public List<PrimerData> getPrimersData() {
    return primersData;
  }
  public PaintData getPaintData() {
    return paintData;
  }

  public HashMap<Mark, Ral[]> getMap() {
    return this.map;
  }

  /**
   * Метод парсить файл-таблицю *.CSV і повертає масив строк
   *
   * @return - розпарсений файл-таблицю в List<String[]>
   */
  private List<String[]> getParsFile(String fileName) {
    CSVParser parser = new CSVParser();
    return parser.parseFile(fileName);
  }

  private HashMap<Mark, Ral[]> createMap(List<String[]> list) {
    HashMap<Mark, Ral[]> map = new HashMap<>();
    // 1. Зробити вибірку по всім Mark і скласти їх в Map
    // 2. Створити і наповнити масиви по кількості Mark
    // 3. Скомбінувати Mark і масиви Ral в HashMap
    for (int i = 0; i < Mark.values().length; i++) {
      for (int j = 0; j < list.size(); j++) {
        List<Ral> arr = new ArrayList<>();
        if (Mark.values()[i].getMarkName().equals(list.get(j)[0])) {
          for (int k = 0; k < Ral.values().length; k++) {
            if (list.get(j)[1].equals(Ral.values()[k].getRalNumber())) {
              arr.add(Ral.values()[k]);
            }
          }
          map.put(Mark.values()[i], arr.toArray(new Ral[0]));
        }
      }
    }
    log.info("Map size is : {}",map.size());
    return map;
  }

  /**
   * Метод робить вибірку грунта по матеріалу заготовки і повертає список грунтів із відповідними
   * строками
   *
   * @param workpiece - заготовка із набором відповідних характеристик
   * @return - список строк List<String[]> із характеристиками відповідних грунтових матеріалів
   */
  private List<String[]> getPrimersDataFromFile(Workpiece workpiece) {
    List<String[]> data = new ArrayList<>();
    for (String[] strings : parsePrimerData) {
      if (strings[0].equals(workpiece.getMaterial().getMaterial())) {
        data.add(strings);
      }
    }
    return data;
  }

  /**
   * Метод створює із відповідних строк файла-таблиці, список даних із відповідних грунтів, що
   * містить відповідні характеристики
   *
   * @return - список грунтових матеріалів List<PrimerData> із нборами відповідних характеристик
   */
  public List<PrimerData> setPrimersData(Workpiece workpiece) {
    List<String[]> primersDataFromFile = getPrimersDataFromFile(workpiece);
    List<PrimerData> dataList = new ArrayList<>();
    if (primersDataFromFile.size() >= 1) {
      for (String[] strings : primersDataFromFile) {
        switch (strings[2]) {
          case "1K": {
            PrimerData primerData = new PrimerData()
                .setPrimerName(strings[1])
                .setPrimerMaterialCoefficient(Double.parseDouble(strings[3]))
                .setPrimerWorkCoefficient(Double.parseDouble(strings[4]))
                .setPrimerSolventName(strings[5])
                .setPrimerSolventCoefficient(Double.parseDouble(strings[6]));
            dataList.add(primerData);
            break;
          }
          case "2K": {
            PrimerData primerData = new PrimerData()
                .setPrimerName(strings[1])
                .setPrimerMaterialCoefficient(Double.parseDouble(strings[3]))
                .setPrimerWorkCoefficient(Double.parseDouble(strings[4]))
                .setPrimerSolventName(strings[5])
                .setPrimerSolventCoefficient(Double.parseDouble(strings[6]))
                .setPrimerHardenerName(strings[7])
                .setPrimerHardenerCoefficient(Double.parseDouble(strings[8]));
            dataList.add(primerData);
            break;
          }
          case "N": {
            PrimerData primerData = new PrimerData()
                .setPrimerName(strings[1])
                .setPrimerMaterialCoefficient(Double.parseDouble(strings[3]))
                .setPrimerWorkCoefficient(Double.parseDouble(strings[4]));
            dataList.add(primerData);
            break;
          }
        }
      }
    } else {
      log.info("No primers in the file! Please check input data file!");
      throw new RuntimeException();
    }
    this.primersData = dataList;
    return dataList;
  }

  /**
   * Метод виконує вибір фарбового матеріалу і повертає відповідну строку таблиці із файла
   *
   * @param workpiece - заготовка із набором характеристик
   * @return - строка із характеристиками відповідного фарбового матеріалу
   */
  private String[] getPaintsDataFromFile(Workpiece workpiece) {
    String[] data = new String[0];
    for (String[] strings : parsePaintData) {
      if (strings[1].equals(workpiece.getRal().getRalNumber()) &&
          strings[0].equals(workpiece.getMark().getMarkName())) {
        data = strings;
      }
    }
    return data;
  }

  /**
   * Метод створює із відповідної строки файла-таблиці, фарбовий матеріал із набором відповідних
   * характеристик
   *
   * @return - фарбовий матеріал PaintData із набором відповідних характеристик
   */
  public PaintData setPaintsData(Workpiece workpiece) {
    String[] paintDataFromFile = getPaintsDataFromFile(workpiece);
    if (paintDataFromFile.length >= 1) {
      // вибірка лише для двокомпонентних фарб
      this.paintData = new PaintData()
          .setPaintName(paintDataFromFile[2])
          .setPaintMaterialCoefficient(Double.parseDouble(paintDataFromFile[3]))
          .setPaintWorkCoefficient(Double.parseDouble(paintDataFromFile[4]))
          .setPaintHardenerName(paintDataFromFile[7])
          .setPaintHardenerCoefficient(Double.parseDouble(paintDataFromFile[8]))
          .setPaintSolventName(paintDataFromFile[5])
          .setPaintSolventCoefficient(Double.parseDouble(paintDataFromFile[6]));
      return paintData;
    } else {
      log.info("No primers in the file! Please check input data file!");
      throw new RuntimeException();
    }
  }
}
