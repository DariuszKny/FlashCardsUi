package com.jpro.flashCardsUi.viewControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jpro.flashCardsUi.client.FlashCardClient;
import com.jpro.flashCardsUi.domain.FetchedFlashCard;
import com.jpro.flashCardsUi.domain.FlashCardProgress;
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
        disableAll();
    }

    private void disableAll(){
        checkButton.setDisable(true);
        newButton.setDisable(true);
        badButton.setDisable(true);
        goodButton.setDisable(true);
        doneButton.setDisable(true);
    }

    private void clearText(){
        inputTextField.setText("");
        outputTextField.setText("");
    }

    public void nextFleshCard(){
        List<FetchedFlashCard> flashCards = flashCardClient.getFlashCardsByUserID(LOGGED_USER.getId());
        FetchedFlashCard first = flashCards.stream()
                .min(Comparator.comparing(FetchedFlashCard::getUpdated))
                .get();
        nextFlashcard = first;
        inputTextField.setText(nextFlashcard.getName());
        nextButton.setDisable(true);
        checkButton.setDisable(false);
    }

    public void checkFleshCard() throws  Exception{
        byte[] amazonCardOutputStream = nextFlashcard.getAmazonCardDto().getBytes();
        InputStream inputStream = new ByteArrayInputStream(amazonCardOutputStream);
        AdvancedPlayer player = new AdvancedPlayer(inputStream, javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());
        player.play();

        outputTextField.setText(nextFlashcard.getAmazonCardDto().getTranslation());

        newButton.setDisable(false);
        badButton.setDisable(false);
        goodButton.setDisable(false);
        doneButton.setDisable(false);
    }

    public void updateFleshCardToNew(){
        update(FlashCardProgress.NEW);
        disableAll();
        nextButton.setDisable(false);
    }

    public void updateFleshCardToBad(){
        update(FlashCardProgress.BAD);
        disableAll();
        nextButton.setDisable(false);
    }

    public void updateFleshCardToGood(){
        update(FlashCardProgress.GOOD);
        disableAll();
        nextButton.setDisable(false);
    }

    public void updateFleshCardToDone(){
        update(FlashCardProgress.DONE);
        disableAll();
        nextButton.setDisable(false);
    }

    private void update(FlashCardProgress flashCardProgress){
        flashCardClient.updateFlashCard(nextFlashcard,flashCardProgress);
        disableAll();
        nextButton.setDisable(false);
    }

}
