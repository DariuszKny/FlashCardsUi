package com.jpro.flashCardsUi.viewControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jpro.flashCardsUi.FlashCardsUI;
import com.jpro.flashCardsUi.domain.FetchedUser;
import com.jpro.flashCardsUi.validator.LoginValidator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginMenuController implements Initializable {

    private MainViewController mainViewController;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField fieldName;

    @FXML
    private JFXPasswordField fieldPassword;

    @FXML
    private JFXPasswordField fieldConfirmPassword;

    @FXML
    private JFXButton logButton;

    @FXML
    private JFXButton adminButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void init(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    @FXML
    public void create() {
        String name = fieldName.getText();
        String password = fieldPassword.getText();
        String confirmPassword = fieldConfirmPassword.getText();

        LoginValidator userValidator = new LoginValidator(name, password, confirmPassword);
        FetchedUser fetchedUser = userValidator.validatedUser();

        if (fetchedUser==null) {
            alert("Username Invalid", "Wrong username, or password");
        } else {
            alert("User Logged", "You may use FlashCards!");
            FlashCardsUI.LOGGED_USER=fetchedUser;
            mainViewController.setAppColor(FlashCardsUI.LOGGED_USER.getUserAppColor());
            mainViewController.setPermission();
            mainViewController.handleButtonOne();
        }
    }

    @FXML
    public void alert(String title, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }



}
