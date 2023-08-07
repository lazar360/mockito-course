package com.in28minutes.business;

import TodoBusinessImpl.TodoBusinessImpl;
import com.in28minutes.data.api.TodoService;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TodoBusinessImplMockTest {

    @Test
    public void test() {
        TodoService todoServiceMock = mock(TodoService.class);

        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");

        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

        TodoBusinessImpl todoBusinessImpl =
                new TodoBusinessImpl(todoServiceMock);

        List<String> filteredTodos = todoBusinessImpl
                 .retrieveTodosRelatedToSpring("Dummy");
         assertEquals(2, filteredTodos.size());
    }
}
