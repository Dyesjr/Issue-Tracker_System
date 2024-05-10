package com.dyes.issuetrackingsystem.service.query;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginResponse {

    private String token;

    private Long expiresIn;
}

