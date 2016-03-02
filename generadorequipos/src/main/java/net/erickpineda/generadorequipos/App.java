package net.erickpineda.generadorequipos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import net.erickpineda.generadorequipos.controllers.RootController;

/**
 * @author Erick Pineda.
 *
 */
public class App extends Application {
  @Override
  public void start(Stage primaryStage) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Root.fxml"));
      Parent root = loader.load();

      Scene scene = new Scene(root, Color.BLACK);
      scene.getStylesheets().add(getClass().getResource("content/css/app.css").toExternalForm());

      primaryStage.setScene(scene);
      primaryStage.setTitle("¡Generador Equipos!");

      primaryStage.getIcons()
          .add(new Image(getClass().getResource("content/images/mantis.png").toExternalForm()));

      RootController rc = loader.getController();
      rc.setMyStage(primaryStage);

      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}