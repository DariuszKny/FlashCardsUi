package com.jpro.flashCardsUi;

import com.jpro.flashCardsUi.viewControllers.MainViewController;
import com.jpro.flashCardsUi.viewControllers.LoginMenuController;
import com.jpro.flashCardsUi.viewControllers.OptionViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.net.URL;

public class FxmlLoader {
    private MainViewController mainViewController;
    private Pane view;

    public FxmlLoader(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
        this.view = null;
    }

    public Pane getView(String fileName) {
        try {
            URL fileUrl = FlashCardsUI.class.getResource("fxml/" + fileName + ".fxml");
            if (fileUrl == null) {
                throw new FileNotFoundException("FXML not found");
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FlashCardsUI.class.getResource("fxml/" + fileName + ".fxml"));
            view = loader.load();
            controllerInit(fileName, loader);
        } catch (Exception e) {
        }
        return view;
    }

    private void controllerInit(String fileName, FXMLLoader loader) {
        if (fileName.equals("LoginView")) {
            LoginMenuController controller = loader.getController();
            controller.init(mainViewController);
        } else if (fileName.equals("OptionView")) {
            OptionViewController controller = loader.getController();
            controller.init(mainViewController);
        }
    }


}
