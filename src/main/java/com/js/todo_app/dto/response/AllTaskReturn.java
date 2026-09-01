package com.js.todo_app.dto.response;

import java.util.Date;

public record AllTaskReturn(
        Long id,
        String task,
        boolean isComplete,
        Date createdAt
) {
};
