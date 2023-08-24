package shpp.com.services;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import lombok.extern.slf4j.Slf4j;
import shpp.com.models.workpiece.Mark;
import shpp.com.models.workpiece.Materials;
import shpp.com.models.workpiece.Ral;
import shpp.com.models.workpiece.SurfaceType;
import shpp.com.models.workpiece.Workpiece;

@Slf4j
public class WorkpieceCreator {

  private static final String ERROR_MESSAGE_SHOT_BLASTING = "\nДробоструминна обробка можлива "
      + "лише для вуглецевої сталі!!!\n\nОберіть інший матерал, або відмініть вибір"
      + " дробоструменевої обробки!";
  private static final String ERROR_MESSAGE_COVERAGE_AREA = "\nПлоща покриття не може бути менша "
      + "за \"0\", або дорівнювати \"0\"!!!\n\nВведіть коректне значення площі покриття!";

  public Workpiece createWorkpiece(JTextField coverageArea, JComboBox<Materials> comboBoxMaterial,
      JComboBox<Object> comboBoxRal, JComboBox<Mark> comboBoxMark, JRadioButton shotBlasting,
      JComboBox<SurfaceType> comboBoxSurfaceType, JTextField difficultFactor) {
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

  public boolean checkInputData(JTextField coverageArea, JComboBox<Materials> comboBoxMaterial,
      JRadioButton shotBlasting, JTextField difficultFactor, JTextArea result) {
    return checkShotBlasting(shotBlasting, comboBoxMaterial, result) &&
        checkCoverageAreaIsCorrect(coverageArea, result) &&
        checkCoverageAreaValue(coverageArea, result) &&
        checkDifficultFactor(difficultFactor, result);
  }

  private boolean checkShotBlasting(JRadioButton shotBlasting,
      JComboBox<Materials> comboBoxMaterial, JTextArea result) {
    if (shotBlasting.isSelected() && !comboBoxMaterial.getSelectedItem().equals(Materials.CARBON)) {
      log.info("Дробоструминне очищення можливе лише для вуглецевих сталей!");
      showErrorMessage(ERROR_MESSAGE_SHOT_BLASTING, result);
      return false;
    }
    return true;
  }

  private boolean checkCoverageAreaIsCorrect(JTextField coverageArea, JTextArea result) {
    try {
      Double.parseDouble(replaceComma(coverageArea.getText()));
      return true;
    } catch (NumberFormatException e) {
      log.error("Not valid input data: {}", coverageArea.getText());
      showErrorMessage(ERROR_MESSAGE_COVERAGE_AREA, result);
      return false;
    }
  }

  private boolean checkCoverageAreaValue(JTextField coverageArea, JTextArea result) {
    double area = Double.parseDouble(replaceComma(coverageArea.getText()));
    if (area <= 0) {
      showErrorMessage(ERROR_MESSAGE_COVERAGE_AREA, result);
      return false;
    }
    return true;
  }

  private boolean checkDifficultFactor(JTextField difficultFactor, JTextArea result) {
    try {
      Double.parseDouble(replaceComma(difficultFactor.getText()));
      return true;
    } catch (NumberFormatException e) {
      log.error("Not valid input data: {}", difficultFactor.getText());
      showErrorMessage("Виникла помилка із значенням difficult factor", result);
      return false;
    }
  }

  private String replaceComma(String input) {
    return input.replace(",", ".");
  }

  /**
   * Output of a message window about a data entry error
   *
   * @param message - text message containing an explanation of the error
   */
  private void showErrorMessage(String message, JTextArea result) {
    JOptionPane.showMessageDialog(null,
        "Сталася помилка. Перевірте дані.", "Помилка",
        JOptionPane.ERROR_MESSAGE);
    PrintResult.printMessage(result, message);
  }
}
