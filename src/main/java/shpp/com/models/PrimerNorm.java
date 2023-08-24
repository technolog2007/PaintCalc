package shpp.com.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import shpp.com.models.materials.PrimerData;

@NoArgsConstructor
@AllArgsConstructor
public class PrimerNorm {

  PrimerData primerData;
  double primerMaterialNorm;
  double primerWorkNorm;
  double primerSolventNorm;
  double primerHardenerNorm;

  public PrimerData getPrimerData() {
    return primerData;
  }

  public PrimerNorm setPrimerData(PrimerData primerData) {
    this.primerData = primerData;
    return this;
  }

  public double getPrimerMaterialNorm() {
    return primerMaterialNorm;
  }

  public PrimerNorm setPrimerMaterialNorm(double primerMaterialNorm) {
    this.primerMaterialNorm = primerMaterialNorm;
    return this;
  }

  public double getPrimerWorkNorm() {
    return primerWorkNorm;
  }

  public PrimerNorm setPrimerWorkNorm(double primerWorkNorm) {
    this.primerWorkNorm = primerWorkNorm;
    return this;
  }

  public double getPrimerSolventNorm() {
    return primerSolventNorm;
  }

  public PrimerNorm setPrimerSolventNorm(double primerSolventNorm) {
    this.primerSolventNorm = primerSolventNorm;
    return this;
  }

  public double getPrimerHardenerNorm() {
    return primerHardenerNorm;
  }

  public PrimerNorm setPrimerHardenerNorm(double primerHardenerNorm) {
    this.primerHardenerNorm = primerHardenerNorm;
    return this;
  }

  @Override
  public String toString() {
    return "PrimerNorm{" +
        "primerData=" + primerData +
        ", primerMaterialNorm=" + primerMaterialNorm +
        ", primerWorkNorm=" + primerWorkNorm +
        ", primerSolventNorm=" + primerSolventNorm +
        ", primerHardenerNorm=" + primerHardenerNorm +
        '}';
  }
}
