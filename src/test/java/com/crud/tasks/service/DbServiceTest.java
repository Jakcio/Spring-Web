package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DbServiceTest {

    @Autowired
    DbService dbService;

    @Test
    void shouldReturnAllTasks() {
        //Given
        Task task1 = new Task(1L, "name1", "desc1");
        Task task2 = new Task(2L, "name2", "desc2");
        long id1 = dbService.saveTask(task1).getId();
        long id2 = dbService.saveTask(task2).getId();

        //When
        List<Task> tasks = dbService.getAllTasks();

        //Then
        assertEquals(2, tasks.size());

        //CleanUp
        dbService.deleteTaskByID(id1);
        dbService.deleteTaskByID(id2);
    }

    @Test
    void shouldReturnTaskById() {
        //Given
        Task task1 = new Task(1L, "name1", "desc1");
        long id1 = dbService.saveTask(task1).getId();

        //When
        Task taskFromDb = dbService.getTask(id1).get();
        long taskIdFromDatabase = taskFromDb.getId();

        //Then
        assertEquals(taskIdFromDatabase, taskFromDb.getId());
        assertEquals(task1.getTitle(), taskFromDb.getTitle());
        assertEquals(task1.getContent(), taskFromDb.getContent());

        //CleanUp
        dbService.deleteTaskByID(id1);
    }
}
