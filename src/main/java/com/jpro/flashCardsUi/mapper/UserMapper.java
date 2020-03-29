package com.jpro.flashCardsUi.mapper;

import com.jpro.flashCardsUi.domain.FetchedUser;
import com.jpro.flashCardsUi.domain.TableViewUser;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public TableViewUser mapToMappedUser(FetchedUser fetchedUser) {
        return new TableViewUser(fetchedUser.getId(), fetchedUser.getName(), fetchedUser.getEmail());
    }


    public List<TableViewUser> mapToMappedUsersList(List<FetchedUser> fetchedUsers) {
      return fetchedUsers.stream()
              .map(user -> new TableViewUser(user.getId(),user.getName(),user.getEmail()))
              .collect(Collectors.toList());
    }
}
