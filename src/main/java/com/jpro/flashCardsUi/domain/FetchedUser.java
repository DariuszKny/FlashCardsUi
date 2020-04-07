package com.jpro.flashCardsUi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchedUser {
    private Long id;
    private String name;
    private String password;
    private String email;
    private Language language;
    private UserAppColor userAppColor;
}
