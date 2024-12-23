
package kyungseo.poc.todo.common.base;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Component
public class GenericResponse {

    // private final static Logger LOGGER = LoggerFactory.getLogger(GenericResponse.class);

    // Success

    public static ResponseEntity<GenericResponseBody> success(String successMessage) {
        return success(HttpStatus.OK, successMessage, Collections.emptyList());
    }

    public static ResponseEntity<GenericResponseBody> success(String successMessage, Object result) {
        return success(HttpStatus.OK, successMessage, result);
    }

    public static ResponseEntity<GenericResponseBody> success(HttpStatus status, String successMessage, Object result) {
        return ResponseEntity.ok(getSuccessBody(status, successMessage, result));
    }

    // Fail

    public static ResponseEntity<GenericResponseBody> fail(String errorMessage) {
        return fail(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, Collections.emptyList());
    }

    public static ResponseEntity<GenericResponseBody> fail(HttpStatus status, String errorMessage) {
        return fail(status, errorMessage, Collections.emptyList());
    }

    public static ResponseEntity<GenericResponseBody> fail(List<ObjectError> allErrors) {
        return fail(HttpStatus.INTERNAL_SERVER_ERROR, StringUtils.EMPTY, allErrors);
    }

    public static ResponseEntity<GenericResponseBody> fail(HttpStatus status, String errorMessage, List<ObjectError> allErrors) {
        return ResponseEntity.ok(getErrorBody(status, errorMessage, allErrors));
    }

    // Utils

    public static GenericResponseBody getSuccessBody(HttpStatus status, String successMessage, Object result) {
        GenericResponseBody body = GenericResponseBody.builder()
                .state(status.value())
                .success(true)
                .message(successMessage)
                .fieldErrors(StringUtils.EMPTY)
                .result(result)
                .timestamp(LocalDateTime.now())
                .build();
        return body;
    }

    public static GenericResponseBody getErrorBody(HttpStatus status, String errorMessage, List<ObjectError> allErrors) {
        return GenericResponseBody.builder()
                .state(status.value())
                .success(false)
                .message(errorMessage)
                .fieldErrors(convertString(allErrors))
                .result(null)
                .timestamp(LocalDateTime.now())
                .build();
    }

    private static String convertString(List<ObjectError> allErrors) {
        //allErrors
        //    .stream().map(x -> x.getDefaultMessage())
        //    .collect(Collectors.joining(","));
        String fieldErrors = allErrors.stream().map(e -> {
            if (e instanceof FieldError) {
                //return "{\"field\":\"" + ((FieldError) e).getField() + "\",\"defaultMessage\":\"'" + ((FieldError) e).getField() + "' 값이 잘못되었습니다.\"}";
                return "{\"field\":\"" + ((FieldError) e).getField() + "\",\"defaultMessage\":\"" + e.getDefaultMessage().replace("\"", "'") + "\"}";
            } else {
                //return "{\"object\":\"" + e.getObjectName() + "\",\"defaultMessage\":\"'" + e.getObjectName() + "' 값이 잘못되었습니다.\"}";
                return "{\"object\":\"" + e.getObjectName() + "\",\"defaultMessage\":\"" + e.getDefaultMessage().replace("\"", "'") + "\"}";
            }
        }).collect(Collectors.joining(","));

        return "[" + fieldErrors + "]";
    }

}