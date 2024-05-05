package com.pizeon.daru.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pizeon.daru.dto.cmmn.ResultDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultDTO<?>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    	ResultDTO<?> resultDTO = ResultDTO.builder()
    			.success(false)
    			.message("오류가 발생했습니다.")
    			.build();
    	return new ResponseEntity<>(resultDTO, HttpStatus.BAD_REQUEST);
    }
}
