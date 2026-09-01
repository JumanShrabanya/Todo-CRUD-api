package com.js.todo_app.dto.response;

public record TaskNotFoundRes(
        int status,
        String message
) {
}
