package wat.edu.pl.firstapp

import androidx.room.Entity
import androidx.room.PrimaryKey

//Opisanie tabeli w bazie danych
@Entity(tableName = "todos")
class TodoEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val title: String,
    val createdAt: Long = System.currentTimeMillis()
)
