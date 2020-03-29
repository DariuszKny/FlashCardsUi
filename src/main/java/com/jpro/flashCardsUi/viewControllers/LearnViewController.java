package com.jpro.flashCardsUi.viewControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jpro.flashCardsUi.client.FlashCardClient;
import com.jpro.flashCardsUi.domain.FetchedFlashCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import static com.jpro.flashCardsUi.HelloJProFXML.LOGGED_USER;

public class LearnViewController implements Initializable {

    private FlashCardClient flashCardClient;
    private FetchedFlashCard nextFlashcard = null;

    @FXML
    private JFXButton nextButton;

    @FXML
    private JFXTextField inputTextField;

    @FXML
    private JFXButton checkButton;

    @FXML
    private JFXTextField outputTextField;

    @FXML
    private JFXButton newButton;

    @FXML
    private JFXButton badButton;

    @FXML
    private JFXButton goodButton;

    @FXML
    private JFXButton doneButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        flashCardClient = new FlashCardClient();
        hideAll();
    }

    public void hideAll(){
        inputTextField.setVisible(false);
        checkButton.setVisible(false);
        outputTextField.setVisible(false);
        newButton.setVisible(false);
        badButton.setVisible(false);
        goodButton.setVisible(false);
        doneButton.setVisible(false);
    }

    public void nextFleshCard(){
        List<FetchedFlashCard> flashCards = flashCardClient.getFlashCardsByUserID(LOGGED_USER.getId());
        FetchedFlashCard first = flashCards.stream()
                .min(Comparator.comparing(FetchedFlashCard::getUpdated))
                .get();
        nextFlashcard = first;
        inputTextField.setText(nextFlashcard.getName());
        inputTextField.setVisible(true);
        checkButton.setVisible(true);
        nextButton.setVisible(false);
    }

    public void checkFleshCard() throws  Exception{
        byte[] amazonCardOutputStream = nextFlashcard.getAmazonCardDto().getBytes();
        InputStream inputStream = new ByteArrayInputStream(amazonCardOutputStream);
        AdvancedPlayer player = new AdvancedPlayer(inputStream, javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());
        player.play();

        outputTextField.setText(nextFlashcard.getAmazonCardDto().getTranslation());
        outputTextField.setVisible(true);

        newButton.setVisible(true);
        badButton.setVisible(true);
        goodButton.setVisible(true);
        doneButton.setVisible(true);
    }

}
