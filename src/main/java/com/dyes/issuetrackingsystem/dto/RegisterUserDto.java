package com.dyes.issuetrackingsystem.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class RegisterUserDto {

    private String email;
    private String password;
    private String fullName;
}
