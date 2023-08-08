package shpp.com.services;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import lombok.extern.slf4j.Slf4j;
import shpp.com.models.workpiece.Mark;
import shpp.com.models.workpiece.Materials;
import shpp.com.models.workpiece.Ral;
import shpp.com.models.workpiece.SurfaceType;
import shpp.com.models.workpiece.Workpiece;

@Slf4j
public class WorkpieceCreator {

  public Workpiece createWorkpiece(JTextField coverageArea, JComboBox<Materials> comboBoxMaterial,
      JComboBox<Object> comboBoxRal, JComboBox<Mark> comboBoxMark, JRadioButton shotBlasting,
      JComboBox<SurfaceType> comboBoxSurfaceType,
      JTextField difficultFactor) {
    return new Workpiece(
        Double.parseDouble(coverageArea.getText()),
        (Materials) comboBoxMaterial.getSelectedItem(),
        (Ral) comboBoxRal.getSelectedItem(),
        (Mark) comboBoxMark.getSelectedItem(),
        shotBlasting.isSelected(),
        (SurfaceType) comboBoxSurfaceType.getSelectedItem(),
        Double.parseDouble(difficultFactor.getText())
    );
  }
}
