package shpp.com.models.materials;

public enum Units {
  KILOGRAM("кг"),
  LITER("л"),
  MINUTE("хв");

  private final String unit;

  Units(String material) {
    this.unit = material;
  }

  public String getUnit() {
    return unit;
  }
}
