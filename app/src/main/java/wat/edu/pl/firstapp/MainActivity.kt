package wat.edu.pl.firstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wat.edu.pl.firstapp.ui.theme.FirstappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Jetpack Compose",
//                        modifier = Modifier.padding(innerPadding)
//                    )

                    Hello_Jetpack()
                    KliknijMnie {  }
                    Licznik()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Witaj w $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstappTheme {
        Greeting("Android")
    }
}

@Composable
fun Hello_Jetpack(){
    Text(text = "Witaj w Jetpack Compose", modifier = Modifier.padding(16.dp))
}

@Composable
fun KliknijMnie(onClick: () -> Unit) {
    var text by remember { mutableStateOf("") }
    Column(Modifier.fillMaxWidth().absolutePadding(20.dp, 200.dp, 20.dp, 0.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text)
        Button(onClick = { text = "Przycisk został kliknięty" },
            modifier = Modifier.height(100.dp).width(300.dp)
        ) {
            Text("Kliknij mnie", color = Color.White, fontSize = 30.sp)
        }
    }
}

@Composable
fun Licznik() {
    var licznik by remember { mutableStateOf(0) }
    Column(
        Modifier.fillMaxWidth().absolutePadding(20.dp, 50.dp, 20.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { licznik++ }, modifier = Modifier.height(50.dp).width(150.dp)) {
            Text("Zwiększ")
        }
        Text("Licznik:\t" + licznik.toString())
    }
}

@Composable
fun PoleTekstowe(){
    var text by remember {mutableStateOf("")}
    TextField(
            label = { Text("Podaj Imie") },
            value = rememberTextFieldState(initialText = "Hello"),
            onValueChange = { }
        )

}