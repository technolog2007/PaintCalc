package shpp.com.graf;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import lombok.extern.slf4j.Slf4j;
import shpp.com.models.workpiece.Material;
import shpp.com.models.workpiece.RAL;
import shpp.com.models.workpiece.SurfaceType;
import shpp.com.models.workpiece.Workpiece;
import shpp.com.services.WorkpieceCreator;

@Slf4j
public class Window {

  private JTextField coverageArea;
  private JTextField difficultFactor;
  private JTextField result;
  private JRadioButton shotBlasting;
  private JButton calculate;
  private JComboBox<Material> comboBoxMaterial = new JComboBox<>(Material.values());
  private JComboBox<RAL> comboBoxRal = new JComboBox<>(RAL.values());
  private JComboBox<SurfaceType> comboBoxSurfaceType = new JComboBox<>(SurfaceType.values());

  private static final String FONT = "Arial";

  public void createWindow() {
    // create jFrame
    JFrame jFrame = new JFrame("Paint calc");
    // створюємо основні елементи графічного вікна діалогу
    createLabels(jFrame);
    createTextFields(jFrame);
    createRadioButtons(jFrame);
    createComboBoxes(jFrame);
    this.calculate = createButton("RESULT", 200, 200, 100, 30);
    jFrame.add(calculate);
    calculate.addActionListener(new ButtonListener());

    // встановлюємо параметри графічного вікна
    jFrame.setLayout(null);
    jFrame.setSize(500, 510);
    jFrame.setVisible(true);
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setResizable(false);
  }

  private JButton createButton(String name, int x, int y, int width, int height) {
    JButton button = new JButton();
    button.setBounds(x, y, width, height);
    button.setFont(new Font(FONT, Font.PLAIN, 15));
    button.setText(name);
    return button;
  }

  private JLabel createLabel(String label, int x, int y, int width, int height) {
    JLabel jLabel = new JLabel(label);
    jLabel.setBounds(x, y, width, height);
    jLabel.setFont(new Font(FONT, Font.PLAIN, 16));
    return jLabel;
  }

  private JTextField createTextFieldForInput(int x, int y, int width, int height, int fontSize,
      String text) {
    JTextField textField = new JTextField(text);
    textField.setBounds(x, y, width, height);
    textField.setFont(new Font(FONT, Font.PLAIN, fontSize));
    textField.setHorizontalAlignment(JTextField.CENTER);
    return textField;
  }

  /**
   * Метод створює текстові лейбли і відображує їх у графічному вікні
   *
   * @param jFrame - графічне вікно
   */
  private void createLabels(JFrame jFrame) {
    jFrame.add(createLabel("Material:", 10, 10, 100, 30));
    jFrame.add(createLabel("RAL:", 10, 40, 100, 30));
    jFrame.add(createLabel("Surface type:", 10, 70, 150, 30));
    jFrame.add(createLabel("Coverage area:", 10, 100, 150, 30));
    jFrame.add(createLabel("Difficult Factor:", 10, 130, 150, 30));
    jFrame.add(createLabel("Shot blasting:", 10, 160, 150, 30));
  }

  /**
   * Метод створює текстові поля для ручного вводу даних на графічному полі
   *
   * @param jFrame - графічне вікно
   */
  private void createTextFields(JFrame jFrame) {
    this.coverageArea = createTextFieldForInput(150, 103, 80, 30, 16, "0");
    jFrame.add(coverageArea);
    this.difficultFactor = createTextFieldForInput(150, 133, 80, 30, 16, "1");
    jFrame.add(difficultFactor);
    this.result = createTextFieldForInput(10, 240, 465, 220, 16, "");
    jFrame.add(result);
  }

  private JRadioButton createRadioButton(boolean select, int x, int y, int width, int height){
    JRadioButton radioButton = new JRadioButton();
    radioButton.setBounds(180, 163, 30, 30);
    radioButton.setSelected(select);
    return radioButton;
  }

  private void createRadioButtons(JFrame jFrame) {
    this.shotBlasting = createRadioButton(false, 180, 163, 30, 30);
    jFrame.add(shotBlasting);
  }

  private void createComboBoxes(JFrame jFrame) {
    comboBoxMaterial.setBounds(150, 10, 100, 30);
    jFrame.add(comboBoxMaterial);
    comboBoxRal.setBounds(150, 40, 100, 30);
    comboBoxRal.setSelectedIndex(1);
    jFrame.add(comboBoxRal);
    comboBoxSurfaceType.setBounds(150, 70, 100, 30);
    jFrame.add(comboBoxSurfaceType);
  }

  class ButtonListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      WorkpieceCreator creator = new WorkpieceCreator();
      Workpiece workpiece = creator.createWorkpiece(coverageArea, comboBoxMaterial, comboBoxRal, shotBlasting, comboBoxSurfaceType, difficultFactor);
      log.info("show workpiece: {}", workpiece.toString());
    }
  }


}
