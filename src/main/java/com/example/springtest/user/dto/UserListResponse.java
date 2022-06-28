package com.example.springtest.user.dto;

import com.example.springtest.user.domain.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"userLists"})
public class UserListResponse {

    private List<UserList> userLists;

    public UserListResponse(List<User> users) {
        this.userLists = users.stream()
                .map(user -> new UserList(user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @ToString(of = {"name", "email"})
    public static class UserList {

        private String name;

        private String email;

        public UserList(String name, String email) {
            this.name = name;
            this.email = email;
        }

    }

}
