package com.jpro.flashCardsUi.mapper;

import com.jpro.flashCardsUi.domain.FetchedFlashCard;
import com.jpro.flashCardsUi.domain.TableViewFlashCard;

import java.util.List;
import java.util.stream.Collectors;

public class FlashCardMapper {

    public TableViewFlashCard mapToTableViewFlashCard(FetchedFlashCard fetchedFlashCard) {
        return new TableViewFlashCard(fetchedFlashCard.getId(),
                fetchedFlashCard.getName(),
                fetchedFlashCard.getLanguage(),
                fetchedFlashCard.getFlashCardProgress());
    }


    public List<TableViewFlashCard> mapToTableViewFlashCardList(List<FetchedFlashCard> fetchedFlashCardList) {
        return fetchedFlashCardList.stream()
                .map(flashCard -> new TableViewFlashCard(flashCard.getId(),flashCard.getName(),flashCard.getLanguage(),flashCard.getFlashCardProgress()))
                .collect(Collectors.toList());
    }
}
