package shpp.com.services;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import lombok.extern.slf4j.Slf4j;
import shpp.com.models.materials.MetalFraction;
import shpp.com.models.materials.PaintNorm;
import shpp.com.models.materials.PrimerNorm;
import shpp.com.models.materials.Rag;
import shpp.com.models.materials.Solvent647;
import shpp.com.models.materials.Units;

@Slf4j
public class PrintResult {

  private List<List<String>> primerResult;
  private List<String> paintResult;
  private List<String> shotBlastingResult;
  private String solventResult;
  private String ragResult;
  private static final String SIGN = " --> ";

  public PrintResult() {
    this.primerResult = new ArrayList<>();
    this.paintResult = new ArrayList<>();
    this.shotBlastingResult = new ArrayList<>();
  }

  private String createString(String name, double norm, Units unit) {
    return name + SIGN + String.format("%.3f",norm) + " " + unit.getUnit();
  }

  private String printSolvent(Solvent647 solvent647) {
    return createString(solvent647.getName(), solvent647.getNorm(), Units.KILOGRAM);
  }

  private String printRag(Rag rag) {
    if(rag != null) {
      return createString(rag.getName(), rag.getNorm(), Units.KILOGRAM);
    } else {
      return "Ганчір'я не потрібне";
    }
  }

  /**
   * Метод створює масив результатів обчислення дробоструминної обробки
   *
   * @param metalFraction - норми дробоструминної обробки
   * @return - результати обрахувань дробоструминної обробки
   */
  private ArrayList<String> printShotBlasting(MetalFraction metalFraction) {
    ArrayList<String> result = new ArrayList<>();
    if (metalFraction != null) {
      result.add(createString(metalFraction.getMetalFractionName(),
          metalFraction.getMetalFractionMaterialNorm(), Units.KILOGRAM));
      result.add(createString(metalFraction.getMetalFractionName(),
          metalFraction.getMetalFractionWorkNorm(), Units.MINUTE));
    } else {
      result.add("Дробоструминне очищення не потрібне!");
    }
    return result;
  }

  /**
   * Метод створює список результатів обчислення процесу фарбування
   *
   * @param paintNorm - норми матеріалів і норми часу для процесу фарбування
   * @return - результати обрахувань процесу фарбування
   */
  private ArrayList<String> printPaint(PaintNorm paintNorm) {
    ArrayList<String> result = new ArrayList<>();
    result.add(createString(paintNorm.getPaint().getPaintName(), paintNorm.getPaintMaterialNorm(),
        Units.LITER));
    result.add(createString(paintNorm.getPaint().getPaintName(), paintNorm.getPaintWorkNorm(),
        Units.MINUTE));
    result.add(createString(paintNorm.getPaint().getPaintSolventName(),
        paintNorm.getPaintSolventNorm(), Units.LITER));
    result.add(createString(paintNorm.getPaint().getPaintHardenerName(),
        paintNorm.getPaintHardenerNorm(), Units.LITER));
    return result;
  }

  /**
   * Метод створює список результатів обчислення процесу ґрунтування одним ґрунтом
   *
   * @param primerNorm - норми матеріалів і норми часу для процесу ґрунтування
   * @return - результати обрахувань процесу ґрунтування
   */
  private List<String> printPrimer(PrimerNorm primerNorm) {
    List<String> result = new ArrayList<>();
    result.add(createString(primerNorm.getPrimerData().getPrimerName(),
        primerNorm.getPrimerMaterialNorm(), Units.LITER));
    result.add(createString(primerNorm.getPrimerData().getPrimerName(),
        primerNorm.getPrimerWorkNorm(), Units.MINUTE));
    result.add(createString(primerNorm.getPrimerData().getPrimerSolventName(),
        primerNorm.getPrimerSolventNorm(), Units.LITER));
    result.add(createString(primerNorm.getPrimerData().getPrimerHardenerName(),
        primerNorm.getPrimerHardenerNorm(), Units.LITER));
    return result;
  }

  /**
   * Метод створює список результатів обчислення процесу ґрунтування декільками ґрунтами
   * (багатошарове покриття)
   *
   * @param primerNormList - норми матеріалів і норми часу для процесу ґрунтування
   * @return - результати обрахувань процесу ґрунтування декільками ґрунтами
   */
  private List<List<String>> printPrimers(List<PrimerNorm> primerNormList) {
    List<List<String>> result = new ArrayList<>();
    for (PrimerNorm primerNorm : primerNormList) {
      result.add(printPrimer(primerNorm));
    }
    return result;
  }

  /**
   * Метод створює списки результатів для всіх матеріалів і процесів, що задіяні в схемі фарбування
   *
   * @param primerNormList - норми матеріалів і норми часу для процесу ґрунтування
   * @param paintNorm      - норми матеріалів і норми часу для процесу фарбування
   * @param metalFraction  - норми матеріалів і норми часу для дробоструминної обробки
   * @param solvent647     - норма витрати для 647-го розчинника
   */
  public void printAll(List<PrimerNorm> primerNormList, PaintNorm paintNorm,
      MetalFraction metalFraction, Solvent647 solvent647, Rag rag) {
    this.shotBlastingResult = printShotBlasting(metalFraction);
    this.ragResult = printRag(rag);
    this.solventResult = printSolvent(solvent647);
    this.primerResult = printPrimers(primerNormList);
    this.paintResult = printPaint(paintNorm);
  }

  private String resultString(String result) {
    return result + "\n";
  }

  public void printAllResult(JTextArea result) {
    StringBuilder builder = new StringBuilder();
    for (String s : shotBlastingResult) {
      builder.append(resultString(s));
    }
    builder.append(resultString(ragResult));
    builder.append(resultString(solventResult));
    for (List<String> strings : primerResult) {
      for (String string : strings) {
        builder.append(resultString(string));
      }
    }
    for (String value : paintResult) {
      builder.append(resultString(value));
    }
    log.info("RESULT : {}", builder);
    result.setText(builder.toString());
  }
}
