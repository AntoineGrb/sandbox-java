package org.springframework.boot.java_sandbox.domain.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/*
    Classe qui va permettre de renvoyer un message d'erreur en JSON
 */

@AllArgsConstructor
@Getter
@Setter
public class ApiErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
