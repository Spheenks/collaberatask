package com.todo.project.test;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.project.controller.TodoController;
import com.todo.project.models.Todo;
import com.todo.project.repository.TodoRepository;
import com.todo.project.service.TodoService;


@WebMvcTest(TodoController.class)
public class TodoControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	TodoController todoController;

	@MockBean
	TodoRepository todoRepository;

	@MockBean
	TodoService todoService;

	Todo RECORD_1 = new Todo(7, "b", "Go to the AWS!", "No");
	Todo RECORD_2 = new Todo(2, "Toothbrush", "It smells dude!", "No");
	Todo RECORD_3 = new Todo(3, "Go to work", "Need money to pay rent!", "No");

	@Test
	public void getAllRecords_success() throws Exception {
		List<Todo> todoList = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
		Mockito.when(todoRepository.findAll()).thenReturn(todoList);

		assertNotNull(todoList);
		assertEquals(3, todoList.size());

		mockMvc.perform(MockMvcRequestBuilders.get("/todolist").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print()).andDo(print());
	}

	@Test
	public void createRecord_success() throws Exception {

		Todo addtodo = new Todo("todo1" , "Another todo", "yes");

		

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/addtodo")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(addtodo));


		mockMvc.perform(mockRequest).andExpect(status().isOk());
		
		doReturn(addtodo).when(todoService).saveTodo(any());

	}
	
	@Test
	public void updateTodo_success() throws Exception {
	    Todo todo = new Todo(9, "g", "Go to the AWS!", "No");

	    Mockito.when(todoRepository.findById(RECORD_1.getId())).thenReturn(Optional.of(RECORD_1));
	    Mockito.when(todoRepository.save(todo)).thenReturn(todo);

	    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/updatetodo/7")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content("{\"title\":\"ggg up!\",\"description\":\"go to work\",\"isDone\":\"No\"}");

	    mockMvc.perform(mockRequest)
	            .andExpect(status().isOk());
	
	}
	
	@Test
	public void DeleteTodoById_success() throws Exception {
	    Mockito.when(todoRepository.findById(RECORD_2.getId())).thenReturn(Optional.of(RECORD_2));

	    mockMvc.perform(MockMvcRequestBuilders
	            .delete("/deletetodo/2")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}

}
