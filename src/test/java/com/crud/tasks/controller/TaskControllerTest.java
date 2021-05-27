package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService dbService;

    @Test
    void shouldFetchTaskList() throws Exception {

        //Given
        Task task1 = new Task(1L, "test1", "desc1");
        Task task2 = new Task(2L, "test2", "desc2");
        TaskDto task1Dto = new TaskDto(1L, "test1", "desc1");
        TaskDto task2Dto = new TaskDto(2L, "test2", "desc2");
        List<Task> tasks = Arrays.asList(task1, task2);
        List<TaskDto> taskDtos = Arrays.asList(task1Dto, task2Dto);

        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);
        when(dbService.getAllTasks()).thenReturn(tasks);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("test1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("desc1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title", Matchers.is("test2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].content", Matchers.is("desc2")));
    }

    @Test
    void shouldFetchTask() throws Exception {
        //Given
        Task task1 = new Task(1L, "test1", "desc1");
        TaskDto task1Dto = new TaskDto(1L, "test1", "desc1");
        when(taskMapper.mapToTaskDto(task1)).thenReturn(task1Dto);
        when(dbService.getTask(1L)).thenReturn(Optional.of(task1));
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/task/getTask/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("test1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("desc1")));
    }

    @Test
    void shouldUpdateTask() throws Exception {
        //Given
        Task task1 = new Task(1L, "test1", "desc1");
        TaskDto task1Dto = new TaskDto(1L, "test1", "desc1");
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(task1Dto);
        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task1);
        when(dbService.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task1);

        Gson gson = new Gson();
        String jsContent = gson.toJson(task1Dto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/task/updateTask").contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("test1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("desc1")));
    }

    @Test
    void deleteTaskTest() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateTask() throws Exception {
        //Given
        Task task1 = new Task(1L, "test1", "desc1");
        TaskDto task1Dto = new TaskDto(1L, "test1", "desc1");
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(task1Dto);
        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task1);
        when(dbService.saveTask(task1)).thenReturn(task1);

        Gson gson = new Gson();
        String jsContent = gson.toJson(task1Dto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/task/createTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }







}





