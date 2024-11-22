package com.alisavran.taskmanagmentapp.RoomDb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alisavran.taskmanagmentapp.Model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}