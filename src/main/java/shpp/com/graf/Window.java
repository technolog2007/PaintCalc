package shpp.com.graf;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import lombok.extern.slf4j.Slf4j;
import shpp.com.models.workpiece.Mark;
import shpp.com.models.workpiece.Materials;
import shpp.com.models.workpiece.Ral;
import shpp.com.models.workpiece.SurfaceType;
import shpp.com.models.workpiece.Workpiece;
import shpp.com.services.PrintResult;
import shpp.com.services.WorkpieceCreator;
import shpp.com.services.calc.Calc;
import shpp.com.services.calc.SchemaData;

@Slf4j
public class Window {

  private JTextField coverageArea;
  private JTextField difficultFactor;
  private JTextArea result;
  private JRadioButton shotBlasting;
  private final JComboBox<Materials> comboBoxMaterial = new JComboBox<>(Materials.values());
  private final JComboBox<Mark> comboBoxMark = new JComboBox<>(Mark.values());
  private JComboBox<Object> comboBoxRal;
  private final Map<Mark, Ral[]> map = new SchemaData().getMap();
  private final JComboBox<SurfaceType> comboBoxSurfaceType = new JComboBox<>(SurfaceType.values());
  private static final String FONT = "Arial";

  public void createWindow() {
    // create jFrame
    JFrame jFrame = new JFrame("Paint calc");
    // створюємо основні елементи графічного вікна діалогу
    createLabels(jFrame);
    createTextFields(jFrame);
    createRadioButtons(jFrame);
    createComboBoxes(jFrame);
    JButton calculate = createButton("CALCULATE", 285, 200, 130, 30);
    jFrame.add(calculate);
    calculate.addActionListener(new ButtonListener());

    // встановлюємо параметри графічного вікна
    jFrame.setLayout(null);
    jFrame.setSize(700, 510);
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

  private JTextArea createTextArea(int x, int y, int width, int height, int fontSize,
      String text) {
    JTextArea textArea = new JTextArea(text);
    textArea.setBounds(x, y, width, height);
    textArea.setFont(new Font(FONT, Font.PLAIN, fontSize));
    return textArea;
  }

  /**
   * Метод створює текстові лейбли і відображує їх у графічному вікні
   *
   * @param jFrame - графічне вікно
   */
  private void createLabels(JFrame jFrame) {
    jFrame.add(createLabel("Material:", 10, 10, 100, 30));
    jFrame.add(createLabel("Mark:", 10, 45, 100, 30));
    jFrame.add(createLabel("RAL:", 10, 80, 100, 30));
    jFrame.add(createLabel("Surface type:", 10, 115, 150, 30));
    jFrame.add(createLabel("Difficult Factor:", 260, 10, 150, 30));
    jFrame.add(createLabel("Coverage area:", 260, 45, 150, 30));
    jFrame.add(createLabel("Shot blasting:", 10, 150, 150, 30));
  }

  /**
   * Метод створює текстові поля для ручного вводу даних на графічному полі
   *
   * @param jFrame - графічне вікно
   */
  private void createTextFields(JFrame jFrame) {
    this.difficultFactor = createTextFieldForInput(420, 10, 80, 30, 16, "1");
    jFrame.add(difficultFactor);
    this.coverageArea = createTextFieldForInput(420, 45, 80, 30, 16, "0");
    jFrame.add(coverageArea);
    this.result = createTextArea(10, 240, 665, 220, 14, "");
    jFrame.add(result);
  }

  private JRadioButton createRadioButton(boolean select, int x, int y, int width, int height) {
    JRadioButton radioButton = new JRadioButton();
    radioButton.setBounds(x, y, width, height);
    radioButton.setSelected(select);
    return radioButton;
  }

  private void createRadioButtons(JFrame jFrame) {
    this.shotBlasting = createRadioButton(false, 180, 152, 30, 30);
    jFrame.add(shotBlasting);
  }

  private void createComboBoxes(JFrame jFrame) {
    comboBoxMaterial.setBounds(150, 10, 100, 30);
    jFrame.add(comboBoxMaterial);
    comboBoxMark.setBounds(150, 45, 100, 30);
    comboBoxMark.setSelectedIndex(0);
    jFrame.add(comboBoxMark);
    comboBoxRal = new JComboBox<>(map.get(comboBoxMark.getSelectedItem()));
    comboBoxRal.setBounds(150, 80, 100, 30);
    comboBoxRal.setSelectedIndex(0);
    jFrame.add(comboBoxRal);
    comboBoxSurfaceType.setBounds(150, 115, 100, 30);
    jFrame.add(comboBoxSurfaceType);

    comboBoxMark.addActionListener(e -> {
      Mark mark = (Mark) comboBoxMark.getSelectedItem();
      Ral[] ralListForMark = map.get(mark);
      comboBoxRal.setModel(new DefaultComboBoxModel<>(ralListForMark));
    });
  }


  class ButtonListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      WorkpieceCreator creator = new WorkpieceCreator();
      Workpiece workpiece = creator.createWorkpiece(coverageArea, comboBoxMaterial, comboBoxRal,
          comboBoxMark, shotBlasting, comboBoxSurfaceType, difficultFactor);
      log.info("show workpiece: {}", workpiece.toString());
      Calc calc = new Calc(workpiece);
      calc.calcAll(workpiece);
      PrintResult printer = new PrintResult();
      printer.printAll(calc.getSchemaNormsCalc().getPrimerNorm(),
          calc.getSchemaNormsCalc().getPaintNorm(), calc.getMetalFraction(), calc.getSolvent647(),
          calc.getRag());
      printer.printAllResult(result);
    }
  }


}
