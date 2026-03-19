package wat.edu.pl.firstapp

import android.R.attr.label
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            HomeScreen()
            ListScreen()
        }
    }
}



private fun popUp(context: Context){
    Toast.makeText(context, "Przycisk zostal klikniety.", Toast.LENGTH_SHORT).show()
}

@Composable
fun ScreenUI() {
    val vm: MainViewModel = viewModel()
    val mod = Modifier.padding(5.dp)
    Column(
        modifier = Modifier.padding(25.dp).fillMaxSize().paddingFromBaseline(top = 50.dp)
    ) {
        Text(
            "Laboratorium 2.1 - ViewModel i Stan",
            fontSize = 18.sp,
            modifier = mod,
            fontWeight = FontWeight.Medium
        )

        Text(
            "Licznik (stan w ViewModel)",
            fontSize = 15.sp,
            modifier = mod,
            fontWeight = FontWeight.Medium
        )
        Row(
            modifier = Modifier.width(300.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilledTonalButton(onClick = { vm.incrementCounter() }, modifier = mod) {
                Text("Zwiększ", modifier = mod)
            }
            Text("Licznik: ${vm.counter}", modifier = mod)
        }

        Text(
            "Pole Tekstowe (stan w ViewModel)",
            fontSize = 15.sp,
            modifier = mod,
            fontWeight = FontWeight.Medium
        )
        OutlinedTextField(
            value = vm.name,
            onValueChange = { vm.setname(it) },
            label = { Text("Podaj imię") },
            modifier = mod,
        )
        if (vm.name.isEmpty()) {
            Text("Witaj!", modifier = mod)
        } else {
            Text("Witaj, ${vm.name}!", modifier = mod)
        }


        Text(
            "Lista (stan w ViewModel)",
            fontSize = 15.sp,
            modifier = mod,
            fontWeight = FontWeight.Medium
        )
        Row(
            modifier = Modifier.width(300.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = vm.newItemText,
                onValueChange = { vm.setnewItemText(it) },
                label = { Text("Dodaj element") },
                modifier = mod.width(180.dp)
            )

            FilledTonalButton(onClick = {
                vm.additem()
            }, modifier = mod)
            {
                Text(text = "Dodaj", modifier = mod) // Button content (text only)
            }

        }
        LazyColumn(modifier = mod) {
            itemsIndexed(items = vm.itemsList) { index, flower ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = flower, modifier = mod)
                    FilledTonalButton(onClick = { vm.removeitem(flower) }, modifier = mod) {
                        Text("Usuń")
                    }
                }
            }
        }
    }
}

@Composable
fun MainApp() {
    val context = LocalContext.current
    var number by rememberSaveable { mutableIntStateOf(0) }
    var text by rememberSaveable { mutableStateOf("") }
    var text1 by rememberSaveable { mutableStateOf("") }
    val list = rememberSaveable { mutableStateListOf<String>() }
    val mod = Modifier.padding(5.dp)
    Column(
        modifier = Modifier.padding(24.dp).fillMaxSize().paddingFromBaseline(top = 50.dp)
    ) {
        Text(
            text = "Witaj w JetpackCompose!", modifier = mod
        )
        FilledTonalButton( onClick = { popUp(context) }, modifier = mod)
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
                    FilledTonalButton(onClick ={list.removeAt(index)} , modifier = mod) {
                        Text("Usuń")
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "first") {
        composable("first") {
            FirstScreen(
                navigate ={
                    navController.navigate("second")
                }
            )
        }
        composable(
            route = "second"
        ) {
            SecondScreen( onBack = { navController.popBackStack() })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(navigate: () -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Moja Aplikacja Compose") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text="To jest pierwszy ekran aplikacji")
            Button(onClick = { navigate() }) {
                Text("Idź do drugiego ekranu")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen( onBack: () -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Moja Aplikacja Compose") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "To jest drugi ekran aplikacji")
            Button(onClick = onBack) {
                Text("Powrót")
            }
        }
    }
}


@Composable
fun ListScreen(){
    val list =  mutableListOf<String>("Element 1", "Element 2", "Element 3", "Element 4", "Element 5")
    Column(modifier = Modifier.fillMaxSize().padding(10.dp), verticalArrangement = Arrangement.Top) {
        Text("Lista", fontSize = 25.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(5.dp))
        LazyColumn(modifier = Modifier.padding(5.dp)) {
            items(items = list) { list ->
                Text(list, fontSize = 15.sp, modifier = Modifier.padding(5.dp).fillMaxWidth().background(color = MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(40.dp)).padding(5.dp), fontWeight = FontWeight.Bold)
            }
        }
    }
}
@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Text("Home", textAlign = TextAlign.Center, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Text("Aplikacja z Bottom Navigation oraz ustawieniami motywu (DataStore)", textAlign = TextAlign.Center)

    }
}
fun SettingsScreen(){}