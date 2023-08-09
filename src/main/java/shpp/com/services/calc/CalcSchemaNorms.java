package shpp.com.services.calc;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import shpp.com.models.PaintNorm;
import shpp.com.models.PrimerNorm;
import shpp.com.models.materials.PrimerData;
import shpp.com.models.workpiece.Workpiece;

@Slf4j
public class CalcSchemaNorms {

  private final List<PrimerNorm> primerNorm;
  private final PaintNorm paintNorm;
  private final SchemaData schemaData;

  public CalcSchemaNorms(Workpiece workpiece) {
    this.schemaData = new SchemaData();
    this.schemaData.setPrimersData(workpiece);
    this.schemaData.setPaintsData(workpiece);
    this.primerNorm = setPrimerDataToPrimerNorm();
    this.paintNorm = setPaintToPaintNorm();
  }

  /**
   * The method accepts the data of the paint schema, creates a List<PrimerNorm>
   *   and sets the input data (PrimerData)
   * @return - List<PrimerNorm>
   */
  private List<PrimerNorm> setPrimerDataToPrimerNorm() {
    List<PrimerNorm> primerNormList = new ArrayList<>();
    List<PrimerData> primersDataList = this.schemaData.getPrimersData();
    for (int i = 0; i < primersDataList.size(); i++) {
      primerNormList.add(new PrimerNorm().setPrimerData(schemaData.getPrimersData().get(i)));
    }
    return primerNormList;
  }

  /**
   * The method takes a paint scheme and returns a PaintNorm that contains
   * the corresponding PaintData
   * @return - PaintNorm
   */
  private PaintNorm setPaintToPaintNorm() {
    return new PaintNorm().setPaint(schemaData.getPaintData());
  }

  /**
   * The method calculates the necessary amount of primer's base and auxiliary materials
   * for preparing the mixture (solvent snd hardener), as well as the time required for
   * applying this coating
   * @param primerNorm - calculation norms for primer use
   * @param workpiece - a workpiece containing a set of input data for calculation
   */
  public void calcPrimersNorm(List<PrimerNorm> primerNorm, Workpiece workpiece) {
    calcPrimersMaterialNorm(primerNorm, workpiece.getCoverageArea());
    calcPrimersWorkNorm(primerNorm, workpiece.getCoverageArea());
    calcPrimersSolventNorm(primerNorm);
    calcPrimersHardenerNorm(primerNorm);
  }

  /**
   * The method calculates the rate of consumption of the primer`s base depending
   * on the coverage area
   *
   * @param primerNorm   - norms containing input data on primer
   * @param coverageArea - coverage area
   */
  private void calcPrimersMaterialNorm(List<PrimerNorm> primerNorm, double coverageArea) {
    primerNorm.replaceAll(norm -> norm
        .setPrimerMaterialNorm(norm.getPrimerData().getPrimerMaterialCoefficient()
            * coverageArea));
  }

  /**
   * The method calculates the rate of time consumption for priming the coverage area
   *
   * @param primerNorm   - norms containing input data on primer
   * @param coverageArea - coverage area
   */
  private void calcPrimersWorkNorm(List<PrimerNorm> primerNorm, double coverageArea) {
    primerNorm.replaceAll(norm -> norm
        .setPrimerWorkNorm(norm.getPrimerData().getPrimerWorkCoefficient()
            * coverageArea));
  }

  /**
   * The method calculates the solvent for the primer`s base
   *
   * @param primerNorm - norms containing input data on primer
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
   * The method calculates the hardener for the primer`s base
   *
   * @param primerNorm - norms containing input data on primer
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

  /**
   * The method calculates the consumption rates of the paint base,
   * auxiliary materials (solvent and hardener), as well as the time consumption rate
   * for applying this coating
   *
   * @param paintNorm - norms containing input data on paint
   * @param workpiece - a workpiece containing a set of input data for calculation
   */
  public void calcPaintNorm(PaintNorm paintNorm, Workpiece workpiece) {
    calcPaintMaterialNorm(paintNorm, workpiece.getCoverageArea());
    calcPaintWorkNorm(paintNorm, workpiece.getCoverageArea());
    calcPaintSolventNorm(paintNorm);
    calcPaintHardenerNorm(paintNorm);
  }

  /**
   * The method calculates the amount of paint base for the specified coverage area
   *
   * @param paintNorm - norms containing input data on paint
   * @param coverageArea - coverage area
   */
  private void calcPaintMaterialNorm(PaintNorm paintNorm, double coverageArea) {
    paintNorm.setPaintMaterialNorm(
        paintNorm.getPaint().getPaintMaterialCoefficient() * coverageArea);
  }

  /**
   * The method calculates the amount of time required to cover a coverage area with primer
   *
   * @param paintNorm - norms containing input data on paint
   * @param coverageArea - coverage area
   */
  private void calcPaintWorkNorm(PaintNorm paintNorm, double coverageArea) {
    paintNorm.setPaintWorkNorm(paintNorm.getPaint().getPaintWorkCoefficient() * coverageArea);
  }

  /**
   * The method calculates the amount of solvent depending on the amount of paint base
   *
   * @param paintNorm - norms containing input data on paint
   */
  private void calcPaintSolventNorm(PaintNorm paintNorm) {
    if (paintNorm.getPaint().getPaintSolventCoefficient() != null) {
      paintNorm.setPaintSolventNorm(
          paintNorm.getPaint().getPaintSolventCoefficient() * paintNorm.getPaintMaterialNorm());
    }
  }

  /**
   * A method that calculates the amount of hardener depending on the amount of paint base
   * @param paintNorm - norms containing input data on paint
   */
  private void calcPaintHardenerNorm(PaintNorm paintNorm) {
    if (paintNorm.getPaint().getPaintHardenerCoefficient() != null) {
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
