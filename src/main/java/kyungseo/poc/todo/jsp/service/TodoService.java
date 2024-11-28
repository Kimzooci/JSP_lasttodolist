
 package kyungseo.poc.todo.jsp.service;

 import java.time.LocalDate;
 import java.util.List;
 import java.util.Optional;
 import java.util.stream.Collectors;
 
 import org.modelmapper.ModelMapper;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.stereotype.Service;
 
 import kyungseo.poc.todo.common.exception.ResourceNotFoundException;
 import kyungseo.poc.todo.jsp.payload.TodoDto;
 import kyungseo.poc.todo.jsp.persistence.entity.Todo;
 import kyungseo.poc.todo.jsp.persistence.repository.TodoRepository;
 
 @Service
 public class TodoService {
 
	 private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	 private final TodoRepository todoRepository;
	 private final ModelMapper modelMapper;
 
	 // 생성자 주입
	 public TodoService(TodoRepository todoRepository, ModelMapper modelMapper) {
		 this.todoRepository = todoRepository;
		 this.modelMapper = modelMapper;
	 }
 
	 public List<TodoDto> getTodosByUser(String username) {
		 return todoRepository.findByUserName(username)
				 .stream()
				 .map(todo -> modelMapper.map(todo, TodoDto.class))
				 .collect(Collectors.toList());
	 }
 
	 public TodoDto getTodoById(long id) {
		 return todoRepository.findById(id)
				 .map(todo -> modelMapper.map(todo, TodoDto.class))
				 .orElseThrow(() -> new ResourceNotFoundException("할일을 찾지 못했습니다."));
	 }
 
	 public void addTodo(String username, String description, LocalDate targetDate, boolean isDone) {
		 todoRepository.save(new Todo(username, description, targetDate, isDone));
	 }
 
	 public void deleteTodo(long id) {
		 Optional<Todo> todo = todoRepository.findById(id);
		 todo.ifPresent(todoRepository::delete);
	 }
 
	 public Long deleteTodosByUser(String username) {
		 return todoRepository.deleteByUserName(username);
	 }
 
	 public void saveTodo(TodoDto todo) {
		 todoRepository.save(modelMapper.map(todo, Todo.class));
	 }
 }
 