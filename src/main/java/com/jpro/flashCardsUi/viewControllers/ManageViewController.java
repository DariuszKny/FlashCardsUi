package com.jpro.flashCardsUi.viewControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jpro.flashCardsUi.client.AmazonCardClient;
import com.jpro.flashCardsUi.client.FlashCardClient;
import com.jpro.flashCardsUi.domain.FetchedAmazonCard;
import com.jpro.flashCardsUi.domain.Language;
import com.jpro.flashCardsUi.domain.TableViewFlashCard;
import com.jpro.flashCardsUi.mapper.FlashCardMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import static com.jpro.flashCardsUi.HelloJProFXML.LOGGED_USER;

public class ManageViewController implements Initializable {

    private AmazonCardClient amazonCardClient;
    private FlashCardClient flashCardClient;
    private FlashCardMapper flashCardMapper;
    private String amazonCardText;
    private byte[] amazonCardOutputStream;
    private ObservableList<TableViewFlashCard> data;

    @FXML
    private JFXTextField toTranslateText;

    @FXML
    private JFXTextField translatedText;

    @FXML
    private JFXButton translateButton;

    @FXML
    private JFXButton playButton;

    @FXML
    private JFXButton createButton;

    @FXML
    private TableView tableView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        amazonCardClient = new AmazonCardClient();
        flashCardClient = new FlashCardClient();
        flashCardMapper = new FlashCardMapper();

        TableColumn id = new TableColumn("ID");
        TableColumn name = new TableColumn("NAME");
        TableColumn language = new TableColumn("LANGUAGE");
        TableColumn flashCardProgress = new TableColumn("PROGRESS");


        tableView.getColumns().addAll(id,name,language,flashCardProgress);
        data = updateList();
        id.setCellValueFactory(new PropertyValueFactory<TableViewFlashCard, Long>("id"));
        name.setCellValueFactory(new PropertyValueFactory<TableViewFlashCard, String>("name"));
        language.setCellValueFactory(new PropertyValueFactory<TableViewFlashCard, String>("language"));
        flashCardProgress.setCellValueFactory(new PropertyValueFactory<TableViewFlashCard, String>("flashCardProgress"));
        tableView.setItems(data);
    }

    public void translate(){
        amazonCardText = amazonCardClient.getTranslate(toTranslateText.getText(), Language.ENGLISH);
        translatedText.setText(amazonCardText);
    }

    public void createFlashCard(){
        FetchedAmazonCard amazonCardDto = new FetchedAmazonCard(null,amazonCardOutputStream,amazonCardText);
        flashCardClient.addFlashCard(toTranslateText.getText(),LOGGED_USER,amazonCardDto);
        data = updateList();
        tableView.setItems(data);
    }

    public void play() throws Exception{
        amazonCardOutputStream = amazonCardClient.getAudio(translatedText.getText());
        InputStream inputStream = new ByteArrayInputStream(amazonCardOutputStream);
        AdvancedPlayer player = new AdvancedPlayer(inputStream, javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());
        player.play();
    }

    private ObservableList<TableViewFlashCard> updateList(){
        return FXCollections.observableArrayList(flashCardMapper.mapToTableViewFlashCardList(flashCardClient.getFlashCardsByUserID(LOGGED_USER.getId())));
    }

}
