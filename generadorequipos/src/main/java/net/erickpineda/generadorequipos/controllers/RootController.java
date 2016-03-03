package net.erickpineda.generadorequipos.controllers;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import net.erickpineda.generadorequipos.App;
import net.erickpineda.generadorequipos.utils.CrearXML;
import net.erickpineda.generadorequipos.utils.LeerXML;
import net.erickpineda.generadorequipos.utils.Msj;

public class RootController {
  @FXML
  private Button leerFichero;
  @FXML
  private Button guardar;
  @FXML
  private BorderPane contenido;
  @FXML
  private ContenidoController contenidoController;
  @FXML
  private HBox hboxContainer;
  private Stage myStage;

  @FXML
  public void initialize() {
    contenido.getStyleClass().add("contenido");
    hboxContainer.getStyleClass().add("hboxContainer");
  }

  @FXML
  public void btnLeerFichero(MouseEvent event) {
    abrirFichero();
  }

  @FXML
  public void btnGuardar(MouseEvent event) {
    guardarFichero();
  }

  /**
   * Crea un FiliChoser.
   * 
   * @param titulo titulo que llevará el FileChooser.
   * @return retorna un FileChooser que se usará para guardar y abrir los ficheros.
   */
  private FileChooser getFileChooser(final String titulo) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle(titulo);

    ExtensionFilter soloTXT = new ExtensionFilter("Solo ficheros XML (*.xml)", "*.xml");
    fileChooser.getExtensionFilters().add(soloTXT);
    fileChooser.setInitialDirectory(new File("."));
    setMyStage(App.PRIMARY_STAGE);
    return fileChooser;
  }

  /**
   * Método para abrir un FileChooser que permite abrir un fichero.
   */
  private void abrirFichero() {
    File xml = getFileChooser("Abrir un fichero XML").showOpenDialog(myStage);
    if (xml != null) {
      procesarXML(xml);
    }
  }

  /**
   * Método para abrir un FileChooser que permite guardar un fichero.
   */
  private void guardarFichero() {
    if (contenidoController.hayDatos()) {
      File xml = getFileChooser("Guardar un fichero XML").showSaveDialog(myStage);
      if (xml != null) {
        CrearXML crear = new CrearXML(xml.getAbsolutePath(), contenidoController.nuevoEquipo());
        crear.crearInstancia();
        crear.crearFicheroXML();
      }
    } else {
      Msj.error("Error", "No hay valores a guardar", "Debes leer un fichero con datos correctos");
    }
  }

  /**
   * Método que recibe un fichero por parámetro, explicitamente un XML y que se procesará para
   * recoger la información y almacenarla en una lista.
   * 
   * @param f fichero XML al que se le extraeran los datos para ser almacenados en una lista.
   */
  private void procesarXML(final File f) {
    LeerXML pro = new LeerXML(f).crearInstancia();
    pro.procesarXML();
    if (pro.getProfesores() != null && !pro.getProfesores().isEmpty()) {
      if (pro.getProfesores().size() > 2) {
        if (contenidoController.hayMujeresYHombres(pro.getProfesores())) {
          contenidoController.setProfesores(pro.getProfesores());
          contenidoController.agregarPersonas();
          myStage.setTitle("¡Generador Equipos! - " + f.toURI().toString());
        } else {
          Msj.warning("Alerta", "Igualdad de género", "Deben existir hombres y mujeres");
        }
      } else {
        Msj.warning("Warning", "Falta personal", "Personal insuficiente para procesar los datos");
      }
    } else {
      Msj.warning("Warning", "Fichero procesado", "El fichero no tiene información válida");
    }
  }

  /**
   * 
   * @param myStage parametro del stage que se esta usando.
   */
  public void setMyStage(final Stage myStage) {
    this.myStage = myStage;
  }
}