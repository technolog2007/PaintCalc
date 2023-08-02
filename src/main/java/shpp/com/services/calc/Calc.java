package shpp.com.services.calc;

import lombok.extern.slf4j.Slf4j;
import shpp.com.models.materials.MetalFraction;
import shpp.com.models.materials.Solvent647;
import shpp.com.models.workpiece.Workpiece;

@Slf4j
public class Calc {

  private final SchemaNormsCalc schemaNormsCalc;
  private MetalFraction metalFraction;
  private final Solvent647 solvent647;

  public Calc(Workpiece workpiece) {
    this.schemaNormsCalc = new SchemaNormsCalc(new SchemaData(workpiece));
    this.solvent647 = new Solvent647();
  }

  public void calcAll(Workpiece workpiece) {
    calcSchemasNorms(workpiece);
    calcMetalFraction(workpiece);
  }

  private void calcSchemasNorms(Workpiece workpiece) {
    schemaNormsCalc.calcPrimersNorm(schemaNormsCalc.getPrimerNorm(), workpiece);
    schemaNormsCalc.calcPaintNorm(schemaNormsCalc.getPaintNorm(), workpiece);
  }

  private void calcMetalFraction(Workpiece workpiece) {
    if (workpiece.getShotBlastingFlag()) {
      this.metalFraction = new MetalFraction(workpiece.getCoverageArea());
    }
  }

  public SchemaNormsCalc getSchemaNormsCalc() {
    return schemaNormsCalc;
  }

  public Solvent647 getSolvent647() {
    return solvent647;
  }

  public MetalFraction getMetalFraction() {
    return metalFraction;
  }
}
