package shpp.com.models.workpiece;

public enum SurfaceType {

  metalConstruction("metal construction"),
  pipeline("pipeline");

  private final String surfaceType;

  SurfaceType(String surfaceType) {
    this.surfaceType = surfaceType;
  }

  public String getSurfaceType() {
    return surfaceType;
  }
}
