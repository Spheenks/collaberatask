package com.todo.project.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.project.models.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
	


}
