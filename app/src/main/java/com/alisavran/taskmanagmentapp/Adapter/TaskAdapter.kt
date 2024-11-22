package com.alisavran.taskmanagmentapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alisavran.taskmanagmentapp.Model.Task
import com.alisavran.taskmanagmentapp.R

class TaskAdapter(

    private var tasks: List<Task>,
    private val onTaskClicked: (Task) -> Unit,
    private val onTaskDeleted: (Task)-> Unit

) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val taskName: TextView = view.findViewById(R.id.taskName)
        val taskCheckBox: CheckBox = view.findViewById(R.id.taskCheckBox)
        val deleteButton: Button = view.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        val task = tasks[position]
        holder.taskName.text = task.name
        holder.taskCheckBox.isChecked = task.isCompleted
        holder.itemView.setOnClickListener { onTaskClicked(task) }
        holder.deleteButton.setOnClickListener{ onTaskDeleted(task)}
    }

    override fun getItemCount() = tasks.size

    fun updateTasks(newTasks: List<Task>) {

        tasks = newTasks
        notifyDataSetChanged()
    }
}