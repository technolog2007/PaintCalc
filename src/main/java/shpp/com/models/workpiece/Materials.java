package shpp.com.models.workpiece;

public enum Materials {
  CARBON("carbon"),
  STAINLESS("stainless"),
  ALUMINUM("aluminum"),
  PLASTIC("plastic");

  private final String material;

  Materials(String material) {
    this.material = material;
  }

  public String getMaterial() {
    return material;
  }
}
