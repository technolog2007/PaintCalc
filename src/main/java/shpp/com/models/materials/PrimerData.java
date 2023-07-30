package shpp.com.models.materials;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PrimerData {

  String primerName;
  Double primerMaterialCoefficient;
  Double primerWorkCoefficient;
  String primerSolventName;
  Double primerSolventCoefficient;
  String primerHardenerName;
  Double primerHardenerCoefficient;

  public String getPrimerName() {
    return primerName;
  }

  public PrimerData setPrimerName(String primerName) {
    this.primerName = primerName;
    return this;
  }

  public Double getPrimerMaterialCoefficient() {
    return primerMaterialCoefficient;
  }

  public PrimerData setPrimerMaterialCoefficient(Double primerMaterialCoefficient) {
    this.primerMaterialCoefficient = primerMaterialCoefficient;
    return this;
  }

  public Double getPrimerWorkCoefficient() {
    return primerWorkCoefficient;
  }

  public PrimerData setPrimerWorkCoefficient(Double primerWorkCoefficient) {
    this.primerWorkCoefficient = primerWorkCoefficient;
    return this;
  }

  public String getPrimerSolventName() {
    return primerSolventName;
  }

  public PrimerData setPrimerSolventName(String primerSolventName) {
    this.primerSolventName = primerSolventName;
    return this;
  }

  public Double getPrimerSolventCoefficient() {
    return primerSolventCoefficient;
  }

  public PrimerData setPrimerSolventCoefficient(Double primerSolventCoefficient) {
    this.primerSolventCoefficient = primerSolventCoefficient;
    return this;
  }

  public String getPrimerHardenerName() {
    return primerHardenerName;
  }

  public PrimerData setPrimerHardenerName(String primerHardenerName) {
    this.primerHardenerName = primerHardenerName;
    return this;
  }

  public Double getPrimerHardenerCoefficient() {
    return primerHardenerCoefficient;
  }

  public PrimerData setPrimerHardenerCoefficient(Double primerHardenerCoefficient) {
    this.primerHardenerCoefficient = primerHardenerCoefficient;
    return this;
  }

  @Override
  public String toString() {
    return "Primer{" +
        "primerName='" + primerName + '\'' +
        ", primerMaterialCoefficient=" + primerMaterialCoefficient +
        ", primerWorkCoefficient=" + primerWorkCoefficient +
        ", primerSolventName='" + primerSolventName + '\'' +
        ", primerSolventCoefficient=" + primerSolventCoefficient +
        ", primerHardenerName='" + primerHardenerName + '\'' +
        ", primerHardenerCoefficient=" + primerHardenerCoefficient +
        '}';
  }
}
