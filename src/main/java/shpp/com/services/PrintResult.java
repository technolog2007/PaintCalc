package shpp.com.services;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import lombok.extern.slf4j.Slf4j;
import shpp.com.models.PaintNorm;
import shpp.com.models.PrimerNorm;
import shpp.com.models.Units;
import shpp.com.models.materials.MetalFraction;
import shpp.com.models.materials.Rag;
import shpp.com.models.materials.Solvent647;

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
    return name + SIGN + String.format("%.3f", norm) + " " + unit.getUnit();
  }

  private String printSolvent(Solvent647 solvent647) {
    return createString(solvent647.getName(), solvent647.getNorm(), Units.KILOGRAM);
  }

  private String printRag(Rag rag) {
    if (rag != null) {
      return createString(rag.getName(), rag.getNorm(), Units.KILOGRAM);
    } else {
      return "Ганчір'я не потрібне";
    }
  }

  /**
   * The method creates an array of shot blasting calculation results
   *
   * @param metalFraction - blasting standards
   * @return - results of blasting calculations
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
   * The method creates a list of the results of the dyeing process calculation
   *
   * @param paintNorm - material standards and time standards for the painting process
   * @return - results of dyeing process calculations
   */
  private ArrayList<String> printPaint(PaintNorm paintNorm) {
    ArrayList<String> result = new ArrayList<>();
    result.add(createString(paintNorm.getPaint().getPaintName(), paintNorm.getPaintMaterialNorm(),
        Units.LITER));
    result.add(createString(paintNorm.getPaint().getPaintName(), paintNorm.getPaintWorkNorm(),
        Units.MINUTE));
    result.add(createString(paintNorm.getPaint().getPaintSolventName(),
        paintNorm.getPaintSolventNorm(), Units.LITER));
    if (paintNorm.getPaint().getPaintHardenerName() != null) {
      result.add(createString(paintNorm.getPaint().getPaintHardenerName(),
          paintNorm.getPaintHardenerNorm(), Units.LITER));
    } else {
      result.add("");
    }
    return result;
  }

  /**
   * The method creates a list of calculation results of the priming process with one soil
   *
   * @param primerNorm - material standards and time standards for the priming process
   * @return - calculation results of the priming process
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
   * The method creates a list of the calculation results of the priming process
   * for several soils (multi-layer coating)
   *
   * @param primerNormList - material standards and time standards for the priming process
   * @return - calculation results of the priming process with several soils
   */
  private List<List<String>> printPrimers(List<PrimerNorm> primerNormList) {
    List<List<String>> result = new ArrayList<>();
    for (PrimerNorm primerNorm : primerNormList) {
      result.add(printPrimer(primerNorm));
    }
    return result;
  }

  /**
   * The method creates lists of results for all materials and processes involved in the painting scheme
   *
   * @param primerNormList - material standards and time standards for the priming process
   * @param paintNorm      - material standards and time standards for the painting process
   * @param metalFraction  - material standards and time standards for shot blasting
   * @param solvent647     - flow rate for the 647th solvent
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

  /**
   * The method displays in a window the calculation results for the process of applying a
   * protective paint coating
   *
   * @param result - JTextArea
   */
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

  /**
   * The method displays the corresponding message in the text field of the result
   *
   * @param result  - JTextArea
   * @param message - text message
   */
  public static void printMessage(JTextArea result, String message) {
    result.setText(message);
  }
}
