package com.in28minutes.business;

import TodoBusinessImpl.TodoBusinessImpl;
import com.in28minutes.data.api.TodoService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class TodoBusinessImplMockTest {

    /**
     * Mockito basic test : when & assertEquals
     */
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

    /**
     * Mockito basic test using BDD style : given, when, assertThat
     */
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

    /**
     * Mockito verify method : given, when, verify : verify call on method
     */
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

    /**
     * Mockito should method
     */
    @Test
    public void testDeleteTodosNotRelatedToSpring2_usingBDD() {

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
        then(todoServiceMock).should().deleteTodo("Learn to dance");

        then(todoServiceMock).should(never()).deleteTodo("Learn Spring");

        then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");
    }

    /**
     * stringArgumentCaptor
     */
    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCapture() {

        // Declare Argument Captor
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        // Define Argument captor on specific method call

        // Capture the argument


        // Given
        TodoService todoServiceMock = mock(TodoService.class);

        List<String> todos = Arrays.asList("Learn to Rock", "Learn Spring MVC", "Learn Spring", "Learn to dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        TodoBusinessImpl todoBusinessImpl =
                new TodoBusinessImpl(todoServiceMock);

        // When
        todoBusinessImpl
                .deleteTodosNotRelatedToSpring("Dummy");

        // Then
        then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());

        // assertThat(stringArgumentCaptor.getValue(), is("Learn to dance")); pour un seul argument
        assertThat(stringArgumentCaptor.getAllValues().size(), is(2)); // Pour tous les arguments
    }
}
