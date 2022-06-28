package com.example.springtest.user.repository;

import com.example.springtest.user.domain.User;
import com.example.springtest.user.exception.NotFoundUserException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@DisplayName("User 리포지토리 테스트")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .name("yoon")
                .email("yoon@test.com")
                .build();
    }

    @Test
    @DisplayName("User를 데이터베이스에 저장한다.")
    void user_save_test() {
        User save = userRepository.save(user);

        assertThat(save.getId()).isNotNull();
        assertThat(save.getName()).isEqualTo(user.getName());
        assertThat(save.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    @DisplayName("존재하는 Email을 가진 User를 저장하면 예외가 발생한다.")
    void user_save_duplicate_email_test() {
        userRepository.save(user);
        User duplicateEmailUser = User.builder()
                .name("yoon")
                .email("yoon@test.com")
                .build();

        assertThatThrownBy(() -> userRepository.save(duplicateEmailUser))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("id로 User를 조회한다.")
    void user_find_by_id_test() {
        User save = userRepository.save(user);

        User find = userRepository.findById(save.getId())
                .orElseThrow(NotFoundUserException::new);

        assertThat(save.getId()).isEqualTo(find.getId());
        assertThat(save.getName()).isEqualTo(find.getName());
        assertThat(save.getEmail()).isEqualTo(find.getEmail());
    }

    @Test
    @DisplayName("email로 User를 조회한다.")
    void user_find_by_email_test() {
        User save = userRepository.save(user);

        User find = userRepository.findByEmail(save.getEmail())
                .orElseThrow(NotFoundUserException::new);

        assertThat(save.getId()).isEqualTo(find.getId());
        assertThat(save.getName()).isEqualTo(find.getName());
        assertThat(save.getEmail()).isEqualTo(find.getEmail());
    }

    @Test
    @DisplayName("존재하는 User를 삭제할 수 있다.")
    void user_delete_test() {
        User save = userRepository.save(user);

        userRepository.delete(save);
        List<User> users = userRepository.findAll();

        assertThat(users).isEmpty();
    }

    @Test
    @DisplayName("email 존재하는지 여부를 확인한다.")
    void user_exists_by_nickname_test() {
        userRepository.save(user);

        boolean existsEmail = userRepository.existsByEmail(user.getEmail());
        boolean nonExistsEmail = userRepository.existsByEmail("helloworld");

        assertThat(existsEmail).isTrue();
        assertThat(nonExistsEmail).isFalse();
    }

}