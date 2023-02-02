package com.todo.project.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.todo.project.models.Todo;
import com.todo.project.repository.TodoRepository;
import com.todo.project.service.TodoService;

@RestController
public class TodoController {

	@Autowired
	TodoService todoService;
	
	
	@Autowired
	TodoRepository todoRepository;

	//Create
	
	@PostMapping(value = "/addtodo")
	public Todo addTodo(@RequestBody Todo todo) {
		return todoService.saveTodo(todo);
	}
	
	
	//Read
	
		@GetMapping(value="/todolist")
		public List<Todo> todoList (){
			return todoRepository.findAll();
			}
	
	//Read

		@GetMapping(value = "/todos")
		public Page<Todo> getTodos(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy) {
			return todoRepository.findAll(PageRequest.of(page.orElse(0), 5, Sort.Direction.ASC, sortBy.orElse("id")));

		}

	//Update

	@PutMapping(value = "/updatetodo/{id}")
	public String updateTodo(@PathVariable int id, @RequestBody Todo todo) {
		return todoService.updateTodo(id, todo);

	}

	//Delete

	@DeleteMapping(value = "/deletetodo/{id}")
	public String deleteTodo(@PathVariable int id) {
		return todoService.deleteTodo(id);
	}



}
