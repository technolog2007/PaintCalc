package shpp.com.services.calc;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import shpp.com.models.materials.PaintNorm;
import shpp.com.models.materials.PrimerNorm;
import shpp.com.models.workpiece.Workpiece;

@Slf4j
public class NormsCalc {

  private final List<PrimerNorm> primerNorm;
  private final PaintNorm paintNorm;

  public NormsCalc(SchemaData schemaData) {
    this.primerNorm = setPrimerDataToPrimerNorm(schemaData);
    this.paintNorm = setPaintToPaintNorm(schemaData);
  }

  private List<PrimerNorm> setPrimerDataToPrimerNorm(SchemaData schemaData) {
    List<PrimerNorm> primerNormList = new ArrayList<>();
    for (int i = 0; i < schemaData.getPrimersData().size(); i++) {
      primerNormList.add(new PrimerNorm().setPrimerData(schemaData.getPrimersData().get(i)));
    }
    return primerNormList;
  }

  private PaintNorm setPaintToPaintNorm(SchemaData schemaData) {
    return new PaintNorm().setPaint(schemaData.getPaintData());
  }

  public List<PrimerNorm> calcPrimersNorm(List<PrimerNorm> primerNorm, Workpiece workpiece) {
    // Розрахунок норми витрати для основи;
    calcPrimersMaterialNorm(primerNorm, workpiece.getCoverageArea());
    // Розрахунок норми праці;
    calcPrimersWorkNorm(primerNorm, workpiece.getCoverageArea());
    // Розрахунок норми витрати для розчинника;
    calcPrimersSolventNorm(primerNorm);
    // Розрахунок норми витрати для затверджувача;
    calcPrimersHardenerNorm(primerNorm);
    // повернути список норм для
    return primerNorm;
  }

  /**
   * Метод виконує розрахунок норми витрати основи для грунта в залежності від площі покриття
   *
   * @param primerNorm   - обраний грунт із характеристиками норм
   * @param coverageArea - площа покриття
   */
  private void calcPrimersMaterialNorm(List<PrimerNorm> primerNorm, double coverageArea) {
    for (int i = 0; i < primerNorm.size(); i++) {
      primerNorm.set(i, primerNorm.get(i)
          .setPrimerMaterialNorm(primerNorm.get(i).getPrimerData().getPrimerMaterialCoefficient()
              * coverageArea));
    }
  }

  /**
   * Метод виконує розрахунок норми витрати часу для грунтування вказаної площини
   *
   * @param primerNorm   - обраний грунт із характеристиками норм
   * @param coverageArea - площа покриття
   */
  private void calcPrimersWorkNorm(List<PrimerNorm> primerNorm, double coverageArea) {
    for (int i = 0; i < primerNorm.size(); i++) {
      primerNorm.set(i, primerNorm.get(i)
          .setPrimerWorkNorm (primerNorm.get(i).getPrimerData().getPrimerWorkCoefficient()
              * coverageArea));
    }
  }

  /**
   * Метод виконує розрахунок розчинника для вказаної основи грунта
   *
   * @param primerNorm - обраний грунт із характеристиками норм
   */
  private void calcPrimersSolventNorm(List<PrimerNorm> primerNorm) {
    for (int i = 0; i < primerNorm.size(); i++) {
      if (primerNorm.get(i).getPrimerData().getPrimerSolventCoefficient() != null) {
        primerNorm.set(i, primerNorm.get(i)
            .setPrimerSolventNorm(primerNorm.get(i).getPrimerData().getPrimerSolventCoefficient() *
                primerNorm.get(i).getPrimerMaterialNorm()));
      }
    }
  }

  /**
   * Метод виконує розрахунок затверджувача для вказаної основи грунта
   *
   * @param primerNorm - обраний грунт із характеристиками норм
   */
  private void calcPrimersHardenerNorm(List<PrimerNorm> primerNorm) {
    for (int i = 0; i < primerNorm.size(); i++) {
      if (primerNorm.get(i).getPrimerData().getPrimerHardenerCoefficient() != null) {
        primerNorm.set(i, primerNorm.get(i)
            .setPrimerHardenerNorm(primerNorm.get(i).getPrimerData().getPrimerHardenerCoefficient()
                * primerNorm.get(i).getPrimerMaterialNorm()));
      }
    }
  }

  public PaintNorm calcPaintNorm(PaintNorm paintNorm, Workpiece workpiece) {
    // Розрахунок норми витрати для основи;
    calcPaintMaterialNorm(paintNorm, workpiece.getCoverageArea());
    // Розрахунок норми праці;
    calcPaintWorkNorm(paintNorm, workpiece.getCoverageArea());
    // Розрахунок норми витрати для розчинника;
    calcPaintSolventNorm(paintNorm);
    // Розрахунок норми витрати для затверджувача;
    calcPaintHardenerNorm(paintNorm);
    // повернути список норм для
    return paintNorm;
  }

  private void calcPaintMaterialNorm(PaintNorm paintNorm, double coverageArea) {
    paintNorm.setPaintMaterialNorm(paintNorm.getPaint().getPaintMaterialCoefficient() * coverageArea);
  }

  private void calcPaintWorkNorm(PaintNorm paintNorm, double coverageArea) {
    paintNorm.setPaintWorkNorm(paintNorm.getPaint().getPaintWorkCoefficient() * coverageArea);
  }

  private void calcPaintSolventNorm(PaintNorm paintNorm) {
    if(paintNorm.getPaint().getPaintSolventCoefficient() != null) {
      paintNorm.setPaintSolventNorm(
          paintNorm.getPaint().getPaintSolventCoefficient() * paintNorm.getPaintMaterialNorm());
    }
  }

  private void calcPaintHardenerNorm(PaintNorm paintNorm) {
    if(paintNorm.getPaint().getPaintHardenerCoefficient() != null) {
      paintNorm.setPaintHardenerNorm(
          paintNorm.getPaint().getPaintHardenerCoefficient() * paintNorm.getPaintMaterialNorm());
    }
  }


  public List<PrimerNorm> getPrimerNorm() {
    return primerNorm;
  }

  public PaintNorm getPaintNorm() {
    return paintNorm;
  }
}
