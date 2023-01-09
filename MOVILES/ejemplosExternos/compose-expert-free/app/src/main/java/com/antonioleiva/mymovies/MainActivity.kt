package com.antonioleiva.mymovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import coil.annotation.ExperimentalCoilApi
import com.antonioleiva.mymovies.ui.MyMoviesApp
import com.antonioleiva.mymovies.ui.navigation.Navigation

@ExperimentalFoundationApi
@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMoviesApp {
                Navigation()
            }
        }
    }
}