package wat.edu.pl.firstapp


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//odpowiada za konfigurację Room oraz udostępnia DAO, tworzy plik bazy danych na dysku
//definuje strukture tabel - na podstawie Todontity, udostępnia narzędzia do wykonywania operacji na bazie danych
@Database(entities = [TodoEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase (context: Context): AppDatabase{
            return INSTANCE?:synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }
}