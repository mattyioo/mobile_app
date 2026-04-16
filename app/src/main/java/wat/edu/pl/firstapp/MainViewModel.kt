package wat.edu.pl.firstapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository = Repository()) : ViewModel(){
    var counter by mutableStateOf(0)
    var name by mutableStateOf("")
    var newItemText by mutableStateOf("")
    var itemsList = mutableStateListOf<String>()
    fun incrementCounter(){
        counter++
    }
    fun setname(value : String){
        name = value
    }
    fun setnewItemText(value : String){
        newItemText = value
    }
    fun additem(){
        newItemText.trim()
        if(newItemText.isNotEmpty()) {
            itemsList.add(newItemText)
            newItemText = ""
        }
    }
    fun removeitem(item : String){
        itemsList.remove(item)
    }

    //Retrofit zadanie 4
    var loading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    var data by mutableStateOf<List<PostDto>>(emptyList())
        private set

    fun refresh() {
        viewModelScope.launch {
            loading = true
            error = null
            try {
                data = repository.getData()
            } catch (e: Exception) {
                error = e.message ?: "Unknown error"
            } finally {
                loading = false
            }
        }
    }
}