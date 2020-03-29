package com.jpro.flashCardsUi;

import com.jpro.flashCardsUi.viewControllers.HelloJProFXMLController;
import com.jpro.flashCardsUi.viewControllers.LoginMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.net.URL;

public class FxmlLoader {
    private HelloJProFXMLController helloJProFXMLController;
    private Pane view;

    public FxmlLoader(HelloJProFXMLController helloJProFXMLController) {
        this.helloJProFXMLController = helloJProFXMLController;
        this.view = null;
    }


    public Pane getPage(String fileName) {
        try {
            URL fileUrl = HelloJProFXML.class.getResource("fxml/" + fileName + ".fxml");
            if (fileUrl == null) {
                throw new FileNotFoundException("FXML not found");
            }
            view = new FXMLLoader().load(fileUrl);
        } catch (Exception e){
            System.out.println("no page found");
        }
        return view;
    }

    public Pane getLoginView() {
        try {
            URL fileUrl = HelloJProFXML.class.getResource("fxml/LoginView.fxml");
            if (fileUrl == null) {
                throw new FileNotFoundException("FXML not found");
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloJProFXML.class.getResource("fxml/LoginView.fxml"));
            view = loader.load();
            LoginMenuController controller = loader.getController();
            controller.init(helloJProFXMLController);
        } catch (Exception e){
            System.out.println("no page found");
        }
        return view;
    }


}
