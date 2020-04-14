package com.jpro.flashCardsUi.viewControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jpro.flashCardsUi.client.UserClient;
import com.jpro.flashCardsUi.validator.NewUserValidator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class RegisterMenuController implements Initializable {

    private UserClient userClient;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton regButton;

    @FXML
    private JFXTextField fieldName;

    @FXML
    private JFXTextField fieldMail;

    @FXML
    private JFXPasswordField fieldPassword;

    @FXML
    private JFXPasswordField fieldConfirmPassword;

    @FXML
    private VBox errorBox;

    @FXML
    private JFXButton okButton;

    @FXML
    private JFXTextArea errorArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorBox.setVisible(false);
        userClient = new UserClient();
    }

    @FXML
    public void create() {
        String name = fieldName.getText();
        String mail = fieldMail.getText();
        String password = fieldPassword.getText();
        String confirmPassword = fieldConfirmPassword.getText();

        NewUserValidator userValidator = new NewUserValidator(name, mail, password, confirmPassword);

        if (!userValidator.validateName()) {
            alert("Username Invalid", "Username already taken, or invalid\nPlease try another one");
        } else if (!userValidator.validateEmail()) {
            alert("Wrong Email", "Please provide correct mail address");
        } else if (!userValidator.validatePassword()) {
            alert("Password incorrect", "Provided password does not match");
        } else {
            alert("User Registered", "You may now login!");
            userClient.addUser(name, mail, password);
        }

    }

    @FXML
    private void alert(String title, String content) {
        errorArea.setText(content);
        errorBox.setVisible(true);
        regButton.setDisable(true);
    }

    @FXML
    private void enableConsole() {
        errorBox.setVisible(false);
        regButton.setDisable(false);
    }


}
