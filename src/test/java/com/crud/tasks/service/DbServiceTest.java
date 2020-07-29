package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    public void getAllTasksTest() {
        //When
        dbService.getAllTasks();

        //Then
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    public void getTaskTest() {
        //Given
        Task task = new Task(100L, "title", "desc");

        //When
        dbService.getTask(task.getId());

        //Then
        verify(taskRepository, times(1)).findById(100L);
    }

    @Test
    public void saveTaskTest() {
        //Given
        Task task = new Task(100L, "title", "desc");

        //When
        dbService.saveTask(task);

        //Then
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void deleteTaskTest() {
        //Given
        Task task = new Task(100L, "title", "desc");

        //When
        dbService.deleteTask(task.getId());

        //Then
        verify(taskRepository, times(1)).deleteById(100L);
    }
}