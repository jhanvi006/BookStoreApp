package com.bridgelabz.bookstoreapp.exceptions;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {
    private static final String message = "Exception while processing REST request";
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception){
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errMsg = errorList.stream()
                .map(objErr -> objErr.getDefaultMessage())
                .collect(Collectors.toList());
        ResponseDTO respDTO = new ResponseDTO(message, errMsg);
        return new ResponseEntity<>(respDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseDTO> handleEmployeePayrollException(CustomException exception){
        ResponseDTO respDTO = new ResponseDTO(message, exception.getMessage());
        return new ResponseEntity<>(respDTO, HttpStatus.BAD_REQUEST);
    }
//    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
//    public ResponseEntity<ResponseDTO> handleSQLIntegrityConstraintViolationException(
//            SQLIntegrityConstraintViolationException exception){
//        ResponseDTO responseDTO = new ResponseDTO(message, "UserId or BookId doesn't exists.");
//        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
//    }

}
