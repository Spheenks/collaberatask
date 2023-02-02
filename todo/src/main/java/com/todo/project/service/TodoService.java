package com.todo.project.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.todo.project.models.Todo;
import com.todo.project.repository.TodoRepository;



@Service
public class TodoService {
	
	
	@Autowired
    TodoRepository todoRepository;

    public Todo saveTodo(Todo todos) {
        return todoRepository.save(todos);
    }
    
//    
//    public Page<Todo> getTodos( Optional<Integer> page,  Optional<String> sortBy) {
//		return todoRepository.findAll(PageRequest.of(page.orElse(0), 5, Sort.Direction.ASC, sortBy.orElse("id")));
//
//	}
    

    
    public String deleteTodoById(int id) {
    	Todo deleteTodo = todoRepository.findById(id).get();
		todoRepository.delete(deleteTodo);
		
		return "Deleted..";
    }
    
    public String updateTodo(int id, Todo todo) {
		Todo updateTodo = todoRepository.findById(id).get();
		updateTodo.setTitle(todo.getTitle());
		updateTodo.setDescription(todo.getDescription());
		updateTodo.setDone(todo.getIsDone());
		todoRepository.save(updateTodo);
		return "updated..";
	}
    
    public String deleteTodo(int id) {

    	Todo deleteTodo = todoRepository.findById(id).get();
		todoRepository.delete(deleteTodo);
		
		return "deleted!";
	}
    





}
