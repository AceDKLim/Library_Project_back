package com.library.library.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.library.domain.User;
import com.library.library.dto.AddUserRequest;
import com.library.library.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User save(AddUserRequest dto) {
        return userRepository.save(
                User.builder().studentID(dto.getStudentID()).password(bCryptPasswordEncoder.encode(dto.getPassword()))
                        .nickname(dto.getNickname()).phone_number(dto.getPhone_number()).build());
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}
