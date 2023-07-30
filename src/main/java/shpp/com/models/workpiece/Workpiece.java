package shpp.com.models.workpiece;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Workpiece {

  double coverageArea;

  Material material;

  RAL ral;

  boolean shotBlastingFlag = false;

  SurfaceType surfaceType = SurfaceType.metalConstruction;

  double difficultyFactor = 1;

  public Workpiece(double coverageArea, Material material, RAL ral) {
    this.coverageArea = coverageArea;
    this.material = material;
    this.ral = ral;
  }

  public Workpiece(double coverageArea, Material material, RAL ral, boolean shotBlastingFlag) {
    this.coverageArea = coverageArea;
    this.material = material;
    this.ral = ral;
    this.shotBlastingFlag = shotBlastingFlag;
  }

  public double getCoverageArea() {
    return coverageArea;
  }

  public Workpiece setCoverageArea(double coverageArea) {
    this.coverageArea = coverageArea;
    return this;
  }

  public Material getMaterial() {
    return material;
  }

  public Workpiece setMaterial(Material material) {
    this.material = material;
    return this;
  }

  public RAL getRal() {
    return ral;
  }

  public Workpiece setRal(RAL ral) {
    this.ral = ral;
    return this;
  }

  public boolean getShotBlastingFlag() {
    return shotBlastingFlag;
  }

  public Workpiece setShotBlastingFlag(boolean shotBlastingFlag) {
    this.shotBlastingFlag = shotBlastingFlag;
    return this;
  }

  public SurfaceType getSurfaceType() {
    return surfaceType;
  }

  public Workpiece setSurfaceType(SurfaceType surfaceType) {
    this.surfaceType = surfaceType;
    return this;
  }

  public double getDifficultyFactor() {
    return difficultyFactor;
  }

  public Workpiece setDifficultyFactor(int difficultyFactor) {
    this.difficultyFactor = difficultyFactor;
    return this;
  }

  @Override
  public String toString() {
    return "Workpiece{" +
        "coverageArea=" + coverageArea +
        ", material=" + material +
        ", ral=" + ral +
        ", shotBlastingFlag=" + shotBlastingFlag +
        ", surfaceType=" + surfaceType +
        ", difficultyFactor=" + difficultyFactor +
        '}';
  }
}
