package com.in28minutes.business;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Test
    public void letsMockListSizeMethod() {
        List listMock = mock(List.class);
        // Argument matcher
        when(listMock.size()).thenReturn(2).thenReturn(3);
        assertEquals(2, listMock.size());
        assertEquals(3, listMock.size());
    }
    @Test
    public void letsMockListGetMethod_usingBDD() {

        // Given
        List<String> listMock = mock(List.class);
        // Argument matcher
        given(listMock.get(anyInt())).willReturn("in28Minutes");

        // When
        String firstElement = listMock.get(0);

        // Then
        assertThat(firstElement, is("in28Minutes"));
    }

    @Test(expected = RuntimeException.class)
    public void letsMockListGetMethod_throwAnException() {

        List listMock = mock(List.class);
        // Argument matcher
        when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something"));
        listMock.get(0);
    }

}
