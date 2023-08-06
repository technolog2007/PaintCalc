package shpp.com.models.workpiece;

public enum Mark {

  HELIOS("Helios"),
  UR("УР"),
  KO("КО"),
  ML("МЛ");
  private final String markName;

  Mark(String ral) {
    this.markName = ral;
  }

  public String getMarkName() {
    return markName;
  }
}
