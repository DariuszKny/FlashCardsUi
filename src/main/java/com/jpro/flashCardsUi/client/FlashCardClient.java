package com.jpro.flashCardsUi.client;

import com.jpro.flashCardsUi.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;

public class FlashCardClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlashCardClient.class);


    public List<FetchedFlashCard> getFlashCardsByUserIdAndLanguage(Long id, Language language) {
        RestTemplate restTemplate = new RestTemplate();
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/flashCard/getFlashCards")
                .queryParam("userId", id)
                .queryParam("language", language)
                .build().encode().toUri();
        try {
            FetchedFlashCard[] boardResponse = restTemplate.getForObject(url, FetchedFlashCard[].class);
            return Arrays.asList(Optional.ofNullable(boardResponse).orElse(new FetchedFlashCard[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public FetchedFlashCard getFlashCard(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/flashCard/getFlashCard")
                .queryParam("flashCardId", id)
                .build().encode().toUri();
        try {
            FetchedFlashCard boardResponse = restTemplate.getForObject(url, FetchedFlashCard.class);
            return boardResponse;
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public List<FetchedFlashCard> getFlashCardsByUserIdAndLanguageAndProgress(Long id, Language language, FlashCardProgress flashCardProgress) {
        RestTemplate restTemplate = new RestTemplate();
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/flashCard/getFlashCardsByProgress")
                .queryParam("userId", id)
                .queryParam("language", language)
                .queryParam("flashCardProgress", flashCardProgress)
                .build().encode().toUri();
        try {
            FetchedFlashCard[] boardResponse = restTemplate.getForObject(url, FetchedFlashCard[].class);
            return Arrays.asList(Optional.ofNullable(boardResponse).orElse(new FetchedFlashCard[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public void addFlashCard(String name, FetchedUser fetchedUser, FetchedAmazonCard fetchedAmazonCard) {
        String url = "http://localhost:8080/v1/flashCard/addFlashCard";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("language", fetchedUser.getLanguage());
        map.put("flashCardProgress", FlashCardProgress.NEW);
        map.put("updated", LocalDateTime.now());
        map.put("userDto", fetchedUser);
        map.put("amazonCardDto", fetchedAmazonCard);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            System.out.println("Request Successful");
            System.out.println(response.getBody());
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
    }

    public void updateFlashCard(FetchedFlashCard fetchedFlashCard, FlashCardProgress flashCardProgress) {
        String url = "http://localhost:8080/v1/flashCard/updateFlashCard";
        RestTemplate restTemplate = new RestTemplate();

        fetchedFlashCard.setFlashCardProgress(flashCardProgress);
        fetchedFlashCard.setUpdated(LocalDateTime.now());

        restTemplate.put(url, fetchedFlashCard);
    }

    public void deleteFlashCard(Long flashCardId) {
        RestTemplate restTemplate = new RestTemplate();
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/flashCard/deleteFlashCard")
                .queryParam("flashCardId", flashCardId)
                .build().encode().toUri();
        try {
            restTemplate.delete(url);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
