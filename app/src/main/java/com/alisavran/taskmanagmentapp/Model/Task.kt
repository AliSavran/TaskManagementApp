package com.alisavran.taskmanagmentapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val isCompleted: Boolean = false
)