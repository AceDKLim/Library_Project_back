package com.library.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.library.library.domain.User;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {

    User findByStudentID(String studentID);

    User findByNickname(String nickname);
}
