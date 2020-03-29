package com.jpro.flashCardsUi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchedAmazonCard {
    private Long id;
    private byte[] bytes;
    private String translation;
}
