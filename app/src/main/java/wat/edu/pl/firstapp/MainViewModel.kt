package wat.edu.pl.firstapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){
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

}