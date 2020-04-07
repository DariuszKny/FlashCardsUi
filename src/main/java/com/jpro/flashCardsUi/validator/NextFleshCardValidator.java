package com.jpro.flashCardsUi.validator;

import com.jpro.flashCardsUi.FlashCardsUI;
import com.jpro.flashCardsUi.client.FlashCardClient;
import com.jpro.flashCardsUi.domain.FetchedFlashCard;
import com.jpro.flashCardsUi.domain.FlashCardProgress;

import java.util.Comparator;
import java.util.List;

public class NextFleshCardValidator {

    public FetchedFlashCard getFirstFlashCard() {
        FlashCardClient flashCardClient = new FlashCardClient();
        List<FetchedFlashCard> flashCards1 = flashCardClient.getFlashCardsByUserIdAndLanguageAndProgress(FlashCardsUI.LOGGED_USER.getId(), FlashCardsUI.LOGGED_USER.getLanguage(), FlashCardProgress.NEW);
        List<FetchedFlashCard> flashCards2 = null;
        List<FetchedFlashCard> flashCards3 = null;
        List<FetchedFlashCard> flashCards4 = null;

        if (!flashCards1.isEmpty()) {
            return nextFleshCard(flashCards1);
        } else {
            flashCards2 = flashCardClient.getFlashCardsByUserIdAndLanguageAndProgress(FlashCardsUI.LOGGED_USER.getId(), FlashCardsUI.LOGGED_USER.getLanguage(), FlashCardProgress.BAD);
        }
        if (!flashCards2.isEmpty()) {
            return nextFleshCard(flashCards2);
        } else {
            flashCards3 = flashCardClient.getFlashCardsByUserIdAndLanguageAndProgress(FlashCardsUI.LOGGED_USER.getId(), FlashCardsUI.LOGGED_USER.getLanguage(), FlashCardProgress.GOOD);
        }
        if (!flashCards3.isEmpty()) {
            return nextFleshCard(flashCards3);
        } else {
            flashCards4 = flashCardClient.getFlashCardsByUserIdAndLanguageAndProgress(FlashCardsUI.LOGGED_USER.getId(), FlashCardsUI.LOGGED_USER.getLanguage(), FlashCardProgress.DONE);
        }
        if (!flashCards4.isEmpty()) {
            return nextFleshCard(flashCards4);
        }
        return null;
    }


    private FetchedFlashCard nextFleshCard(List<FetchedFlashCard> fetchedFlashCards) {
        return fetchedFlashCards.stream()
                .min(Comparator.comparing(FetchedFlashCard::getUpdated))
                .get();
    }
}
