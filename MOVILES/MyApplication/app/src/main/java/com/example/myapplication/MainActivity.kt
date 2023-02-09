package com.example.myapplication

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.ui.common.DrawerContent
import com.example.myapplication.ui.common.MiBottomBar
import com.example.myapplication.ui.common.MiTopAppBar
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PantallaHome("Android")
                }
            }
        }
    }
}


@Composable
fun PantallaHome(
    name: String,
    pantallaViewModel: PantallaViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val state by pantallaViewModel.state.collectAsState()

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MiTopAppBar(onMenuButtonClick = {
                scope.launch { // Abrir drawer
                    scaffoldState.drawerState.open()
                }
            })
        },

        drawerContent = { // Contenido del drawer
            DrawerContent { // Cerrar drawer
                scope.launch { scaffoldState.drawerState.close() }
            }
        },
        bottomBar = { MiBottomBar() },
        snackbarHost = {
            SnackbarHost(
                hostState = it,
                snackbar = { data -> Snackbar(data, backgroundColor = Color.Red) })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Crear")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true

    ) { paddingValues ->

        state.error?.let { error ->
            scope.launch {
                scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                scaffoldState.snackbarHostState.showSnackbar(error)
                pantallaViewModel.limpiaError()

            }
        }
        if (state.isLoading) {

            CircularProgressIndicator()
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colors.background)
                    .border(
                        dimensionResource(id = R.dimen.unpoquito),
                        MaterialTheme.colors.primary
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.activity_horizontal_margin))
                        .background(Color.White)
                        .border(1.dp, MaterialTheme.colors.primary)
                ) {
                    Text(
                        text = stringResource(R.string.Hola) + " $name!",
                        style = MaterialTheme.typography.button,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clickable { pantallaViewModel.changeNumber(state.numero + 1) }

                    )
                    PutaCaja(
                        texto = state.persona.nombre,
                        onNombreChange = pantallaViewModel::changeText,
                    )
                    Button(
                        modifier = Modifier
                            .align(Alignment.Start),
                        onClick = {
                            pantallaViewModel.provocaError()
                            pantallaViewModel.changeNumber(state.numero + 1)
                            Toast.makeText(
                                context,
                                context.resources.getString(R.string.Hola) + "${state.numero} ${state.error}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }) {

                        Text(text = stringResource(id = R.string.Hola))
                    }
                }
            }
        }

    }
}

@Composable
fun PutaCaja(
    texto: String,
    onNombreChange: (String) -> Unit,

    ) {
    TextField(
        value = texto,
        onValueChange = onNombreChange,
        label = { Text("Nombre") },
    )

}

@Preview(
    showBackground = true,

    device = Devices.PIXEL_4_XL,
    showSystemUi = true,
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,

    )
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),


            ) {
            PantallaHome("Android")
        }
    }
}