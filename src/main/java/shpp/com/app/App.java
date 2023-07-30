package shpp.com.app;

import lombok.extern.slf4j.Slf4j;
import shpp.com.graf.Window;

@Slf4j
public class App {

  public static void main(String[] args) {
    // створюю вікно інтерфейса
    Window window = new Window();
    window.createWindow();
//
//    // ввід даних, створення заготівлі
//    Workpiece workpiece = new Workpiece(10, Material.CARBON, RAL.ral3000, true);
//    // Створення схеми фарбування. Парсинг таблиць із вхідними данними
//    SchemaData schemaData = new SchemaData(workpiece);
//    List<PrimerData> list = schemaData.getPrimersData();
//    for (int i = 0; i < list.size(); i++) {
//      log.info("Primer data : {}", list.get(i));
//    }
//    PaintData paintData = schemaData.getPaintData();
//    log.info("Paint data is: {}", paintData.toString());
//    // розрахунок норм для матеріалів із схеми фарбування
//    NormsCalc calc = new NormsCalc(schemaData);
//    calc.calcPrimersNorm(calc.getPrimerNorm(), workpiece);
//    calc.calcPaintNorm(calc.getPaintNorm(), workpiece);
//    for(int i = 0; i < calc.getPrimerNorm().size(); i++){
//      log.info("Primer norm is : {}", calc.getPrimerNorm().get(i));
//    }
//    log.info("Paint norm is : {}", calc.getPaintNorm().toString());
//    log.info("MetalFraction norms are : {}", schemaData.getMetalFraction());

  }
}
