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
            Licz()
//StatefulTextField()

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Witaj $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)


@Composable
fun BasicTextButton() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        FilledTonalButton( onClick = { Fun1(context) })
        {
            Text(text = "Kliknij mnie") // Button content (text only)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun BasicTextButtonPreview() {
    BasicTextButton()
}


private fun Fun1(context: Context){
    Toast.makeText(context, "Przycisk zostal klikniety.", Toast.LENGTH_SHORT).show()
}

@Composable
fun Licz() {
    val context = LocalContext.current
    var number by remember { mutableStateOf(0) }
    var text by remember { mutableStateOf("") }
    var text1 by remember { mutableStateOf("") }
    val list=remember { mutableStateListOf<String>() }
    val mod=Modifier.padding(10.dp)
    Column(
        modifier = Modifier.padding(24.dp).fillMaxSize(),
//verticalArrangement = Arrangement.SpaceEvenly,
//horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Witaj w JetpackCompose!", modifier = mod
        )
        FilledTonalButton( onClick = { Fun1(context) }, modifier = mod)
        {
            Text(text = "Kliknij mnie", modifier = mod) // Button content (text only)
        }
        Row(modifier = Modifier.width(180.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilledTonalButton(onClick = { number++ }, modifier = mod)
            {
                Text(text = "Zwieksz") // Button content (text only)
            }
            Text(text = "Licznik: $number", modifier = mod)
        }
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
