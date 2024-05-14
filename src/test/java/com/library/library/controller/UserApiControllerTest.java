package com.library.library.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import com.library.library.domain.User;
import com.library.library.repository.UserRepository;

public class UserApiControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testLogout() {

    }

    @DisplayName("회원가입에 성공한다")
    @Test
    @WithMockUser
    public void testSignup() throws Exception {
        // final String url = "/user";
        final String studentId = "studentId";
        final String password = "password";
        final String nickname = "nickname";
        final String phone_number = "phone_number";
        User testUser = userRepository.save(User.builder()
                .studentID(studentId)
                .password(password)
                .nickname(nickname)
                .phone_number(phone_number)
                .build());
        System.out.println(testUser);
    }
}
