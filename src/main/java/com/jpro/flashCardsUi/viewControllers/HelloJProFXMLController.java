package com.jpro.flashCardsUi.viewControllers;

import com.jfoenix.controls.JFXButton;
import com.jpro.flashCardsUi.FxmlLoader;
import com.jpro.flashCardsUi.HelloJProFXML;
import com.jpro.webapi.JProApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;


public  class HelloJProFXMLController implements Initializable {


    protected JProApplication jProApplication;


    @FXML
    AnchorPane root;

    @FXML
  private Label label;

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
   public void handleButtonOne(){
       FxmlLoader loader= new FxmlLoader(this);
       Pane view = loader.getPage("LearnView");

       mainPane.setCenter(view);
   }
    @FXML
    public void handleButtonTwo(){
        FxmlLoader loader= new FxmlLoader(this);
        Pane view = loader.getPage("MenageView");
        mainPane.setCenter(view);
    }
    @FXML
    public void handleButtonThree(){
        FxmlLoader loader= new FxmlLoader(this);
        Pane view = loader.getPage("Three");
        mainPane.setCenter(view);
    }
    @FXML
    public void handleButtonFour(){
        FxmlLoader loader= new FxmlLoader(this);
        Pane view = loader.getLoginView();
        mainPane.setCenter(view);
    }
    @FXML
    public void handleButtonFive(){
        FxmlLoader loader= new FxmlLoader(this);
        Pane view = loader.getPage("RegisterView");
        mainPane.setCenter(view);
    }

    @FXML
    public void handleButtonSix(){
        HelloJProFXML.LOGGED_USER=null;
        alert("Logged Out","You successfully logged");
        setPermission();
        handleButtonFour();
    }

    @FXML
    public void handleAdminButton(){
        FxmlLoader loader= new FxmlLoader(this);
        Pane view = loader.getPage("AdminView");
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

    public void setPermission(){
        if(HelloJProFXML.LOGGED_USER==null){
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
        if(HelloJProFXML.LOGGED_USER!=null && HelloJProFXML.LOGGED_USER.getName().equals("admin")) {
            adminButton.setVisible(true);
            adminButton.setDisable(false);
        }
    }

    public void alert(String title, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
