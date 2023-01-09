package com.example.fistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.BatteryUnknown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fistcompose.ui.theme.FistComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FistComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    Navigation()

                }
            }
        }
    }
}


@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.LISTADO,
    ) {
        composable(
            route = Routes.LISTADO
        ) {
            PantallaLista(
                text = "1",
                onNavigate = { id ->
                    navController.navigate(Routes.DETALLE + "?todoId=${id}")
                }
            )
        }
        composable(
            route = Routes.PANTALLA
        ) {
            PantallaDos(

                onNavigate = {
                    navController.navigate(it)
                }
            )
        }
        composable(
            route = Routes.DETALLE + "?todoId={todoId}",
            arguments = listOf(
                navArgument(name = "todoId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            PantallaDetalle(
                id = it.arguments?.get("todoId") as Int,
                onPopBackStack = {
                    navController.popBackStack()
                })
        }
    }

}

@Composable
fun PantallaDos(
    onNavigate: (String) -> Unit,
) {
    var texto by rememberSaveable {
        mutableStateOf(Routes.LISTADO)
    }

    TextField(value = texto, onValueChange = { texto = it })

    Button(onClick = { onNavigate(texto) }) {
        Row() {
            Icon(
                imageVector = Icons.Outlined.BatteryUnknown,
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
            )
            Text(text = "kkk")
        }
    }
}

fun cambiaTexto( nombre:String, pass:String){

}

@Composable
fun PantallaLista(
    viewModel: PantallaListaViewModel = hiltViewModel(),
    text: String,
    onNavigate: (Int) -> Unit,
) {
    val texto  = viewModel.text.collectAsState()
    val listadoData = viewModel.listado.collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxWidth()) {

        PrimerTrozo(
            text = texto.value,
            onNavigate = onNavigate,
            onTextChange = { viewModel.changeText(it) }
        )
        Button(onClick = { viewModel.changeText((texto.value.toInt() + 1).toString()) }) {
            Text(text = "suma")
        }

        TopAppBar(
            title = { Text(stringResource(id = R.string.app_name)) },
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {

            items(listadoData.value) { data ->
                Card(modifier = Modifier
                    .clickable {
                        onNavigate(data.id)
                    }
                    .padding(5.dp)

                ) {

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.secondary)
                            .padding(5.dp)
                    ) {
                        Text(text = "${data.id} ${data.nombre}")
                    }
                }
            }


        }



    }
}


@Composable
fun PrimerTrozo(
    text: String = "inicio",
    onTextChange: (String) -> Unit,
    onNavigate: (Int) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth(),
    ) {
        Greeting(
            "Android"
        )



        OutlinedTextField(
            value = text,
            isError = true,
            onValueChange = onTextChange,
        )

        Button(onClick = { onNavigate(text.toInt()) }) {
            Text(
                text = "vete"
            )
        }
    }

}

@Composable
fun PantallaDetalle(
    id: Int,
    onPopBackStack: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Greeting(
            "Pantalla Detalle $id"

        )
        Button(onClick = onPopBackStack) {
            Text(
                text = "Volver"
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    FistComposeTheme {

        Navigation()
    }
}
