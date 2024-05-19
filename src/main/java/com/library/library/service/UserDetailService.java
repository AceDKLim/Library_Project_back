package com.library.library.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.library.library.domain.User;
import com.library.library.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String studentID) throws UsernameNotFoundException {
        return userRepository.findByStudentID(studentID).orElseThrow(() -> new UsernameNotFoundException("studentId not found"));
    }
}
