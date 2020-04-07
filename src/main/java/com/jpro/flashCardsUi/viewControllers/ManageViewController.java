package com.jpro.flashCardsUi.viewControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jpro.flashCardsUi.client.AmazonCardClient;
import com.jpro.flashCardsUi.client.FlashCardClient;
import com.jpro.flashCardsUi.domain.FetchedAmazonCard;
import com.jpro.flashCardsUi.domain.FetchedFlashCard;
import com.jpro.flashCardsUi.domain.FlashCardProgress;
import com.jpro.flashCardsUi.domain.TableViewFlashCard;
import com.jpro.flashCardsUi.mapper.FlashCardMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import static com.jpro.flashCardsUi.FlashCardsUI.LOGGED_USER;

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

        data = updateList();

        tableView.getColumns().addAll(id, name, language, flashCardProgress);
        addRevertButtonToTable();
        addDeleteButtonToTable();

        id.setCellValueFactory(new PropertyValueFactory<TableViewFlashCard, Long>("id"));
        name.setCellValueFactory(new PropertyValueFactory<TableViewFlashCard, String>("name"));
        language.setCellValueFactory(new PropertyValueFactory<TableViewFlashCard, String>("language"));
        flashCardProgress.setCellValueFactory(new PropertyValueFactory<TableViewFlashCard, String>("flashCardProgress"));

        tableView.setItems(data);

    }

    public void translate() {
        amazonCardText = amazonCardClient.getTranslate(toTranslateText.getText(), LOGGED_USER.getLanguage());
        translatedText.setText(amazonCardText);
    }

    public void createFlashCard() {
        FetchedAmazonCard amazonCardDto = new FetchedAmazonCard(null, amazonCardOutputStream, amazonCardText);
        flashCardClient.addFlashCard(toTranslateText.getText(), LOGGED_USER, amazonCardDto);
        data = updateList();
        tableView.setItems(data);
    }

    public void play() throws Exception {
        amazonCardOutputStream = amazonCardClient.getAudio(translatedText.getText(), LOGGED_USER.getLanguage());
        InputStream inputStream = new ByteArrayInputStream(amazonCardOutputStream);
        AdvancedPlayer player = new AdvancedPlayer(inputStream, javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());
        player.play();
    }

    private ObservableList<TableViewFlashCard> updateList() {
        return FXCollections.observableArrayList(flashCardMapper.mapToTableViewFlashCardList(flashCardClient.getFlashCardsByUserIdAndLanguage(LOGGED_USER.getId(), LOGGED_USER.getLanguage())));
    }

    private void addRevertButtonToTable() {
        TableColumn<TableViewFlashCard, Void> colBtn = new TableColumn("REVERT BUTTON");

        Callback<TableColumn<TableViewFlashCard, Void>, TableCell<TableViewFlashCard, Void>> cellFactory = new Callback<TableColumn<TableViewFlashCard, Void>, TableCell<TableViewFlashCard, Void>>() {
            @Override
            public TableCell<TableViewFlashCard, Void> call(final TableColumn<TableViewFlashCard, Void> param) {
                final TableCell<TableViewFlashCard, Void> cell = new TableCell<TableViewFlashCard, Void>() {

                    private final Button btn = new JFXButton("REVERT");

                    {
                        btn.setStyle("-fx-background-color: #4a6a3a; -fx-opacity: 0.70");
                        btn.setOnAction((ActionEvent event) -> {
                            TableViewFlashCard flashCard = getTableView().getItems().get(getIndex());
                            FetchedFlashCard fetchedFlashCard = flashCardClient.getFlashCard(flashCard.getId());
                            flashCardClient.updateFlashCard(fetchedFlashCard, FlashCardProgress.NEW);
                            data = updateList();
                            tableView.setItems(data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        tableView.getColumns().add(colBtn);
    }

    private void addDeleteButtonToTable() {
        TableColumn<TableViewFlashCard, Void> colBtn = new TableColumn("DELETE BUTTON");

        Callback<TableColumn<TableViewFlashCard, Void>, TableCell<TableViewFlashCard, Void>> cellFactory = new Callback<TableColumn<TableViewFlashCard, Void>, TableCell<TableViewFlashCard, Void>>() {
            @Override
            public TableCell<TableViewFlashCard, Void> call(final TableColumn<TableViewFlashCard, Void> param) {
                final TableCell<TableViewFlashCard, Void> cell = new TableCell<TableViewFlashCard, Void>() {

                    private final Button btn = new JFXButton("DELETE");

                    {
                        btn.setStyle("-fx-background-color: #ba4a4a; -fx-opacity: 0.70");
                        btn.setOnAction((ActionEvent event) -> {
                            TableViewFlashCard flashCard = getTableView().getItems().get(getIndex());
                            flashCardClient.deleteFlashCard(flashCard.getId());
                            data = updateList();
                            tableView.setItems(data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        tableView.getColumns().add(colBtn);
    }

}
