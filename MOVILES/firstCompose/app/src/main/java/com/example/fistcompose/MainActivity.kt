package com.example.fistcompose

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Earbuds
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.fistcompose.ui.theme.FistComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FistComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    PrimerBox()
                }
            }
        }
    }


    fun irPantalla() {
        startActivity(Intent(this, MainActivity2::class.java))
    }

}


@Composable
fun PrimerBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        contentAlignment = Alignment.Center

    ) {

        val snackbarHostState = remember { SnackbarHostState() }


        val i = 0;
        val context = LocalContext.current
        val circularProgressDrawable: CircularProgressDrawable = remember {
            val c = CircularProgressDrawable(context)
            c.strokeWidth = 5f
            c.centerRadius = 30f
            c.start()
            c
        }
        Image(
            painter = rememberImagePainter(
                data = "https://placebear.com/200/300",
                builder = {
                    placeholder(circularProgressDrawable)
                    transformations(CircleCropTransformation())
                    crossfade(durationMillis = 2000)
                }
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit


        )
        val lista =
            listOf(3, 1, 1, 1)


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {


            items(lista) { numero ->
                val color = MaterialTheme.colors
                var background by remember{ mutableStateOf(color.primary) }
                Row(
                    Modifier
                        .clickable {
                            background =
                                if (background == color.secondary) color.primary else color.secondary
                        }
                        .background(background)) {
                    Button(
                        onClick = {

                        },

                        ) {
                        Text(text = "Hola $numero")
                    }
                    if (background != color.secondary) {
                        Text(
                            text = numero.toString(),


                            style = MaterialTheme.typography.h6,
                        )
                    }
                }
            }
        }



        Greeting(
            name = "JJrf J",
            modifier = Modifier.align(Alignment.TopStart)
        )
        Greeting(
            name = "JJaa ddJ",
            modifier = Modifier.align(Alignment.TopStart)
        )
        Greeting(

            name = stringResource(R.string.ivan),
            modifier = Modifier.align(Alignment.BottomEnd)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            val scope = rememberCoroutineScope()

            Icon(

                imageVector = Icons.TwoTone.Earbuds,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clickable {

                        context.startActivity(Intent(context, MainActivity2::class.java))

                    },
                tint = MaterialTheme.colors.primary,

                )
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_ac_unit_24),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clickable {
                        scope.launch {
                            snackbarHostState.showSnackbar("Hello")
                        }
                        Toast
                            .makeText(context, "TOAST", Toast.LENGTH_SHORT)
                            .show()

                    },

                tint = MaterialTheme.colors.primary,
            )
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomEnd)
        )

    }
}

@Preview(showBackground = true, widthDp = 200, heightDp = 200)
@Composable
fun DefaultPreview() {
    FistComposeTheme {
        PrimerBox()
    }
}
