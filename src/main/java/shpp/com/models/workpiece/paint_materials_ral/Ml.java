package shpp.com.models.workpiece.paint_materials_ral;

public enum Ml {
  SILVER("silver");

  private final String ral;

  Ml(String ral) {
    this.ral = ral;
  }

  public String getRal() {
    return ral;
  }
}
