package shpp.com.services;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import lombok.extern.slf4j.Slf4j;
import shpp.com.models.materials.MetalFraction;
import shpp.com.models.materials.PaintNorm;
import shpp.com.models.materials.PrimerNorm;
import shpp.com.models.materials.Solvent647;

@Slf4j
public class PrintResult {

  private List<List<String>> primerResult;
  private List<String> paintResult;
  private List<String> shotBlastingResult;
  private String solventResult;

  public PrintResult() {
    this.primerResult = new ArrayList<>();
    this.paintResult = new ArrayList<>();
    this.shotBlastingResult = new ArrayList<>();
  }

  private String printSolvent(Solvent647 solvent647) {
    return solvent647.getName() + " --> " + solvent647.getNorm() + " кг";
  }

  private ArrayList<String> printShotBlasting(MetalFraction metalFraction) {
    ArrayList<String> result = new ArrayList<>();
    if (metalFraction != null) {
      String resultMaterialNorm =
          metalFraction.getMetalFractionName() + " --> "
              + metalFraction.getMetalFractionMaterialNorm()
              + " кг";
      result.add(resultMaterialNorm);
      String resultWorkNorm =
          metalFraction.getMetalFractionName() + " --> " + metalFraction.getMetalFractionWorkNorm()
              + " хв";
      result.add(resultWorkNorm);
    } else {
      result.add("Дробоструменеве очищення не потрібне!");
    }
    return result;
  }

  private ArrayList<String> printPaint(PaintNorm paintNorm) {
    ArrayList<String> result = new ArrayList<>();
    String resultMaterialNorm =
        paintNorm.getPaint().getPaintName() + " --> " + paintNorm.getPaintMaterialNorm() + " л";
    result.add(resultMaterialNorm);
    String resultWorkNorm =
        paintNorm.getPaint().getPaintName() + " --> " + paintNorm.getPaintWorkNorm() + " хв";
    result.add(resultWorkNorm);
    String resultSolventNorm =
        paintNorm.getPaint().getPaintSolventName() + " --> " + paintNorm.getPaintSolventNorm() + " л";
    result.add(resultSolventNorm);
    String resultHardenerNorm =
        paintNorm.getPaint().getPaintHardenerName() + " --> " + paintNorm.getPaintHardenerNorm()
            + " л";
    result.add(resultHardenerNorm);
    return result;
  }

  private List<String> printPrimer(PrimerNorm primerNorm) {
    List<String> result = new ArrayList<>();
    String resultMaterialNorm =
        primerNorm.getPrimerData().getPrimerName() + " --> " + primerNorm.getPrimerMaterialNorm()
            + " л";
    result.add(resultMaterialNorm);
    String resultWorkNorm =
        primerNorm.getPrimerData().getPrimerName() + " --> " + primerNorm.getPrimerWorkNorm()
            + " хв";
    result.add(resultWorkNorm);
    String resultSolventNorm =
        primerNorm.getPrimerData().getPrimerSolventName() + " --> "
            + primerNorm.getPrimerSolventNorm()
            + " л";
    result.add(resultSolventNorm);
    String resultHardenerNorm =
        primerNorm.getPrimerData().getPrimerHardenerName() + " --> "
            + primerNorm.getPrimerHardenerNorm()
            + " л";
    result.add(resultHardenerNorm);
    return result;
  }

  private List<List<String>> printPrimers(List<PrimerNorm> primerNormList) {
    List<List<String>> result = new ArrayList<>();
    for (PrimerNorm primerNorm : primerNormList) {
      result.add(printPrimer(primerNorm));
    }
    return result;
  }

  public void printAll(List<PrimerNorm> primerNormList, PaintNorm paintNorm,
      MetalFraction metalFraction, Solvent647 solvent647) {
    this.primerResult = printPrimers(primerNormList);
    this.paintResult = printPaint(paintNorm);
    this.shotBlastingResult = printShotBlasting(metalFraction);
    this.solventResult = printSolvent(solvent647);
  }

  public void printAllResult(JTextArea result) {
    String resultSummary = "";
    // print in console primers norm
    for (int i = 0; i < primerResult.size(); i++) {
      for (int j = 0; j < primerResult.get(i).size(); j++) {
        resultSummary =resultSummary + primerResult.get(i).get(j) + "\n";
        log.info(primerResult.get(i).get(j));
      }
    }
    // print in console print norm
    for (int i = 0; i < paintResult.size(); i++) {
      resultSummary = resultSummary + paintResult.get(i) + "\n";
      log.info(paintResult.get(i));
    }
    // print in console shot blasting norm
    for (int i = 0; i < shotBlastingResult.size(); i++) {
      resultSummary = resultSummary + shotBlastingResult.get(i) + "\n";
      log.info(shotBlastingResult.get(i));
    }
    // print in console solvent norm
    log.info(solventResult);
    resultSummary = resultSummary + solventResult + "\n";
    log.info("RESULT : {}", resultSummary);
    result.setText(resultSummary);
  }
}
