package com.in28minutes.business;

import TodoBusinessImpl.TodoBusinessImpl;
import com.in28minutes.data.api.TodoService;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

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

    @Test
    public void testRetrieveTodosRelatedToSpring_usingBDD() {

        // Given
        TodoService todoServiceMock = mock(TodoService.class);

        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        TodoBusinessImpl todoBusinessImpl =
                new TodoBusinessImpl(todoServiceMock);

        // When
        List<String> filteredTodos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("Dummy");

        // Then
        assertThat(filteredTodos.size(), is(2));
    }
    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDD() {

        // Given
        TodoService todoServiceMock = mock(TodoService.class);

        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        TodoBusinessImpl todoBusinessImpl =
                new TodoBusinessImpl(todoServiceMock);

        // When
        todoBusinessImpl
                .deleteTodosNotRelatedToSpring("Dummy");

        // Then
        verify(todoServiceMock).deleteTodo("Learn to dance");

        verify(todoServiceMock, times(1)).deleteTodo("Learn to dance");

        verify(todoServiceMock, never()).deleteTodo("Learn Spring");
    }
}
