package ikt205.project.mylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ikt205.project.mylist.data.TodoList
import ikt205.project.mylist.databinding.ActivityMainBinding

const val EXTRA_INFO = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listRecycler.layoutManager = LinearLayoutManager(this)
        binding.listRecycler.adapter = ListRecyclerAdapter(emptyList<TodoList>(), this::onListClicked)

        ListManager.instance.onList = {
            (binding.listRecycler.adapter as ListRecyclerAdapter).updateRecycler(it)
        }

        ListManager.instance.loadList()

        binding.newListButton.setOnClickListener { newList() }
    }

    private fun newList() {
        val defaultTitle = getString(R.string.default_title)
        val defaultEntries = mutableListOf<String>("")

        val list = TodoList(defaultTitle, defaultEntries)

        ListManager.instance.addList(list)
    }

    private fun onListClicked(todoList: TodoList): Unit {

        val intent = Intent(this, ListEditActivity::class.java).apply {
            putExtra(EXTRA_INFO, todoList)
        }

        startActivity(intent)
    }
}