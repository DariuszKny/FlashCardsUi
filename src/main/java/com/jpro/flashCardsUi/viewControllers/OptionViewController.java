package com.jpro.flashCardsUi.viewControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import com.jpro.flashCardsUi.client.UserClient;
import com.jpro.flashCardsUi.domain.Language;
import com.jpro.flashCardsUi.domain.UserAppColor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static com.jpro.flashCardsUi.FlashCardsUI.LOGGED_USER;

public class OptionViewController implements Initializable {

    private UserClient userClient;
    private MainViewController mainViewController;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXToggleButton toggleButton;

    @FXML
    private JFXComboBox<Language> comboBox;

    public void init(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    List<Language> list = new ArrayList<>(Arrays.asList(Language.ENGLISH,Language.FRENCH,Language.GERMAN,Language.SPANISH));
    ObservableList<Language> observableList = FXCollections.observableList(list);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userClient = new UserClient();

        comboBox.setItems(observableList);
        comboBox.setValue(LOGGED_USER.getLanguage());

        if(LOGGED_USER.getUserAppColor()== UserAppColor.DARK){
            toggleButton.setSelected(true);
            toggleButton.setText("ON");
        } else {
            toggleButton.setSelected(false);
            toggleButton.setText("OFF");
        }

        toggleButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if(toggleButton.isSelected()==true){
                    toggleButton.setText("ON");
                    LOGGED_USER.setUserAppColor(UserAppColor.DARK);
                    mainViewController.setAppColor(UserAppColor.DARK);
                } else {
                    toggleButton.setText("OFF");
                    LOGGED_USER.setUserAppColor(UserAppColor.LIGHT);
                    mainViewController.setAppColor(UserAppColor.LIGHT);
                }
            }
        });
    }

    @FXML
    private void comboChanged(){
        LOGGED_USER.setLanguage(comboBox.getValue());
    }

    @FXML
    private void updateUser(){
        userClient.updateUser(LOGGED_USER);
    }
}
