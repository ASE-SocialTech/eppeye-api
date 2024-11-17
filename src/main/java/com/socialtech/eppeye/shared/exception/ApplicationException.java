package com.socialtech.eppeye.shared.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationException extends RuntimeException {
    private HttpStatus status;

    public ApplicationException(HttpStatus status, String _message) {
        super(_message);
        this.status = status;
    }
}
