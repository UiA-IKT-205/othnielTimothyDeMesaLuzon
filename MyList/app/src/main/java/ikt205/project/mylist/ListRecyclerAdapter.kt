package ikt205.project.mylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ikt205.project.mylist.data.TodoList
import ikt205.project.mylist.databinding.ListLayoutBinding

class ListRecyclerAdapter(private var todoLists: List<TodoList>,
                          private val onListClicked:(TodoList)-> Unit)
    : RecyclerView.Adapter<ListRecyclerAdapter.ViewHolder>() {

    class ViewHolder(val binding: ListLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todoList: TodoList, onListClicked: (TodoList) -> Unit) {
            binding.listTitle.text = todoList.title
            binding.cardView.setOnClickListener { onListClicked(todoList) }
        }
    }

    override fun getItemCount(): Int = todoLists.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoList = todoLists[position]
        holder.bind(todoList, onListClicked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    public fun updateRecycler(newLists:List<TodoList>) {
        todoLists = newLists
        notifyDataSetChanged()
    }
}