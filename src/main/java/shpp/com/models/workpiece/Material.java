package shpp.com.models.workpiece;

public enum Material {
  CARBON("carbon"),
  STAINLESS("stainless"),
  ALUMINUM("aluminum"),
  PLASTIC("plastic");

  private final String material;

  Material(String material) {
    this.material = material;
  }

  public String getMaterial() {
    return material;
  }
}
