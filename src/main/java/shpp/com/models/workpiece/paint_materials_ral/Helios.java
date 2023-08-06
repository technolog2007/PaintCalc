package shpp.com.models.workpiece.paint_materials_ral;

public enum Helios {

  RAL9017("9017"),
  RAL3000("3000"),
  RAL7012("7012"),
  RAL1018("1018"),
  RAL1002("1002"),
  RAL9003("9003");

  private final String ral;

  Helios(String ral) {
    this.ral = ral;
  }

  public String getRal() {
    return ral;
  }
}
