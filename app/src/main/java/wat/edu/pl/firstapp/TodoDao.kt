package wat.edu.pl.firstapp


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

//Dao zawiera metody do wykonywania operacji na bazie danych
@Dao
interface TodoDao {
    @Query("SELECT * FROM todos ORDER BY createdAt DESC")
    fun getAllTodos(): Flow<List<TodoEntity>>
    @Insert
    suspend fun insert(todo: TodoEntity)
    @Delete
    suspend fun delete(todo: TodoEntity)
}