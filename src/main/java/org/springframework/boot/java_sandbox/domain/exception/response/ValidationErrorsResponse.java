package org.springframework.boot.java_sandbox.domain.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ValidationErrorsResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;
    private List<FieldError> errors;

    @AllArgsConstructor
    @Getter
    @Setter
    public static class FieldError {
        private String field;
        private String message;
    }
}
