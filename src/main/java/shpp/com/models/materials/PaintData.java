package shpp.com.models.materials;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PaintData {

  String paintName;
  Double paintMaterialCoefficient;
  Double paintWorkCoefficient;
  String paintSolventName;
  Double paintSolventCoefficient;
  String paintHardenerName;
  Double paintHardenerCoefficient;

  public String getPaintName() {
    return paintName;
  }

  public PaintData setPaintName(String paintName) {
    this.paintName = paintName;
    return this;
  }

  public Double getPaintMaterialCoefficient() {
    return paintMaterialCoefficient;
  }

  public PaintData setPaintMaterialCoefficient(Double paintMaterialCoefficient) {
    this.paintMaterialCoefficient = paintMaterialCoefficient;
    return this;
  }

  public Double getPaintWorkCoefficient() {
    return paintWorkCoefficient;
  }

  public PaintData setPaintWorkCoefficient(Double paintWorkCoefficient) {
    this.paintWorkCoefficient = paintWorkCoefficient;
    return this;
  }

  public String getPaintSolventName() {
    return paintSolventName;
  }

  public PaintData setPaintSolventName(String paintSolventName) {
    this.paintSolventName = paintSolventName;
    return this;
  }

  public Double getPaintSolventCoefficient() {
    return paintSolventCoefficient;
  }

  public PaintData setPaintSolventCoefficient(Double paintSolventCoefficient) {
    this.paintSolventCoefficient = paintSolventCoefficient;
    return this;
  }

  public String getPaintHardenerName() {
    return paintHardenerName;
  }

  public PaintData setPaintHardenerName(String paintHardenerName) {
    this.paintHardenerName = paintHardenerName;
    return this;
  }

  public Double getPaintHardenerCoefficient() {
    return paintHardenerCoefficient;
  }

  public PaintData setPaintHardenerCoefficient(Double paintHardenerCoefficient) {
    this.paintHardenerCoefficient = paintHardenerCoefficient;
    return this;
  }

  @Override
  public String toString() {
    return "Paint{" +
        "paintName='" + paintName + '\'' +
        ", paintMaterialCoefficient=" + paintMaterialCoefficient +
        ", paintWorkCoefficient=" + paintWorkCoefficient +
        ", paintSolventName='" + paintSolventName + '\'' +
        ", paintSolventCoefficient=" + paintSolventCoefficient +
        ", paintHardenerName='" + paintHardenerName + '\'' +
        ", paintHardenerCoefficient=" + paintHardenerCoefficient +
        '}';
  }
}
