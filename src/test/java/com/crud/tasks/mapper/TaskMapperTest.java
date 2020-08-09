package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class TaskMapperTest {

    @Autowired
    TaskMapper taskMapper;

    @Test
    void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Task_Name", "Description_task");

        //When
        Task mappingResult = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(Optional.of(1L), Optional.ofNullable(mappingResult.getId()));
        assertEquals("Task_Name", mappingResult.getTitle());
        assertEquals("Description_task", mappingResult.getContent());
    }

    @Test
    void mapToTaskDto() {
        //Given
        Task task = new Task(2L, "TaskDto_Name", "Description_taskDto");

        //When
        TaskDto mappingResult = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(Optional.of(2L), Optional.ofNullable(mappingResult.getId()));
        assertEquals("TaskDto_Name", mappingResult.getTitle());
        assertEquals("Description_taskDto", mappingResult.getContent());
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        Task taskList = new Task(3L, "List_Name", "Description1");
        List<Task> list = new ArrayList<>();
        list.add(taskList);

        //When
        List<TaskDto> mappingResult = taskMapper.mapToTaskDtoList(list);

        //Then
        assertEquals(Optional.of(3L), Optional.ofNullable(mappingResult.get(0).getId()));
        assertEquals("List_Name", mappingResult.get(0).getTitle());
        assertEquals("Description1", mappingResult.get(0).getContent());
    }
}