package shpp.com.models.workpiece;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Workpiece {

  double coverageArea;

  Materials material;

  Ral ral;

  Mark mark;

  boolean shotBlastingFlag = false;

  SurfaceType surfaceType = SurfaceType.METAL_CONSTRUCTION;

  double difficultyFactor = 1;

  public double getCoverageArea() {
    return coverageArea;
  }

  public Workpiece setCoverageArea(double coverageArea) {
    this.coverageArea = coverageArea;
    return this;
  }

  public Materials getMaterial() {
    return material;
  }

  public Workpiece setMaterial(Materials material) {
    this.material = material;
    return this;
  }

  public Ral getRal() {
    return ral;
  }

  public Workpiece setRal(Ral ral) {
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

  public Mark getMark() {
    return mark;
  }

  public Workpiece setMark(Mark mark) {
    this.mark = mark;
    return this;
  }

  @Override
  public String toString() {
    return "Workpiece{" +
        "coverageArea=" + coverageArea +
        ", material=" + material +
        ", ral=" + ral +
        ", mark=" + mark +
        ", shotBlastingFlag=" + shotBlastingFlag +
        ", surfaceType=" + surfaceType +
        ", difficultyFactor=" + difficultyFactor +
        '}';
  }
}
