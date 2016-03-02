package net.erickpineda.generadorequipos.utils;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Clase con métodos estáticos que permiten abrir ventanas de tipo Dialog, según su conveniencia
 * INFORMATION, WARNING, ERROR y CONFIRMATION.
 * 
 * @author Erick Pineda.
 *
 */
public class Msj {
  public static void information(String title, String header, String content) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.getIcons().add(new Image(Msj.class.getResource("../content/images/mantis.png").toExternalForm()));
    alert.showAndWait();
  }

  public static void warning(String title, String header, String content) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.getIcons().add(new Image(Msj.class.getResource("../content/images/mantis.png").toExternalForm()));
    alert.showAndWait();
  }

  public static void error(String title, String header, String content) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.getIcons().add(new Image(Msj.class.getResource("../content/images/mantis.png").toExternalForm()));
    alert.showAndWait();
  }

  public static Optional<ButtonType> confirmation(String title, String header, String content) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.getIcons().add(new Image(Msj.class.getResource("../content/images/mantis.png").toExternalForm()));
    return alert.showAndWait();
  }
}
