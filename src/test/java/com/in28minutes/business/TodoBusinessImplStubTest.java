package com.in28minutes.business;
import TodoBusinessImpl.TodoBusinessImpl;
import com.in28minutes.data.api.TodoService;
import data.api.TodoServiceStub;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TodoBusinessImplStubTest {

    @Test
    public void test() {
        TodoService todoServiceStub = new TodoServiceStub();
        TodoBusinessImpl todoBusinessImpl =
                new TodoBusinessImpl(todoServiceStub);
         List<String> filteredTodos = todoBusinessImpl
                 .retrieveTodosRelatedToSpring("Dummy");
         assertEquals(2, filteredTodos.size());
    }
}
