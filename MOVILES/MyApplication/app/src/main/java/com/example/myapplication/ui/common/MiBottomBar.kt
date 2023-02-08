package com.example.myapplication.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier

@Composable
fun MiBottomBar() {
    BottomAppBar {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
            IconButton(onClick = { /*TODO*/ }) { // (1)
                Icon(Icons.Filled.Menu, contentDescription = "Abri menú desplegable")
            }
        }

        Spacer(Modifier.weight(1f, true)) // (2)

        IconButton(onClick = { /*TODO*/ }) { // (3)
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Buscar")
        }
        IconButton(onClick = { /*TODO*/ }) { // (4)
            Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Más")
        }
    }
}