package com.antonioleiva.mymovies.ui.screens.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import com.antonioleiva.mymovies.ui.MyMoviesApp

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun MainScreen(onNavigate: (Int) -> Unit) {
    MyMoviesApp {
        val state = rememberScaffoldState()
        Scaffold(
            scaffoldState = state,
            topBar = { MainAppBar() }

        ) { padding ->
            MediaList(
                onClick = { onNavigate(it.id) },
                modifier = Modifier.padding(padding)
            )
        }

    }
}