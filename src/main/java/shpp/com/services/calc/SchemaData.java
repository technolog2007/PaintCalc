package shpp.com.services.calc;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import shpp.com.models.materials.PaintData;
import shpp.com.models.materials.PrimerData;
import shpp.com.models.workpiece.Mark;
import shpp.com.models.workpiece.Ral;
import shpp.com.models.workpiece.Workpiece;
import shpp.com.util.CSVParser;

/**
 * Creates a set of primer and paint materials that correspond to the coating scheme
 */
@Slf4j
public class SchemaData {

  private List<PrimerData> primersData;
  private PaintData paintData;
  private final List<String[]> parsePrimerData;
  private final List<String[]> parsePaintData;
  private final Map<Mark, Ral[]> map;

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

  public Map<Mark, Ral[]> getMap() {
    return this.map;
  }

  /**
   * The method parses the *.CSV table file and returns an array of strings
   *
   * @return - parsed table file in List<String[]>
   */
  private List<String[]> getParsFile(String fileName) {
    CSVParser parser = new CSVParser();
    return parser.parseFile(fileName);
  }

  private EnumMap<Mark, Ral[]> createMap(List<String[]> list) {
    EnumMap<Mark, Ral[]> markEnumMap = new EnumMap<>(Mark.class);
    for (int i = 0; i < Mark.values().length; i++) {
      List<Ral> arr = new ArrayList<>();
      for (String[] strings : list) {
        if (Mark.values()[i].getMarkName().equals(strings[0])) {
          for (int k = 0; k < Ral.values().length; k++) {
            if (strings[1].equals(Ral.values()[k].getRalNumber())) {
              arr.add(Ral.values()[k]);
            }
          }
        }
      }
      markEnumMap.put(Mark.values()[i], arr.toArray(new Ral[0]));
    }
    return markEnumMap;
  }

  /**
   * The method makes a sample of soil based on the material of the workpiece and returns a list of
   * soils with the corresponding terms
   *
   * @param workpiece - a workpiece with a set of appropriate characteristics
   * @return - a list of strings List<String[]> with the characteristics of the corresponding soil
   * materials
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
   * The method creates a list of data from the corresponding soils, containing the corresponding
   * characteristics, from the corresponding file-table strings
   *
   * @return - a list of primer materials List<PrimerData> with sets of corresponding
   * characteristics
   */
  public List<PrimerData> setPrimersData(Workpiece workpiece) {
    List<String[]> primersDataFromFile = getPrimersDataFromFile(workpiece);
    List<PrimerData> dataList = new ArrayList<>();
    if (!primersDataFromFile.isEmpty()) {
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
          default:
            log.info("Something wrong with \"primer\" file! Please check input data file!");
            throw new RuntimeException();
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
   * The method selects the paint material and returns the corresponding term of the table from the
   * file
   *
   * @param workpiece - workpiece with a set of characteristics
   * @return - line with the characteristics of the corresponding paint material
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
   * The method creates a paint material with a set of appropriate characteristics from the
   * appropriate term of the table file
   *
   * @return - PaintData paint material with a set of corresponding characteristics
   */
  public PaintData setPaintsData(Workpiece workpiece) {
    String[] paintDataFromFile = getPaintsDataFromFile(workpiece);
    if (paintDataFromFile.length == 9) {
      // sample only for two-component paints
      this.paintData = new PaintData()
          .setPaintName(paintDataFromFile[2])
          .setPaintMaterialCoefficient(Double.parseDouble(paintDataFromFile[3]))
          .setPaintWorkCoefficient(Double.parseDouble(paintDataFromFile[4]))
          .setPaintHardenerName(paintDataFromFile[7])
          .setPaintHardenerCoefficient(Double.parseDouble(paintDataFromFile[8]))
          .setPaintSolventName(paintDataFromFile[5])
          .setPaintSolventCoefficient(Double.parseDouble(paintDataFromFile[6]));
      return paintData;
    } else if (paintDataFromFile.length == 7) {
      // sample only for one-component paints
      this.paintData = new PaintData()
          .setPaintName(paintDataFromFile[2])
          .setPaintMaterialCoefficient(Double.parseDouble(paintDataFromFile[3]))
          .setPaintWorkCoefficient(Double.parseDouble(paintDataFromFile[4]))
          .setPaintSolventName(paintDataFromFile[5])
          .setPaintSolventCoefficient(Double.parseDouble(paintDataFromFile[6]));
      return paintData;
    } else {
      log.info("No primers in the file! Please check input data file!");
      throw new RuntimeException();
    }
  }
}