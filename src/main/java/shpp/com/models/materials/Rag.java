package shpp.com.models.materials;

public class Rag {

  private final String name = "Ганчір'я ...";
  private final double coefficient = 0.05; // уточнити коефіцієнт
  private double norm;

  public String getName() {
    return name;
  }

  public Rag setNorm(double norm) {
    this.norm = norm;
    return this;
  }

  public double getCoefficient() {
    return coefficient;
  }

  public double getNorm() {
    return norm;
  }
}
