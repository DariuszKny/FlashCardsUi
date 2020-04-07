package com.jpro.flashCardsUi.client;

import com.jpro.flashCardsUi.domain.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class AmazonCardClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(AmazonCardClient.class);


    public byte[] getAudio(String text, Language language) {
        RestTemplate restTemplate = new RestTemplate();
        byte[] bytes = null;
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/amazonCard/getAudio")
                .queryParam("text", text)
                .queryParam("language", language)
                .build().encode().toUri();
        try {
            bytes = restTemplate.getForObject(url, byte[].class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return bytes;
    }

    public String getTranslate(String text, Language language) {
        RestTemplate restTemplate = new RestTemplate();
        String translate = "";
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/amazonCard/getTranslate")
                .queryParam("text", text)
                .queryParam("language", language)
                .build().encode().toUri();
        try {
            translate = restTemplate.getForObject(url, String.class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return translate;
    }
}
