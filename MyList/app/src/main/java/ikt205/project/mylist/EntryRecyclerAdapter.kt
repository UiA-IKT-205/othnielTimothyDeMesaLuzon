package ikt205.project.mylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ikt205.project.mylist.data.TodoList
import ikt205.project.mylist.databinding.EntryLayoutBinding

class EntryRecyclerAdapter(private var todoList: TodoList)
    : RecyclerView.Adapter<EntryRecyclerAdapter.ViewHolder>() {

        class ViewHolder(val binding: EntryLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(entry: String) {
                binding.entryBox.text = entry
            }
        }

    override fun getItemCount(): Int = todoList.entries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = todoList.entries[position]
        holder.bind(entry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(EntryLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    public fun updateEntryRecycler(newEntry:String) {
        todoList.entries.add(newEntry)
        notifyDataSetChanged()
    }
}