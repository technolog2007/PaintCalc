package shpp.com.services.calc;

import lombok.extern.slf4j.Slf4j;
import shpp.com.models.materials.MetalFraction;
import shpp.com.models.materials.Rag;
import shpp.com.models.materials.Solvent647;
import shpp.com.models.workpiece.Workpiece;

@Slf4j
public class Calc {

  private final CalcSchemaNorms schemaNormsCalc;
  private MetalFraction metalFraction;
  private final Solvent647 solvent647;
  private Rag rag;

  public Calc(Workpiece workpiece) {
    this.solvent647 = new Solvent647();
    this.schemaNormsCalc = new CalcSchemaNorms(workpiece);
  }

  public void calcAll(Workpiece workpiece) {
    calcMetalFraction(workpiece);
    calcRag(workpiece);
    calcSchemasNorms(workpiece);
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
  private void calcRag(Workpiece workpiece){
    if(!workpiece.getShotBlastingFlag()){
      this.rag = new Rag();
      this.rag = rag.setNorm(workpiece.getCoverageArea() * rag.getCoefficient());
    }
  }

  public CalcSchemaNorms getSchemaNormsCalc() {
    return schemaNormsCalc;
  }

  public Solvent647 getSolvent647() {
    return solvent647;
  }

  public MetalFraction getMetalFraction() {
    return metalFraction;
  }

  public Rag getRag() {
    return rag;
  }
}
