package com.example.springtest.user.service;

import com.example.springtest.user.domain.User;
import com.example.springtest.user.dto.UserEmailRequest;
import com.example.springtest.user.dto.UserInfoResponse;
import com.example.springtest.user.dto.UserListResponse;
import com.example.springtest.user.dto.UserSaveRequest;
import com.example.springtest.user.exception.DuplicatedEmailException;
import com.example.springtest.user.exception.NotFoundUserException;
import com.example.springtest.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void save(UserSaveRequest request) {
        checkEmailDuplicate(request.getEmail());
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();
        userRepository.save(user);
    }

    private void checkEmailDuplicate(String email) {
        if (isExsitsEmail(email)) {
            throw new DuplicatedEmailException();
        }
    }

    private boolean isExsitsEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    @Transactional(readOnly = true)
    public UserInfoResponse findByEmail(UserEmailRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(NotFoundUserException::new);
        return new UserInfoResponse(user.getName(), user.getEmail());
    }

    @Transactional(readOnly = true)
    public UserListResponse findAll() {
        List<User> users = userRepository.findAll();
        return new UserListResponse(users);
    }

}
