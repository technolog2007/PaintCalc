package shpp.com.models.schema;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import shpp.com.models.materials.PaintNorm;
import shpp.com.models.materials.PrimerNorm;
@NoArgsConstructor
@AllArgsConstructor
public class PaintingSchema {

  List<PrimerNorm> primerNorms;
  List<PaintNorm> paintNorms;

  public List<PrimerNorm> getPrimerNorms() {
    return primerNorms;
  }

  public PaintingSchema setPrimerNorms(
      List<PrimerNorm> primerNorms) {
    this.primerNorms = primerNorms;
    return this;
  }

  public List<PaintNorm> getPaintNorms() {
    return paintNorms;
  }

  public PaintingSchema setPaintNorms(
      List<PaintNorm> paintNorms) {
    this.paintNorms = paintNorms;
    return this;
  }
}
