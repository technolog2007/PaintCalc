package shpp.com.models.workpiece.paint_materials_ral;

public enum Ko {
  SILVER("silver");

  private final String ral;

  Ko(String ral) {
    this.ral = ral;
  }

  public String getRal() {
    return ral;
  }
}
