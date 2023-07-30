package shpp.com.services.calc;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import shpp.com.models.materials.MetalFraction;
import shpp.com.models.materials.PaintData;
import shpp.com.models.materials.PrimerData;
import shpp.com.models.workpiece.Workpiece;
import shpp.com.util.CSVParser;

/**
 * Створює набір грунтових і лакофарбових матеріалів, які відповідають схемі покриття
 */
@Slf4j
public class SchemaData {

  private final MetalFraction metalFraction;
  private final List<PrimerData> primersData;
  private final PaintData paintData;

  private static final String FILE_PRIMER = "primer";
  private static final String FILE_PAINT = "paint";


  public SchemaData(Workpiece workpiece) {
    this.primersData = setPrimersData(getPrimersDataFromFile(getParsFile(FILE_PRIMER), workpiece));
    this.paintData = setPaintsData(getPaintsDataFromFile(getParsFile(FILE_PAINT), workpiece));
    this.metalFraction = setMetalFractionData(workpiece);
  }

  private MetalFraction setMetalFractionData(Workpiece workpiece) {
    if(workpiece.getShotBlastingFlag()){
      return new MetalFraction(workpiece.getCoverageArea());
    }
    return null;
  }

  public List<PrimerData> getPrimersData() {
    return primersData;
  }
  public PaintData getPaintData() {
    return paintData;
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

  /**
   * Метод робить вибірку грунта по матеріалу заготовки і повертає список грунтів із
   * відповідними строками
   *
   * @param list - розпарсена таблиця грунтових матеріалів
   * @param workpiece - заготовка із набором відповідних характеристик
   * @return - список строк List<String[]> із характеристиками відповідних грунтових матеріалів
   */
  private List<String[]> getPrimersDataFromFile(List<String[]> list, Workpiece workpiece) {
    List<String[]> data = new ArrayList<>();
    for (String[] strings : list) {
      if (strings[0].equals(workpiece.getMaterial().getMaterial())) {
        data.add(strings);
      }
    }
    return data;
  }

  /**
   * Метод створює із відповідних строк файла-таблиці, список даних із відповідних грунтів,
   * що містить відповідні характеристики
   *
   * @param primersDataFromFile - список строк із характеристиками відповідних грунтових матеріалів
   * @return - список грунтових матеріалів List<PrimerData> із нборами відповідних характеристик
   */
  private List<PrimerData> setPrimersData(List<String[]> primersDataFromFile) {
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
    return dataList;
  }

  /**
   * Метод виконує вибір фарбового матеріалу і повертає відповідну строку таблиці із файла
   * @param list - розпарсена таблиця фарбових матеріалів
   * @param workpiece - заготовка із набором характеристик
   * @return - строка із характеристиками відповідного фарбового матеріалу
   */
  private String[] getPaintsDataFromFile(List<String[]> list, Workpiece workpiece) {
    String[] data = new String[0];
    for (String[] strings : list) {
      if (strings[0].equals(workpiece.getRal().getRal())) {
        data = strings;
      }
    }
    return data;
  }

  /**
   * Метод створює із відповідної строки файла-таблиці, фарбовий матеріал із набором відповідних
   * характеристик
   *
   * @param paintDataFromFile - відповідна строка із характеристикамми відповідного фарбового
   *                          матеріалу
   * @return - фарбовий матеріал PaintData із набором відповідних характеристик
   */
  private PaintData setPaintsData(String[] paintDataFromFile) {
    if (paintDataFromFile.length >= 1) {
      // вибірка лише для двокомпонентних фарб
      return new PaintData()
          .setPaintName(paintDataFromFile[1])
          .setPaintMaterialCoefficient(Double.parseDouble(paintDataFromFile[2]))
          .setPaintWorkCoefficient(Double.parseDouble(paintDataFromFile[3]))
          .setPaintHardenerName(paintDataFromFile[6])
          .setPaintHardenerCoefficient(Double.parseDouble(paintDataFromFile[7]))
          .setPaintSolventName(paintDataFromFile[4])
          .setPaintSolventCoefficient(Double.parseDouble(paintDataFromFile[5]));
    } else {
      log.info("No primers in the file! Please check input data file!");
      throw new RuntimeException();
    }
  }

  public MetalFraction getMetalFraction() {
    return metalFraction;
  }
}
