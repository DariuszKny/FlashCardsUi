package com.jpro.flashCardsUi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchedFlashCard {
    private Long id;
    private String name;
    private Language language;
    private FlashCardProgress flashCardProgress;
    private LocalDateTime updated;
    private FetchedUser userDto;
    private FetchedAmazonCard amazonCardDto;
}
