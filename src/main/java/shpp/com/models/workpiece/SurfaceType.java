package shpp.com.models.workpiece;

public enum SurfaceType {

  METAL_CONSTRUCTION("metal construction"),
  PIPELINE("pipeline");

  private final String typeOfSurface;

  SurfaceType(String surfaceType) {
    this.typeOfSurface = surfaceType;
  }

  public String getTypeOfSurface() {
    return typeOfSurface;
  }
}
