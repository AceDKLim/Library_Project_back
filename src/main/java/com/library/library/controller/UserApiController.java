package com.library.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.library.library.domain.User;
import com.library.library.dto.AddUserRequest;
import com.library.library.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;

    @PostMapping("api/signup")
    public ResponseEntity<User> signup(@RequestBody AddUserRequest request) {
        User savedUser = userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("api/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok().build();
    }

    @GetMapping("api/check/id/{studentNumber}")
    public ResponseEntity<Boolean> checkStudentNumber(@PathVariable String studentNumber) {
        try {
            userService.findById(studentNumber);
            return ResponseEntity.accepted().body(true);
        } catch (Exception e) {
            return ResponseEntity.accepted().body(false);
        }
    }

    @GetMapping("api/check/nickname/{nickname}")
    public ResponseEntity<Boolean> chechNickname(@PathVariable String nickname) {
        try {
            userService.findByNickname(nickname);
            return ResponseEntity.accepted().body(true);
        } catch (Exception e) {
            return ResponseEntity.accepted().body(false);
        }

    }

}
