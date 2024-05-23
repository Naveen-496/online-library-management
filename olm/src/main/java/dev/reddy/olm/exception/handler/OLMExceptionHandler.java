package dev.reddy.olm.exception.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import dev.reddy.olm.dto.ErrorResponse;
import dev.reddy.olm.exception.InvalidCredentialsException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.awt.*;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Controller
@RestControllerAdvice
@Slf4j
public class OLMExceptionHandler implements  ErrorController {

    private final ObjectMapper MAPPER = new ObjectMapper();

    public void handle(Exception exception, HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
              if ( exception instanceof InvalidCredentialsException) {
                  response.setStatus(UNAUTHORIZED.value());
                  var errorResponse = ErrorResponse.of(exception.getMessage(), UNAUTHORIZED, UNAUTHORIZED.value());
                  MAPPER.writeValue(response.getWriter(), errorResponse);
            }
        }catch (Exception e) {
           response.setStatus(500);

        }
    }
}
