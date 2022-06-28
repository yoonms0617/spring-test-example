package com.example.springtest.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"name", "email"})
public class UserInfoResponse {

    private String name;

    private String email;

    public UserInfoResponse(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
