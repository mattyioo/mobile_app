package wat.edu.pl.firstapp

import kotlinx.coroutines.flow.Flow
//Repozytorium oddziela logikę dostępu do bazy od ViewModelu i UI.
class TodoRepository(
    private val todoDao: TodoDao
) {
    val todos : Flow<List<TodoEntity>> = todoDao.getAllTodos()
    suspend fun addTodo(title: String){
        val todo = TodoEntity(
            title=title,
            createdAt = System.currentTimeMillis()
        )
        todoDao.insert(todo)
    }
    suspend fun removeTodo(todo: TodoEntity){
        todoDao.delete(todo)
    }
}
