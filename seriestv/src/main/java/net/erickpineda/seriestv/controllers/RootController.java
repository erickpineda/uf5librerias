package net.erickpineda.seriestv.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import net.erickpineda.seriestv.App;
import net.erickpineda.seriestv.models.Capitulo;
import net.erickpineda.seriestv.models.Serie;
import net.erickpineda.seriestv.models.Temporada;
import net.erickpineda.seriestv.utils.Msj;

public class RootController {
  @FXML
  private VBox vbox;
  @FXML
  private TextField buscador;
  @FXML
  private ImageView imagen;
  @FXML
  private ComboBox<String> combobox;
  @FXML
  private ListView<String> listView;
  @FXML
  private Label esp;
  @FXML
  private Label sub;
  @FXML
  private Label ing;
  private boolean esPrimeraBusqueda = true;
  private static final String SERIES_SUBMIT = "//div[@class='post-header']/div/div/div/a";
  private static final String WEB = "http://seriesblanco.com/";
  private static final String NOMBRES_TEMPORADAS = "//div[contains(@id,'post-body-')]/h2";
  private static final String DATOS_CAPITULOS = "//div[contains(@id,'post-body-')]/table";
  private static final String TR = "tbody > tr";
  private static final String NOMBRE_CAPITULO = "td:nth-child(1) > a";
  private static final String IDIOMAS = "td:nth-child(2) > img";
  private Serie serieNueva;
  
  @FXML
  public void btnBuscar(MouseEvent event) {
    ChromeDriverManager.getInstance().setup();
    // PhantomJsDriverManager.getInstance().setup();
    WebDriver navegador = null;
    esconderVentana();
    
    try {
      accionesBuscar(navegador);
    } catch (Exception e) {
      reset(navegador);
      Msj.err("Error", "Error interno", "Intenta buscar la serie de nuevo");
    } finally {
      mostrarVentana();
    }
  }

  @FXML
  public void cbClick(MouseEvent event) {
    if (combobox.getItems() != null && !combobox.getItems().isEmpty()) {
      if (!listView.getItems().isEmpty()) {
        listView.getItems().clear();
      }
      listView.getItems().setAll(rellenarOLConCapitulos(serieNueva));
      listView.refresh();
    }
  }

  /**
   * Acciones que desencadenará el botón de buscar.
   * 
   * @param driver navegador.
   */
  private void accionesBuscar(WebDriver driver) {
    if (esPrimeraBusqueda) {
      driver = new ChromeDriver();
      esPrimeraBusqueda = false;
    }
    irA(driver, WEB);
    buscar(driver, buscador.getText());
    Msj.inf(WEB, "Se han procesado los datos correctamente", serieNueva.getNombre());
  }

  /**
   * Rellena un {@code ObservableList<String>} a través de una serie que pasa por parámetro. Itera
   * la lista de temporadas de dicha serie, para agregar el nombre del capitulo al
   * {@code ObservableList<String>} resultante.
   * 
   * @param serie serie que tendra los datos necesarios para agregarlos a la nueva lista.
   * @return retorna un {@code ObservableList<String>} con la información de los nombres de cada
   *         capítulo según la temporada seleccionada en el combobox.
   */
  private ObservableList<String> rellenarOLConCapitulos(final Serie serie) {
    ObservableList<String> olCapitulos = FXCollections.observableArrayList();
    serie.getTemporadas().forEach(t -> {
      String item = combobox.getSelectionModel().getSelectedItem();
      if (item != null && !item.isEmpty() && item.equals(t.getNombre())) {
        esp.setText(String.format("%d", t.getCantidadEsp()));
        ing.setText(String.format("%d", t.getCantidadIng()));
        sub.setText(String.format("%d", t.getCantidadSub()));
        t.getCapitulos().forEach(c -> olCapitulos.add(c.getNombre()));
      }
    });
    return olCapitulos;
  }

  /**
   * Método que Itera una lista de temporadas a través del parámetro {@linkplain serie} quien tiene
   * la información almacenada.
   * 
   * @param serie parámetro que tiene los datos a iterar.
   * @return un {@code ObservableList<String>} con los nombres de las temporadas de la serie.
   */
  private ObservableList<String> rellenarOLConTemporadas(final Serie serie) {
    ObservableList<String> olTemporadas = FXCollections.observableArrayList();
    serie.getTemporadas().forEach(t -> olTemporadas.add(t.getNombre()));
    return olTemporadas;
  }

  /**
   * Método para navegar a través de una URL.
   * 
   * @param driver tipo de driver que va a navegar a través de la url.
   * @param url url a navegar.
   */
  private void irA(final WebDriver driver, final String url) {
    driver.navigate().to(url);
  }

  /**
   * 
   * @param driver navegador.
   * @param texto texto a escribir en el buscador.
   */
  private void buscar(final WebDriver driver, final String texto) {
    WebElement buscador = driver.findElement(By.id("buscar-blanco"));
    buscador.sendKeys(texto);
    buscador.submit();
    esperarSegundos(driver, 5);

    List<WebElement> series = driver.findElements(By.xpath(SERIES_SUBMIT));
    if (series.size() == 1 && series.get(0) != null) {
      series.get(0).click();

      esperarSegundos(driver, 2);
      setSerie(crearSerie(driver));
      reset(driver);

      if (!combobox.getItems().isEmpty()) {
        combobox.getItems().clear();
      }
      combobox.setItems(rellenarOLConTemporadas(serieNueva));
      if (serieNueva != null) {
        imagen.setImage(serieNueva.getImagen());
      }
    } else {
      Msj.warn("Warning", "Coincidencias",
          "Se han encontrado varias coincidencias prueba con otro nombre de serie");
    }
  }

