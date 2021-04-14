package ikt205.project.mylist

import ikt205.project.mylist.data.TodoList

class ListManager {

    private lateinit var testCollection: MutableList<TodoList>

    var onList: ((List<TodoList>) -> Unit)? = null
    var onListUpdate:((todoList:TodoList) -> Unit)? = null

    fun loadList() {
        testCollection = mutableListOf(
            TodoList("Test", mutableListOf("val1", "val2", "val3"))
        )

        onList?.invoke(testCollection)
    }

    fun addList(todoList: TodoList){
        testCollection.add(todoList)
        onList?.invoke(testCollection)
    }

    companion object{
        val instance = ListManager()
    }
    
}