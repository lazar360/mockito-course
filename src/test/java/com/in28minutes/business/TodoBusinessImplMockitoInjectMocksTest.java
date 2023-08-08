package com.in28minutes.business;

import TodoBusinessImpl.TodoBusinessImpl;
import com.in28minutes.data.api.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockitoInjectMocksTest {

    @Mock
    TodoService todoServiceMock;

    @InjectMocks
    TodoBusinessImpl todoBusinessImpl;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");

    /**
     * Mockito basic test : when & assertEquals
     */
    @Test
    public void test() {

        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

        List<String> filteredTodos = todoBusinessImpl
                 .retrieveTodosRelatedToSpring("Dummy");
         assertEquals(2, filteredTodos.size());
    }

    /**
     * Mockito basic test using BDD style : given, when, assertThat
     */
    @Test
    public void testRetrieveTodosRelatedToSpring_usingBDD() {

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

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

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

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

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

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

        // Given
        List<String> todos2 = Arrays.asList("Learn to Rock", "Learn Spring MVC", "Learn Spring", "Learn to dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos2);

        // When
        todoBusinessImpl
                .deleteTodosNotRelatedToSpring("Dummy");

        // Then
        then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());

        // assertThat(stringArgumentCaptor.getValue(), is("Learn to dance")); pour un seul argument
        assertThat(stringArgumentCaptor.getAllValues().size(), is(2)); // Pour tous les arguments
    }
}
