package dev.reddy.olm.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class ApiResponse {

    private Object data;
    private Object errors;
    private String status;

    public ApiResponse(Object data, Object errors, String status) {
        this.data = data;
        this.errors = errors;
        this.status = status;
    }

    public static ApiResponse getSuccessResponse(Object data) {
        return new ApiResponse(data, Collections.emptyList(), "success");
    }

    public static ApiResponse getErrorResponse(Object errors) {
        return new ApiResponse(Collections.emptyList(), errors, "failure");
    }

    public static ApiResponse getResponse() {
        return new ApiResponse(new Object(), Collections.emptyList(), "success");
    }

}
