package com.library.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {

    private String studentID;
    private String password;
    private String nickname;
    private String phone_number;
}
