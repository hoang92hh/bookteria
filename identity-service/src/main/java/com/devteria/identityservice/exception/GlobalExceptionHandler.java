package com.devteria.identityservice.exception;

import com.devteria.identityservice.dto.request.ApiResponse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.Map;
import java.util.Objects;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String MIN_ATTRIBUTE = "min";

//    @ExceptionHandler(value = Exception.class)
//    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception){
//        ApiResponse apiResponse = new ApiResponse();
//
//        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
//        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
//
//                return ResponseEntity
//                .badRequest()
//                .body(apiResponse);
//    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(apiResponse);
    }


    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AccessDeniedException exception){
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        return ResponseEntity.status(errorCode.getStatusCode())
                .body(ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build()
                      );
    }

    /* Exception for @Anotation of Spring */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception){


        ErrorCode errorCode = ErrorCode.INVALID_KEY; // error code default when exception xay ra;

        Map<String, Object> attributes = null ;


        try {
            String enumKey = exception.getFieldError().getDefaultMessage();/*get value of message() from field is not valid : */
            log.info(enumKey);
            errorCode = ErrorCode.valueOf(enumKey); // override errocode default khi 1 exception xay ra da duoc dinh nghia trong ErroCode theo enumKey.

            ConstraintViolation constraintViolation = exception.getBindingResult()
                    .getAllErrors().getFirst().unwrap(ConstraintViolation.class);

            attributes = constraintViolation.getConstraintDescriptor().getAttributes();

            log.info(attributes.toString());

        } catch (IllegalArgumentException e){

        }
        finally {

            ApiResponse apiResponse = new ApiResponse();

            apiResponse.setCode(errorCode.getCode());
            apiResponse.setMessage(Objects.nonNull(attributes) ?
                    mapAttribute(errorCode.getMessage(), attributes)
                    : errorCode.getMessage());

            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    private String mapAttribute(String message, Map<String,Object> attributes){
        //get value tu attribute
        String minValue = String.valueOf(attributes.get(MIN_ATTRIBUTE));

        return message.replace("{"+ MIN_ATTRIBUTE + "}", minValue);
    };
}
