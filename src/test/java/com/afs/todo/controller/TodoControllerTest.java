package com.afs.todo.controller;

import com.afs.todo.entity.TodoItem;
import com.afs.todo.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    MockMvc mockMvc;

    @AfterEach
    void cleanRepository() {
        todoRepository.deleteAll();
    }

    @Test
    void should_return_all_todo_items_when_perform_get_given_todo_items() throws Exception {
        //given
        TodoItem todoItem1 = new TodoItem( "Take English Exam", false);
        TodoItem todoItem2 = new TodoItem("Take Chinese Exam", true);
        todoRepository.insert(todoItem1);
        todoRepository.insert(todoItem2);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].text").value("Take English Exam"))
                .andExpect(jsonPath("$[0].done").value(false))
                .andExpect(jsonPath("$[1].id").isString())
                .andExpect(jsonPath("$[1].text").value("Take Chinese Exam"))
                .andExpect(jsonPath("$[1].done").value(true));
    }

    @Test
    void should_return_one_todo_item_when_perform_get_by_id_given_todo_items() throws Exception {
        //given
        TodoItem todoItem = new TodoItem("Join Christmas Party", false);
        todoRepository.insert(todoItem);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/{id}", todoItem.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(todoItem.getId()))
                .andExpect(jsonPath("$.text").value("Join Christmas Party"))
                .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    void should_add_a_todo_item_when_perform_post_given_todo_item() throws Exception {
        //given
        String todoItem = "{\n" +
                "        \"text\": \"Swimming with sister\",\n" +
                "        \"done\": false\n" +
                "    }";

        //when
        //then
        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(todoItem))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value("Swimming with sister"))
                .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    void should_return_updated_todo_item_when_perform_put_given_todo_item() throws Exception {
        //given
        TodoItem todoItem1 = new TodoItem("Join Housewarming Party", false);
        TodoItem todoItem2 = new TodoItem("Play tennis", true);
        TodoItem todoItem3 = new TodoItem("Eat turkey", false);

        todoRepository.insert(todoItem1);
        todoRepository.insert(todoItem2);
        todoRepository.insert(todoItem3);

        String employee = "{\n" +
                "        \"text\": \"Eat turkey\",\n" +
                "        \"done\": true\n" +
                "    }";

        //when
        //then
        mockMvc.perform(put("/todos/{id}", todoItem3.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employee))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("Eat turkey"))
                .andExpect(jsonPath("$.done").value(true));
    }

    @Test
    void should_delete_todo_item_when_perform_delete_given_todo_item_and_id() throws Exception {
        //given
        TodoItem todoItem1 = new TodoItem("Join Housewarming Party", false);
        TodoItem todoItem2 = new TodoItem("Play tennis", true);

        todoRepository.insert(todoItem1);
        todoRepository.insert(todoItem2);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/todos/{id}", todoItem1.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}


