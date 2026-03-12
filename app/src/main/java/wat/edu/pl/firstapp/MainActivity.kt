package wat.edu.pl.firstapp

import android.R.attr.label
import android.R.attr.value
import android.R.attr.width
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import wat.edu.pl.firstapp.ui.theme.FirstAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            licznik()


        }
    }
}



private fun Fun1(context: Context){
    Toast.makeText(context, "Przycisk zostal klikniety.", Toast.LENGTH_SHORT).show()
}

@Composable
fun licznik() {
    val context = LocalContext.current
    var number by rememberSaveable() { mutableStateOf(0) }
    var text by rememberSaveable() { mutableStateOf("") }
    var text1 by rememberSaveable() { mutableStateOf("") }
    val list=rememberSaveable() { mutableStateListOf<String>() }
    val mod=Modifier.padding(5.dp)
    Column(
        modifier = Modifier.padding(24.dp).fillMaxSize().paddingFromBaseline(top = 50.dp)
    ) {
        Text(
            text = "Witaj w JetpackCompose!", modifier = mod
        )
        FilledTonalButton( onClick = { Fun1(context) }, modifier = mod)
        {
            Text(text = "Kliknij mnie", modifier = mod) // Button content (text only)
        }

            FilledTonalButton(onClick = { number++ }, modifier = mod)
            {
                Text(text = "Zwieksz") // Button content (text only)
            }
            Text(text = "Licznik: $number", modifier = mod)

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Podaj imie") },
            modifier = mod
        )
        if (text.isEmpty()) {
            Text("Witaj!", modifier = mod)
        } else {
            Text("Witaj, $text", modifier = mod)
        }
        Row(modifier = Modifier.width(300.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            TextField(
                value = text1,
                onValueChange = { text1 = it },
                label = { Text("Dodaj element") },
                modifier = mod.width(180.dp)
            )

            FilledTonalButton(onClick = { list.add(text1)
            }, modifier = mod)
            {
                Text(text = "Dodaj", modifier = mod) // Button content (text only)
            }

        }
        LazyColumn(modifier = mod){
            itemsIndexed(items = list) {index, flower ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = flower, modifier = mod)
                    FilledTonalButton(onClick ={list.removeAt(index)} , modifier = mod,) {
                        Text("Usuń")
                    }
                }
            }
        }
    }
}
