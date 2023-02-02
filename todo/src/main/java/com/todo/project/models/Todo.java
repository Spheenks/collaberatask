package com.todo.project.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;



@Entity
@Builder
@Getter
@ToString
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String title;
	
	@Column
	private String description;
	
	@Column
	private String isDone;
	
	
	public Todo() {
	}
	
	public Todo( String title, String description, String isDone) {
		super();

		this.title = title;
		this.description = description;
		this.isDone = isDone;
	}
	public Todo(int id, String title, String description, String isDone) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.isDone = isDone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIsDone() {
		return isDone;
	}
	public void setDone(String isDone) {
		this.isDone = isDone;
	}
	
	
}
