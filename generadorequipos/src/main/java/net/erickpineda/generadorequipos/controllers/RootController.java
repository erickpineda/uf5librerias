package net.erickpineda.generadorequipos.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
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
    return fileChooser;
  }

  /**
   * Método para abrir un FileChooser que permite abrir un fichero.
   */
  private void abrirFichero() {
    File xml = getFileChooser("Abrir un fichero XML").showOpenDialog(myStage);
    if (xml != null) {
      procesarXML(xml);
      myStage.setTitle("¡Generador Equipos! - " + xml.toURI().toString());
    }
  }

  /**
   * Método para abrir un FileChooser que permite guardar un fichero.
   */
  private void guardarFichero() {
    if (contenidoController.nuevoEquipo() != null) {
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
    try {
      InputStream entrada = f.toURI().toURL().openStream();
      XMLStreamReader parser = XMLInputFactory.newInstance().createXMLStreamReader(entrada);
      LeerXML pro = new LeerXML(parser);
      pro.procesarXML();
      contenidoController.setProfesores(pro.getProfesores());
      contenidoController.agregarPersonas();
    } catch (XMLStreamException | FactoryConfigurationError e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
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