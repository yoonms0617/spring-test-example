package com.example.springtest.user.controller;

import com.example.springtest.user.domain.User;
import com.example.springtest.user.dto.UserEmailRequest;
import com.example.springtest.user.dto.UserInfoResponse;
import com.example.springtest.user.dto.UserListResponse;
import com.example.springtest.user.dto.UserSaveRequest;
import com.example.springtest.user.service.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
@ExtendWith(SpringExtension.class)
@MockBean(JpaMetamodelMappingContext.class)
class UserControllerTest {

    private static final String HOME = "/";

    private static final UserInfoResponse USER_INFO_RESPONSE = new UserInfoResponse("yoon", "yoon@test.com");
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

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("회원을 저장한다.")
    void user_save_test() throws Exception {
        willDoNothing().given(userService).save(any(UserSaveRequest.class));

        String content = objectMapper.writeValueAsString(USER_SAVE_REQUEST);
        mockMvc.perform(
                post("/user/save")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrl(HOME));
    }

    @Test
    @DisplayName("Email로 회원을 조회한다.")
    void user_find_email_test() throws Exception {
        given(userService.findByEmail(any(UserEmailRequest.class))).willReturn(USER_INFO_RESPONSE);

        String content = objectMapper.writeValueAsString(USER_EMAIL_REQUEST);
        mockMvc.perform(
                get("/user")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(USER_INFO_RESPONSE)));
    }

    @Test
    @DisplayName("모든 회원을 조회한다.")
    void user_find_all_test() throws Exception {
        given(userService.findAll()).willReturn(USER_LIST_RESPONSE);

        mockMvc.perform(
                get("/user/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(USER_LIST_RESPONSE)));
    }

}