package com.js.todo_app.service;

import com.js.todo_app.dto.request.TodoCreate;
import com.js.todo_app.dto.response.AllTaskReturn;
import com.js.todo_app.entity.Todo;
import com.js.todo_app.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private  final TodoRepository todoRepository;

//    to get all the tasks
    public List<AllTaskReturn> getTasks(){
        return todoRepository.findAll()
                .stream()
                .map(todo -> new AllTaskReturn(
                        todo.getId(),
                        todo.getTask(),
                        todo.isComplete(),
                        todo.getCreatedAt()
                ))
                .toList();
    }

//    to add a new task
    public AllTaskReturn addTask(TodoCreate todo){
        Todo newTodo=new Todo();

        newTodo.setTask(todo.task());
        newTodo.setComplete(false);
        newTodo.setCreatedAt(new Date());

        Todo savedTodo=todoRepository.save(newTodo);
        return new AllTaskReturn(
                savedTodo.getId(),
                savedTodo.getTask(),
                savedTodo.isComplete(),
                savedTodo.getCreatedAt()
        );
    }
}
