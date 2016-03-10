package net.erickpineda.seriestv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Erick Pineda
 */
public class App extends Application {
  public static Stage PRIMARY_STAGE = null;

  @Override
  public void start(Stage stage) throws Exception {
    try {
      BorderPane loader = FXMLLoader.load(getClass().getResource("/fxml/Root.fxml"));
      Scene scene = new Scene(loader, Color.BLACK);
      scene.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
      stage.setScene(scene);
      stage.setTitle("¡Buscador de series!");
      stage.getIcons().add(new Image(getClass().getResource("/img/mantis.png").toExternalForm()));
      stage.show();
      PRIMARY_STAGE = stage;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Application.launch(App.class, (java.lang.String[]) null);
  }
}
