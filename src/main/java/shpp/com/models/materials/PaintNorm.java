package shpp.com.models.materials;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PaintNorm {

  PaintData paintData;
  double paintMaterialNorm;
  double paintWorkNorm;
  double paintSolventNorm;
  double paintHardenerNorm;

  public PaintData getPaint() {
    return paintData;
  }

  public PaintNorm setPaint(PaintData paintData) {
    this.paintData = paintData;
    return this;
  }

  public double getPaintMaterialNorm() {
    return paintMaterialNorm;
  }

  public PaintNorm setPaintMaterialNorm(double paintMaterialNorm) {
    this.paintMaterialNorm = paintMaterialNorm;
    return this;
  }

  public double getPaintWorkNorm() {
    return paintWorkNorm;
  }

  public PaintNorm setPaintWorkNorm(double paintWorkNorm) {
    this.paintWorkNorm = paintWorkNorm;
    return this;
  }

  public double getPaintSolventNorm() {
    return paintSolventNorm;
  }

  public PaintNorm setPaintSolventNorm(double paintSolventNorm) {
    this.paintSolventNorm = paintSolventNorm;
    return this;
  }

  public double getPaintHardenerNorm() {
    return paintHardenerNorm;
  }

  public PaintNorm setPaintHardenerNorm(double paintHardenerNorm) {
    this.paintHardenerNorm = paintHardenerNorm;
    return this;
  }

  @Override
  public String toString() {
    return "PaintNorm{" +
        "paint=" + paintData +
        ", paintMaterialNorm=" + paintMaterialNorm +
        ", paintWorkNorm=" + paintWorkNorm +
        ", paintSolventNorm=" + paintSolventNorm +
        ", paintHardenerNorm=" + paintHardenerNorm +
        '}';
  }
}
