package com.alisavran.taskmanagmentapp.Repository

import androidx.lifecycle.LiveData
import com.alisavran.taskmanagmentapp.Model.Task
import com.alisavran.taskmanagmentapp.RoomDb.TaskDao

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task) = taskDao.insertTask(task)
    suspend fun update(task: Task) = taskDao.updateTask(task)
    suspend fun delete(task: Task) = taskDao.deleteTask(task)
}