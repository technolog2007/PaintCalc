package shpp.com.models.workpiece.paint_materials_ral;

public enum Ur {
  RAL1018("1018"),
  RAL1002("1002");

  private final String ral;

  Ur(String ral) {
    this.ral = ral;
  }

  public String getRal() {
    return ral;
  }
}
