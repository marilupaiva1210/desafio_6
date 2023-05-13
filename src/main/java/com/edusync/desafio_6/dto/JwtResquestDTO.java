package com.edusync.desafio_6.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class JwtResquestDTO implements Serializable {
    private static final long serialVersiononUID = 1L;

    private String username;
    private String password;
}
