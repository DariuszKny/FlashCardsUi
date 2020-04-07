package com.jpro.flashCardsUi.viewControllers;

import com.jfoenix.controls.JFXButton;
import com.jpro.flashCardsUi.FlashCardsUI;
import com.jpro.flashCardsUi.FxmlLoader;
import com.jpro.flashCardsUi.domain.UserAppColor;
import com.jpro.webapi.JProApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class MainViewController implements Initializable {


    protected JProApplication jProApplication;


    @FXML
    AnchorPane root;

    @FXML
    private AnchorPane upBar;

    @FXML
    private VBox sideBar;

    @FXML
    private BorderPane mainPane;

    @FXML
    private JFXButton buttonOne;

    @FXML
    private JFXButton buttonTwo;

    @FXML
    private JFXButton buttonThree;

    @FXML
    private JFXButton buttonFour;

    @FXML
    private JFXButton buttonFive;

    @FXML
    private JFXButton buttonSix;

    @FXML
    private JFXButton adminButton;

    @FXML
    public void handleButtonOne() {
        FxmlLoader loader = new FxmlLoader(this);
        Pane view = loader.getView("LearnView");

        mainPane.setCenter(view);
    }

    @FXML
    public void handleButtonTwo() {
        FxmlLoader loader = new FxmlLoader(this);
        Pane view = loader.getView("MenageView");
        mainPane.setCenter(view);
    }

    @FXML
    public void handleButtonThree() {
        FxmlLoader loader = new FxmlLoader(this);
        Pane view = loader.getView("OptionView");
        mainPane.setCenter(view);
    }

    @FXML
    public void handleButtonFour() {
        FxmlLoader loader = new FxmlLoader(this);
        Pane view = loader.getView("LoginView");
        mainPane.setCenter(view);
    }

    @FXML
    public void handleButtonFive() {
        FxmlLoader loader = new FxmlLoader(this);
        Pane view = loader.getView("RegisterView");
        mainPane.setCenter(view);
    }

    @FXML
    public void handleButtonSix() {
        FlashCardsUI.LOGGED_USER = null;
        alert("Logged Out", "You successfully logged");
        setPermission();
        handleButtonFour();
    }

    @FXML
    public void handleAdminButton() {
        FxmlLoader loader = new FxmlLoader(this);
        Pane view = loader.getView("AdminView");
        mainPane.setCenter(view);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminButton.setVisible(false);
        adminButton.setDisable(true);
        setPermission();
    }

    public void init(JProApplication jProApplication) {
        this.jProApplication = jProApplication;
    }

    public void setPermission() {
        if (FlashCardsUI.LOGGED_USER == null) {
            buttonOne.setDisable(true);
            buttonTwo.setDisable(true);
            buttonThree.setDisable(true);
            buttonFour.setDisable(false);
            buttonFive.setDisable(false);
            buttonSix.setDisable(true);
            adminButton.setVisible(false);
            adminButton.setDisable(true);
        } else {
            buttonOne.setDisable(false);
            buttonTwo.setDisable(false);
            buttonThree.setDisable(false);
            buttonFour.setDisable(true);
            buttonFive.setDisable(true);
            buttonSix.setDisable(false);
        }
        if (FlashCardsUI.LOGGED_USER != null && FlashCardsUI.LOGGED_USER.getName().equals("admin")) {
            adminButton.setVisible(true);
            adminButton.setDisable(false);
        }
    }

    public void alert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public void setAppColor(UserAppColor userAppColor) {
        if (userAppColor == UserAppColor.DARK) {
            root.setStyle("-fx-background-color: #292826");
            upBar.setStyle("-fx-background-color: #292826");
            sideBar.setStyle("-fx-background-color: #292826");
        } else {
            root.setStyle("-fx-background-color: #eec1ad");

            upBar.setStyle("-fx-background-color: #dbac98");
            sideBar.setStyle("-fx-background-color: #c98276");
        }
    }
}
