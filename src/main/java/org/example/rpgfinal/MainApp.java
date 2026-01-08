package org.example.rpgfinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                MainApp.class.getResource("/org/example/rpgfinal/view/main-view.fxml")
        );

        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(
                getClass().getResource("/org/example/rpgfinal/style/style.css").toExternalForm()
        );
        stage.setTitle("RPG Final");
        stage.setScene(scene);
        stage.show();
    }
}
