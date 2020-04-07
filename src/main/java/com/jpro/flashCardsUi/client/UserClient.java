package com.jpro.flashCardsUi.client;

import com.jpro.flashCardsUi.domain.FetchedUser;
import com.jpro.flashCardsUi.domain.Language;
import com.jpro.flashCardsUi.domain.UserAppColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Component
public class UserClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserClient.class);


    public List<FetchedUser> getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/user/getUsers")
                .build().encode().toUri();
        try {
            FetchedUser[] boardResponse = restTemplate.getForObject(url, FetchedUser[].class);
            return Arrays.asList(Optional.ofNullable(boardResponse).orElse(new FetchedUser[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public void deleteUser(Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/user/deleteUser")
                .queryParam("userId", userId)
                .build().encode().toUri();
        try {
            restTemplate.delete(url);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


    public void addUser(String name, String mail, String password) {
        String url = "http://localhost:8080/v1/user/addUser";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("password", password);
        map.put("email", mail);
        map.put("language", Language.ENGLISH);
        map.put("userAppColor", UserAppColor.DARK);

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

    public void updateUser(FetchedUser fetchedUser) {
        String url = "http://localhost:8080/v1/user/updateUser";
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.put(url, fetchedUser);
    }
}

