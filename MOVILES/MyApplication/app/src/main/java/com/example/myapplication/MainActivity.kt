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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

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
    pantallaViewModel : PantallaViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val state by pantallaViewModel.state.collectAsState()
    val text by pantallaViewModel.text.collectAsState()



    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(MaterialTheme.colors.background)
        .border(1.dp, MaterialTheme.colors.primary),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier= Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.Blue)
                .border(1.dp, MaterialTheme.colors.primary)    ) {
            Text(
                text = stringResource(R.string.Hola) + " $name!",
                style= MaterialTheme.typography.button,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { pantallaViewModel.changeNumber(state.numero + 1) }

            )
           PutaCaja(

               viewModel = pantallaViewModel
//                label = { Text("Label") },
//                modifier = Modifier
//                    .align(Alignment.CenterHorizontally)
//                    .padding(16.dp)
            )
            Button(
                modifier = Modifier
                    .align(Alignment.Start),
                onClick = {
                    pantallaViewModel.changeNumber(state.numero+1)
                    Toast.makeText(
                        context,
                        context.resources.getString(R.string.Hola) + "${state.numero} ${state.texto}",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {

                Text(text = "Hola")
            }
        }
    }
}

@Composable
fun PutaCaja(
    viewModel: PantallaViewModel) {
    TextField(
        value = viewModel.state.collectAsState().value.texto,
        onValueChange = viewModel::changeText,
        label = { Text("Label") },
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