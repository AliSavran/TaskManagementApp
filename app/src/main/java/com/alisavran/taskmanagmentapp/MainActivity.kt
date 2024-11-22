package com.alisavran.taskmanagmentapp

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alisavran.taskmanagmentapp.Adapter.TaskAdapter
import com.alisavran.taskmanagmentapp.Model.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter(emptyList(), { task ->
        },
            { task ->

            viewModel.delete(task)
        })
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        viewModel.allTasks.observe(this) { tasks ->
            tasks?.let {
                adapter.updateTasks(it)
            }
        }

        fab.setOnClickListener {
            showAddTaskDialog()

        }
    }

    private fun showAddTaskDialog() {

        val dialogLayout = layoutInflater.inflate(R.layout.dialog_add_task, null)
        val editTaskName = dialogLayout.findViewById<EditText>(R.id.editTaskName)

        AlertDialog.Builder(this)
            .setTitle("New Task Add")
            .setView(dialogLayout)
            .setPositiveButton("Add") { _, _ ->
                val taskName = editTaskName.text.toString()
                if (taskName.isNotEmpty()) {
                    val newTask = Task(name = taskName)
                    viewModel.insert(newTask)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}