package com.jpro.flashCardsUi;

import com.jpro.flashCardsUi.domain.FetchedUser;
import com.jpro.flashCardsUi.viewControllers.MainViewController;
import com.jpro.webapi.JProApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class FlashCardsUI extends JProApplication {
    public static FetchedUser LOGGED_USER = null;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/MainView.fxml"));
        Scene scene = null;
        try {
            Parent root = loader.load();
            MainViewController controller = loader.getController();
            controller.init(this);

            //create JavaFX scene
            scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Hello");
        stage.setScene(scene);
        stage.show();
    }
}
