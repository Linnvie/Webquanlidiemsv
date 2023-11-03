package com.ptit.apiquanlidiem.exception;

import com.ptit.apiquanlidiem.dto.BaseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public BaseDto handleInvalidArgument(MethodArgumentNotValidException ex) {
            Map<String, String> errorMap = new HashMap<>();
            BaseDto responseDto = new BaseDto();
            responseDto.setStatus("BAD_REQUEST");
            responseDto.setCode(HttpStatus.BAD_REQUEST.value());

            List<String> errorMessages = new ArrayList<>();
            ex.getBindingResult().getFieldErrors().forEach(error -> {
                String field = error.getField();
                String defaultMessage = error.getDefaultMessage();
                errorMap.put(field, defaultMessage);
                errorMessages.add(defaultMessage);
            });

            if (errorMessages.size() == 1) {
                responseDto.setMessage(errorMessages.get(0));
            } else {
                responseDto.setMessage(errorMessages);
            }

            return responseDto;
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<BaseDto> handleIllegalArgumentException(IllegalArgumentException ex) {
            BaseDto response = new BaseDto();
            response.setStatus("BAD_REQUEST");
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<BaseDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
            BaseDto response = new BaseDto();
            response.setStatus(HttpStatus.NOT_FOUND.series().name());
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        @ExceptionHandler(DuplicateResourceException.class)
        public ResponseEntity<BaseDto> handleDuplicateResourceException(DuplicateResourceException ex) {
            BaseDto response = new BaseDto();
            response.setStatus(HttpStatus.CONFLICT.series().name());
            response.setCode(HttpStatus.CONFLICT.value());
            response.setMessage(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        @ExceptionHandler(InternalServerErrorException.class)
        public ResponseEntity<BaseDto> handleInternalServerErrorException(InternalServerErrorException ex) {
            BaseDto response = new BaseDto();
            response.setStatus("ERR_BAD_REQUEST");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        @ExceptionHandler(AuthorizeException.class)
        public ResponseEntity<BaseDto> handleAuthorizeException(AuthorizeException ex) {
            BaseDto response = new BaseDto();
            response.setStatus("FORBIDDEN");
            response.setCode(403);
            response.setMessage(ex.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        @ExceptionHandler(NotActivatedExceptionHandler.class)
        public ResponseEntity<BaseDto> handleNotActivatedExceptionHandler(NotActivatedExceptionHandler ex) {
            BaseDto response = new BaseDto();
            response.setStatus("NOT_ACTIVATED");
            response.setCode(403);
            response.setMessage(ex.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

//        @ExceptionHandler(InvalidEmailOrPasswordException.class)
//        public ResponseEntity<BaseDto> handleInvalidEmailOrPasswordException(InvalidEmailOrPasswordException ex) {
//            BaseDto response = new BaseDto();
//            response.setStatus("INVALID_EMAIL_OR_PASSWORD");
//            response.setCode(HttpStatus.UNAUTHORIZED.value());
//            response.setMessage(ex.getMessage());
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//        }
//
//        @ExceptionHandler(EmailAlreadyExistException.class)
//        public ResponseEntity<BaseDto> handleEmailAlreadyExistException(EmailAlreadyExistException ex) {
//            BaseDto response = new BaseDto();
//            response.setStatus("EMAIL_ALREADY_EXIST");
//            response.setCode(HttpStatus.CONFLICT.value());
//            response.setMessage(ex.getMessage());
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
//        }

    }

