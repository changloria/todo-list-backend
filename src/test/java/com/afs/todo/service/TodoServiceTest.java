package com.afs.todo.service;

import com.afs.todo.entity.TodoItem;
import com.afs.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class TodoServiceTest {

    @Mock
    TodoRepository todoRepository;

    @InjectMocks
    TodoService todoService;

    @Test
    void should_return_all_todo_items_when_perform_get_given_todo_items() {
        //given
        List<TodoItem> todoItemList = new ArrayList<>(Collections.singletonList(new TodoItem( "Take Physics Exam", false)));
        given(todoRepository.findAll())
                .willReturn(todoItemList);
        //when
        List<TodoItem> actual = todoService.findAll();
        //then
        assertEquals(todoItemList, actual);
        assertEquals(todoItemList.get(0).getText(), actual.get(0).getText());
        assertEquals(todoItemList.get(0).getDone(), actual.get(0).getDone());
    }

    @Test
    void should_return_a_todo_item_when_perform_get_by_id_given_todo_items() {
        //given
        TodoItem todoItem = new TodoItem("Join Christmas Party", false);

        given(todoRepository.findById(todoItem.getId()))
                .willReturn(java.util.Optional.of(todoItem));
        //when
        TodoItem actual = todoService.findById(todoItem.getId());
        //then
        assertEquals(todoItem, actual);
        assertEquals(todoItem.getText(), actual.getText());
    }

    @Test
    void should_update_a_todo_item_when_perform_put_given_todo_item() {
        //given
        TodoItem todoItem = new TodoItem("Happy Day", false);
        TodoItem updatedTodoItem = new TodoItem("Happy Day 1", false);
        given(todoRepository.findById(any()))
                .willReturn(java.util.Optional.of(todoItem));
        todoItem.setText(updatedTodoItem.getText());
        todoItem.setDone(updatedTodoItem.getDone());
        given(todoRepository.save(any(TodoItem.class)))
                .willReturn(todoItem);

        //when
        TodoItem actual = todoService.update(todoItem.getId(), updatedTodoItem);

        //then
        verify(todoRepository).save(todoItem);
        assertEquals(todoItem, actual);
        assertEquals(todoItem.getText(), actual.getText());
    }

    @Test
    void should_add_a_todo_item_when_perform_post_given_todo_item() {
        //given
        TodoItem newTodoItem = new TodoItem("Sad Day", false);
        given(todoRepository.insert(newTodoItem))
                .willReturn(newTodoItem);

        //when
        TodoItem actual = todoService.create(newTodoItem);

        //then
        verify(todoRepository).insert(newTodoItem);
        assertEquals(newTodoItem, actual);
    }

    @Test
    void should_delete_todo_item_when_perform_delete_given_todo_item_and_id() {
        //given
        TodoItem todoItem = new TodoItem("Happy",false);
        willDoNothing().given(todoRepository).deleteById(todoItem.getId());
        //when
        todoService.remove(todoItem.getId());
        //then
        verify(todoRepository).deleteById(todoItem.getId());
        assertEquals(0, todoRepository.findAll().size());
    }
    
}
