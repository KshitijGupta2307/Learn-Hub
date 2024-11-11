import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete

// Define Task data class
data class Task(val text: String, var isCompleted: Boolean)

// ViewModel for managing the To-Do list
class ToDoViewModel : ViewModel() {
    val tasks = mutableStateListOf<Task>()

    fun addTask(task: Task) {
        tasks.add(task)
    }

    fun removeTask(task: Task) {
        tasks.remove(task)
    }

    fun updateTask(index: Int, updatedTask: Task) {
        tasks[index] = updatedTask
    }
}

// Composable for a single task item
@Composable
fun TaskItem(task: Task, onTaskCheckedChange: (Boolean) -> Unit, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = onTaskCheckedChange
        )

        Text(
            text = task.text,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            fontSize = 18.sp
        )

        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Task",
                tint = Color.Red
            )
        }
    }
}

// Main To-Do Composable that observes and interacts with the ViewModel
@Composable
fun ToDO(viewModel: ToDoViewModel = viewModel()) {
    var taskText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "To-Do List",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input field for new task
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            BasicTextField(
                value = taskText,
                onValueChange = { taskText = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .background(Color.LightGray, shape = MaterialTheme.shapes.small)
                    .padding(8.dp),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(fontSize = 18.sp)
            )

            Button(
                onClick = {
                    if (taskText.isNotBlank()) {
                        viewModel.addTask(Task(taskText, false)) // Add new task via ViewModel
                        taskText = "" // Reset the input field
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // List of tasks
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(viewModel.tasks) { task ->
                TaskItem(
                    task = task,
                    onTaskCheckedChange = { isChecked ->
                        val updatedTask = task.copy(isCompleted = isChecked)
                        val index = viewModel.tasks.indexOf(task)
                        viewModel.updateTask(index, updatedTask)
                    },
                    onDelete = {
                        viewModel.removeTask(task)
                    }
                )
            }
        }
    }
}

