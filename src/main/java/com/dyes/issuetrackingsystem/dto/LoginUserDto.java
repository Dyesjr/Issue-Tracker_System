package com.dyes.issuetrackingsystem.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class LoginUserDto {

    private String email;
    private String password;
}
