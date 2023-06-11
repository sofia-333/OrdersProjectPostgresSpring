package ge.ibsu.demo.controllers;

import ge.ibsu.demo.dto.ErrorMessage;
import ge.ibsu.demo.exceptions.PreConditionException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseController {
    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(e.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(PreConditionException.class)
    public ResponseEntity<ErrorMessage> handlePreConditionException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.PRECONDITION_FAILED)
                .body(new ErrorMessage(e.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage(e.getMessage()));
    }

}