  /**
   * Rellena una lista con los idiomas que tiene cada serie según la búsqueda efectuada.
   * 
   * @param lista lista a rellenar.
   */
  private void rellenarIdiomas(final List<Temporada> lista) {
    lista.forEach(t -> {
      t.getCapitulos().forEach(c -> {
        t.sumarEsp(c.getEsp());
        t.sumarIng(c.getIng());
        t.sumarSub(c.getSub());
      });
    });
  }

  /**
   * 
   * @param driver navegador.
   * @return retorna una nueva serie a través de una navegación efectuada.
   */
  private Serie crearSerie(final WebDriver driver) {
    Serie serie = new Serie(driver.getTitle());
    List<Temporada> temporadas = crearTemporadas(driver);
    rellenarIdiomas(temporadas);
    
    Image im = obtenerImagen(driver);
    if (im != null) {
      serie.setImagen(im);
    }

    serie.setTemporadas(temporadas);
    return serie;
  }

  /**
   * 
   * @param driver navegador.
   * @return retorna una imágen a través de una URL resultante de una búsqueda.
   */
  private Image obtenerImagen(final WebDriver driver) {
    String src = driver.findElement(By.xpath("//img[@id='port_serie']")).getAttribute("src");
    Image image = new Image(src, 100, 100, false, false);
    return image;
  }

  /**
   * 
   * @param driver navegador.
   * @return retorna una lista de temporadas según la búsqueda.
   */
  private List<Temporada> crearTemporadas(final WebDriver driver) {
    List<WebElement> titulos = driver.findElements(By.xpath(NOMBRES_TEMPORADAS));
    esperarSegundos(driver, 2);
    List<Temporada> temporadas = new ArrayList<Temporada>();

    for (int i = 0; i < titulos.size(); i++) {
      titulos.get(i).click();
      temporadas.add(new Temporada(titulos.get(i).getText(), i + 1, crearCapitulos(driver)));
    }
    return temporadas;
  }

  /**
   * 
   * @param driver navegador.
   * @return retorna una lista de capitulos según la búsqueda efectuada.
   */
  private List<Capitulo> crearCapitulos(final WebDriver driver) {
    List<WebElement> tablas = driver.findElements(By.xpath(DATOS_CAPITULOS));
    List<Capitulo> capitulos = new ArrayList<Capitulo>();
    
    for (WebElement t : tablas) {
      rellenarCapitulos(capitulos, t);
    }
    return capitulos;
  }

  /**
   * 
   * @param capitulos lista de capítulos a rellenar.
   * @param t WebElement que será la tabla con los datos a iterar.
   */
  private void rellenarCapitulos(final List<Capitulo> capitulos, final WebElement t) {
    List<WebElement> tr = t.findElements(By.cssSelector(TR));

    for (int i = 0; i < tr.size(); i++) {
      WebElement elCapitulo = tr.get(i).findElement(By.cssSelector(NOMBRE_CAPITULO));
      if (elCapitulo != null && !elCapitulo.getText().isEmpty()) {
        List<WebElement> idiomas = tr.get(i).findElements(By.cssSelector(IDIOMAS));
        Capitulo cap = new Capitulo(elCapitulo.getText(), i + 1);
        cap.setCantidadIdiomas(idiomas.size());
        idiomas.forEach(e -> {
          if (e.getAttribute("src").contains("es")) {cap.sumarEsp();}
          if (e.getAttribute("src").contains("vo")) {cap.sumarIng();}
          if (e.getAttribute("src").contains("vos")) {cap.sumarSub();}
        });
        capitulos.add(cap);
      }
    }
  }

  /**
   * 
   * @param serie serie a cambiar.
   */
  private void setSerie(final Serie serie) {
    this.serieNueva = serie;
  }
  
  /**
   * Método para decirle al navegador que espere unos segúndos antes de hacer algo, de forma
   * implicita.
   * 
   * @param driver navegador.
   * @param segundos segundos que debe esperarse.
   */
  private void esperarSegundos(final WebDriver driver, final long segundos) {
    driver.manage().timeouts().implicitlyWait(segundos, TimeUnit.SECONDS);
  }

  /**
   * Método para esconder la ventana.
   */
  private void esconderVentana() {
    App.PRIMARY_STAGE.hide();
  }

  /**
   * Método para mostrar y traer al frente la ventana.
   */
  private void mostrarVentana() {
    App.PRIMARY_STAGE.show();
  }

  /**
   * 
   * @param driver navegador.
   */
  private void reset(final WebDriver driver) {
    cerrarNavegador(driver);
    esPrimeraBusqueda = true;
  }
  
  /**
   * Cierra la conexión del navegador.
   * 
   * @param driver navegador.
   */
  private void cerrarNavegador(final WebDriver driver) {
    if (driver != null) driver.quit();
  }
}
