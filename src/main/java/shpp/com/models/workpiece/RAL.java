package shpp.com.models.workpiece;

public enum RAL {

  ral9017("9017"),
  ral3000("3000"),
  ral7012("7012"),
  ral1018("1018"),
  ral1002("1002"),
  ral9003("9003"),
  ;

  private final String ral;

  RAL(String ral) {
    this.ral = ral;
  }

  public String getRal() {
    return ral;
  }
}
