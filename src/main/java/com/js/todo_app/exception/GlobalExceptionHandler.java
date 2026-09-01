package com.js.todo_app.exception;

import com.js.todo_app.dto.response.TaskNotFoundRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<TaskNotFoundRes> handleTaskNotFound(TaskNotFoundException ex){
        TaskNotFoundRes notFound = new TaskNotFoundRes(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()

        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(notFound);
    }
}
