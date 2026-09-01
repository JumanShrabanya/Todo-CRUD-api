package com.js.todo_app.service;

import com.js.todo_app.dto.request.ChageTaskCompletion;
import com.js.todo_app.dto.request.TaskTitleUpdateReq;
import com.js.todo_app.dto.request.TodoCreate;
import com.js.todo_app.dto.response.AllTaskReturn;
import com.js.todo_app.entity.Todo;
import com.js.todo_app.exception.TaskNotFoundException;
import com.js.todo_app.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

//    Change task title
    public AllTaskReturn changeTaskTitle(Long id, TaskTitleUpdateReq taskTitleUpdateReq){
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException("Task not found with id: "+id));

        todo.setTask(taskTitleUpdateReq.task());

        Todo savedTodo=todoRepository.save(todo);

        return new AllTaskReturn(
                savedTodo.getId(),
                savedTodo.getTask(),
                savedTodo.isComplete(),
                savedTodo.getCreatedAt()
        );
    }

//    change task completion
    public AllTaskReturn changeTaskCompletion(Long id){
        Todo todo= todoRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException("Task not found with the id: "+id));

        todo.setComplete(!todo.isComplete());

        Todo savedTodo = todoRepository.save(todo);

        return new AllTaskReturn(
                savedTodo.getId(),
                savedTodo.getTask(),
                savedTodo.isComplete(),
                savedTodo.getCreatedAt()
        );
    }
//    delete a task
    public void deleteTask(Long id){
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException("Task not found with id: "+id));

        todoRepository.deleteById(todo.getId());
    }
}
