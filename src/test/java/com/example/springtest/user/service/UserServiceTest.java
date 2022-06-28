package com.example.springtest.user.service;

import com.example.springtest.user.domain.User;
import com.example.springtest.user.dto.UserEmailRequest;
import com.example.springtest.user.dto.UserInfoResponse;
import com.example.springtest.user.dto.UserListResponse;
import com.example.springtest.user.dto.UserSaveRequest;
import com.example.springtest.user.repository.UserRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private static final UserEmailRequest USER_EMAIL_REQUEST = new UserEmailRequest("yoon@test.com");
    private static final UserSaveRequest USER_SAVE_REQUEST = new UserSaveRequest("yoon", "yoon@test.com");
    private static final UserListResponse USER_LIST_RESPONSE = new UserListResponse(
            Arrays.asList(
                    User.builder()
                            .name("one")
                            .email("one@test.com")
                            .build(),
                    User.builder()
                            .name("two")
                            .email("two@test.com")
                            .build())
    );

    private static final User USER = User.builder().name("yoon").email("yoon@test.com").build();
    private static final List<User> USERS = Arrays.asList(User.builder()
                    .name("one")
                    .email("one@test.com")
                    .build(),
            User.builder()
                    .name("two")
                    .email("two@test.com")
                    .build());

    @Test
    @DisplayName("회원을 저장한다.")
    void user_save_test() {
        given(userRepository.save(any(User.class))).willReturn(USER);

        userService.save(USER_SAVE_REQUEST);

        then(userRepository).should().save(any(User.class));
    }

    @Test
    @DisplayName("Email로 회원을 조회한다.")
    void user_find_email_test() {
        given(userRepository.findByEmail(any(String.class))).willReturn(Optional.of(USER));

        UserInfoResponse response = userService.findByEmail(USER_EMAIL_REQUEST);

        then(userRepository).should().findByEmail(any(String.class));
        assertThat(response.getName()).isEqualTo("yoon");
        assertThat(response.getEmail()).isEqualTo("yoon@test.com");
    }

    @Test
    @DisplayName("모든 회원을 조회한다.")
    void user_find_all_test() {
        given(userRepository.findAll()).willReturn(USERS);

        UserListResponse userListResponse = userService.findAll();

        then(userRepository).should().findAll();
        assertThat(userListResponse).isNotNull();
    }

}