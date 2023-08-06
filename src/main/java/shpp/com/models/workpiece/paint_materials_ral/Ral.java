package shpp.com.models.workpiece.paint_materials_ral;

public enum Ral {

  RAL9017("9017"),
  RAL3000("3000"),
  RAL7012("7012"),
  RAL1018("1018"),
  RAL1002("1002"),
  RAL9003("9003"),
  SILVER("silver");

  private final String ralNumber;

  Ral(String ral) {
    this.ralNumber = ral;
  }

  public String getRalNumber() {
    return ralNumber;
  }
}
