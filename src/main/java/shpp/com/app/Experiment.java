package shpp.com.app;

import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Experiment {

  private final JFrame frame;
  private final JComboBox<String> countryComboBox;
  private final JComboBox<String> cityComboBox;
  private final Map<String, String[]> cityMap;

  public Experiment() {
    frame = new JFrame("Залежні комбіновані списки");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new FlowLayout());

    // Створюємо комбінований список країн
    String[] countries = {"Україна", "США", "Німеччина"};
    countryComboBox = new JComboBox<>(countries);
    frame.add(new JLabel("Країна:"));
    frame.add(countryComboBox);

    // Створюємо мапу, що містить міста для кожної країни
    cityMap = new HashMap<>();
    cityMap.put("Україна", new String[]{"Київ", "Львів", "Харків"});
    cityMap.put("США", new String[]{"Нью-Йорк", "Лос-Анджелес", "Чикаго"});
    cityMap.put("Німеччина", new String[]{"Берлін", "Мюнхен", "Гамбург"});

    // Створюємо комбінований список міст
    cityComboBox = new JComboBox<>();
    frame.add(new JLabel("Місто:"));
    frame.add(cityComboBox);

    // Додаємо слухача подій до першого комбінованого списку
    countryComboBox.addActionListener(e -> {
      // Отримуємо обрану країну
      String selectedCountry = (String) countryComboBox.getSelectedItem();
      // Отримуємо список міст для обраної країни
      String[] cities = cityMap.get(selectedCountry);
      // Оновлюємо комбінований список міст
      cityComboBox.setModel(new DefaultComboBoxModel<>(cities));
    });

    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new Experiment();
      }
    });
  }
}
