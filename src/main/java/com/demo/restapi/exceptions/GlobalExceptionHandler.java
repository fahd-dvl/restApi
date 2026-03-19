package com.demo.restapi.exceptions;

import com.demo.restapi.dtos.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. RESOURCE_NOT_FOUND
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        return buildResponse( ex.getMessage(),"RESOURCE_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    // 2. The JSON is physically broken (missing comma, bracket, etc.)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleMalformedJson(HttpMessageNotReadableException ex) {
        return buildResponse("The JSON body is syntactically incorrect or unreadable.", "BODY_NOT_VALID", HttpStatus.BAD_REQUEST);
    }

    // 3. The JSON is fine, but the DATA is wrong (e.g., price is -10)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationFields(MethodArgumentNotValidException ex) {
        // Senior move: Extract which specific field failed
        String field = ex.getBindingResult().getFieldError().getField();
        String msg = "Validation failed for field: " + field;
        return buildResponse(msg, "BODY_NOT_VALID", HttpStatus.BAD_REQUEST);
    }
    // 4. The user forgot a required @RequestParam
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParams(MissingServletRequestParameterException ex) {
        String msg = "Required parameter is missing: " + ex.getParameterName();
        return buildResponse(msg, "PARAMS_NOT_VALID", HttpStatus.BAD_REQUEST);
    }

    // 5. The user sent 'hello' instead of a numeric ID
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String msg = "Parameter '" + ex.getName() + "' should be of type " + ex.getRequiredType().getSimpleName();
        return buildResponse(msg, "PARAMS_NOT_VALID", HttpStatus.BAD_REQUEST);
    }

    // 6. PATH_NOT_FOUND (New)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handlePathNotFound(NoHandlerFoundException ex) {
        return buildResponse("Request path is not valid, check documentation.", "PATH_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
    // 7. SERVER_ERROR (New - MUST BE LAST)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalError(Exception ex) {
        // Log ex.printStackTrace() here for your own debugging
        return buildResponse("Something is wrong with server, try again later.", "SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // 9.For the business
    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<ErrorResponse> handleBusinessError(BusinessLogicException ex) {
        // We map this to BODY_NOT_VALID because the input data (the email) is the problem
        return buildResponse(ex.getMessage(), "BODY_NOT_VALID", HttpStatus.BAD_REQUEST);
    }
    // 10.Works with requestParam
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraint(ConstraintViolationException ex) {
        return buildResponse(ex.getMessage(), "PARAMS_NOT_VALID", HttpStatus.BAD_REQUEST);
    }











    private ResponseEntity<ErrorResponse> buildResponse(String msg, String code, HttpStatus status) {
        ErrorResponse err = new ErrorResponse(code, msg, LocalDateTime.now());
        return new ResponseEntity<>(err, status);
    }
}
