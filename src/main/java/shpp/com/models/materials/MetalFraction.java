package shpp.com.models.materials;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
public class MetalFraction {

  private String metalFractionName = "metal fraction diameter 50";
  private double metalFractionMaterialCoefficient = 0.3;
  private double metalFractionWorkCoefficient = 10.0;
  private double metalFractionMaterialNorm;
  private double metalFractionWorkNorm;

  public MetalFraction(double coverageArea){
    this.metalFractionMaterialNorm = this.metalFractionMaterialCoefficient * coverageArea;
    this.metalFractionWorkNorm = this.metalFractionWorkCoefficient * coverageArea;
  }
  public String getMetalFractionName() {
    return metalFractionName;
  }

  public MetalFraction setMetalFractionName(String metalFractionName) {
    this.metalFractionName = metalFractionName;
    return this;
  }

  public double getMetalFractionMaterialCoefficient() {
    return metalFractionMaterialCoefficient;
  }

  public MetalFraction setMetalFractionMaterialCoefficient(
      double metalFractionMaterialCoefficient) {
    this.metalFractionMaterialCoefficient = metalFractionMaterialCoefficient;
    return this;
  }

  public double getMetalFractionWorkCoefficient() {
    return metalFractionWorkCoefficient;
  }

  public MetalFraction setMetalFractionWorkCoefficient(double metalFractionWorkCoefficient) {
    this.metalFractionWorkCoefficient = metalFractionWorkCoefficient;
    return this;
  }

  public double getMetalFractionMaterialNorm() {
    return metalFractionMaterialNorm;
  }

  public MetalFraction setMetalFractionMaterialNorm(double metalFractionMaterialNorm) {
    this.metalFractionMaterialNorm = metalFractionMaterialNorm;
    return this;
  }

  public double getMetalFractionWorkNorm() {
    return metalFractionWorkNorm;
  }

  public MetalFraction setMetalFractionWorkNorm(double metalFractionWorkNorm) {
    this.metalFractionWorkNorm = metalFractionWorkNorm;
    return this;
  }

  @Override
  public String toString() {
    return "MetalFraction{" +
        "metalFractionName='" + metalFractionName + '\'' +
        ", metalFractionMaterialCoefficient=" + metalFractionMaterialCoefficient +
        ", metalFractionWorkCoefficient=" + metalFractionWorkCoefficient +
        ", metalFractionMaterialNorm=" + metalFractionMaterialNorm +
        ", metalFractionWorkNorm=" + metalFractionWorkNorm +
        '}';
  }
}
