package com.js.todo_app.controller;

import com.js.todo_app.dto.request.ChageTaskCompletion;
import com.js.todo_app.dto.request.TaskTitleUpdateReq;
import com.js.todo_app.dto.request.TodoCreate;
import com.js.todo_app.dto.response.AllTaskReturn;
import com.js.todo_app.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<AllTaskReturn>> getTasks(){
        List<AllTaskReturn> tasks= todoService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<AllTaskReturn> addTask(@RequestBody TodoCreate request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todoService.addTask(request));
    }

    @PatchMapping("/{id}/update-title")
    public ResponseEntity<AllTaskReturn> changeTaskTitle(@PathVariable Long id, @RequestBody TaskTitleUpdateReq request){
        AllTaskReturn response=todoService.changeTaskTitle(id,request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }


    @PatchMapping("/{id}/update-completion")
    public ResponseEntity<AllTaskReturn> changeTaskCompletion(@PathVariable Long id){

        AllTaskReturn response = todoService.changeTaskCompletion(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        todoService.deleteTask(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Task deleted successfully");
    }
}
