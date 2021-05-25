package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskMapperTest {

    @Autowired
    TaskMapper taskMapper;

    @Test
    void shouldMapToTaskDto(){
        //Given
        Task task1 = new Task(1L, "name1", "desc1");
        //When
        TaskDto taskDto1 = taskMapper.mapToTaskDto(task1);

        //Then
        assertEquals(task1.getTitle(), taskDto1.getTitle());
        assertEquals(task1.getContent(), taskDto1.getContent());
        assertEquals(task1.getId(), taskDto1.getId());
    }

    @Test
    void shouldMapToTask(){
        //Given
        TaskDto taskDto1 = new TaskDto(1L, "name1", "desc1");
        //When
        Task task1 = taskMapper.mapToTask(taskDto1);

        //Then
        assertEquals(task1.getTitle(), taskDto1.getTitle());
        assertEquals(task1.getContent(), taskDto1.getContent());
        assertEquals(task1.getId(), taskDto1.getId());
    }


    @Test
    void shouldMapToTaskDtoList(){
        //Given
        List<Task> tasks = new ArrayList<>();

        Task task1 = new Task(1L, "name1", "desc1");
        Task task2 = new Task(2L, "name2", "desc2");
        tasks.add(task1);
        tasks.add(task2);
        //When
        List<TaskDto> tasksDto = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertEquals(tasksDto.size(), tasks.size());
        assertEquals(tasksDto.get(0).getTitle(), tasks.get(0).getTitle());
        assertEquals(tasksDto.get(0).getContent(), tasks.get(0).getContent());
    }

}
