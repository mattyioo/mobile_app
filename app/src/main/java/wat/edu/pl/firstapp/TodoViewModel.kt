package wat.edu.pl.firstapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

//ViewModel przechowuje stan UI i wywołuje metody repozytorium.
class TodoViewModel(
    private val repository: TodoRepository
) : ViewModel(){
    val todos: Flow<List<TodoEntity>> = repository.todos
    fun add(title: String){
        viewModelScope.launch {
            repository.addTodo(title)
        }
    }
    fun delete(todo: TodoEntity){
        viewModelScope.launch{
            repository.removeTodo(todo)
        }
    }
}
