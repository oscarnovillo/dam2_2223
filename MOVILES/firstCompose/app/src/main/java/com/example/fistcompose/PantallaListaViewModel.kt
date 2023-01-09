package com.example.fistcompose

import androidx.lifecycle.ViewModel
import com.example.fistcompose.data.ListaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PantallaListaViewModel  @Inject constructor(
    private val repository: ListaRepository
): ViewModel() {

    val listado = repository.getData()

    val _text = MutableStateFlow<String>("")
    val text : StateFlow<String> = _text




    fun changeText(texto: String)
    {

    }

}