package shpp.com.services;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import lombok.extern.slf4j.Slf4j;
import shpp.com.models.workpiece.Materials;
import shpp.com.models.workpiece.RAL;
import shpp.com.models.workpiece.SurfaceType;
import shpp.com.models.workpiece.Workpiece;

@Slf4j
public class WorkpieceCreator {

  public Workpiece createWorkpiece(JTextField coverageArea, JComboBox<Materials> comboBoxMaterial,
      JComboBox<RAL> comboBoxRal, JRadioButton shotBlasting,
      JComboBox<SurfaceType> comboBoxSurfaceType,
      JTextField difficultFactor) {
    return new Workpiece(
        Double.parseDouble(coverageArea.getText()),
        (Materials) comboBoxMaterial.getSelectedItem(),
        (RAL) comboBoxRal.getSelectedItem(),
        shotBlasting.isSelected(),
        (SurfaceType) comboBoxSurfaceType.getSelectedItem(),
        Double.parseDouble(difficultFactor.getText())
    );
  }
}
