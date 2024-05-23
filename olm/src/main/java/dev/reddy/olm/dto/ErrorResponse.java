package dev.reddy.olm.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorResponse {

    private String message;
    private HttpStatus httpStatus;
    private int code;
    private final long timestamp;

    protected ErrorResponse(String message, HttpStatus status, int code) {
        this.message = message;
        this.httpStatus = status;
        this.code = code;
        this.timestamp = System.currentTimeMillis();
    }

    public static ErrorResponse of(String message, HttpStatus status, int code) {
        return new ErrorResponse(message, status, code);
    }
}
