package data.api;

import com.in28minutes.data.api.TodoService;

import java.util.Arrays;
import java.util.List;

public class TodoServiceStub implements TodoService {

    @Override
    public List<String> retrieveTodos(String user) {
        return Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
    }

    @Override
    public void deleteTodo(String todo) {

    }
}
