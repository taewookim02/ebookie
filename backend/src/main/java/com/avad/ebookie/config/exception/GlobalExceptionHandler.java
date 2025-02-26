package com.avad.ebookie.config.exception;

import com.avad.ebookie.domain.auth.exception.EmailDuplicateException;
import com.avad.ebookie.domain.auth.exception.MemberNotFoundException;
import com.avad.ebookie.domain.auth.exception.PasswordMismatchException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // JWT 토큰 유효 x
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorResponse> handleSignatureException(SignatureException ex) {
        log.error("handleSignatureException", ex);
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.SIGNATURE_EXCEPTION);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    // JWT 토큰 malformed
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorResponse> handleMalformedJwtException(MalformedJwtException ex) {
        log.error("handleSignatureException", ex);
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.MALFORMED_JWT);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException ex) {
        log.error("handleExpiredJwtException", ex);
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.EXPIRED_JWT);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMemberNotFoundException(MemberNotFoundException ex) {
        log.error("handleMemberNotFoundException", ex);
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.MEMBER_NOT_FOUND);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
        log.error("handleBadCredentialsException", ex);
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BAD_CREDENTIALS);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(EmailDuplicateException.class)
    public ResponseEntity<ErrorResponse> handleEmailDuplicateException(EmailDuplicateException ex) {
        log.error("handleEmailDuplicateException", ex);
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorResponse> handlePasswordMismatchException(PasswordMismatchException ex) {
        log.error("handlePasswordMismatchException", ex);
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    /**
     * https://salithachathuranga94.medium.com/validation-and-exception-handling-in-spring-boot-51597b580ffd
     * spring starter validation 예외처리
     * DTO에서 유효성검사 실패 시 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }


}
