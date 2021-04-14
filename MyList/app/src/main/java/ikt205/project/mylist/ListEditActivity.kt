package ikt205.project.mylist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ikt205.project.mylist.data.TodoList
import ikt205.project.mylist.databinding.ActivityListEditBinding

class ListEditActivity : AppCompatActivity(){

    private lateinit var binding: ActivityListEditBinding
    private lateinit var todoList: TodoList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedList = intent.getParcelableExtra<TodoList>(EXTRA_INFO)
        if (receivedList != null) {
            todoList = receivedList
        } else {
            finish()
        }
        binding.listHeader.text = todoList.title
        binding.entryRecycler.adapter = EntryRecyclerAdapter(todoList)
        binding.newEntryButton.setOnClickListener { newEntry() }
    }

    fun newEntry() {
        val entry = binding.newEntryEditText.text.toString()
        EntryRecyclerAdapter(todoList).updateEntryRecycler(entry)
    }
}