package com.example.myapplication

import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.PantallaState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PantallaViewModel  @Inject constructor() : ViewModel() {



    private val _state = MutableStateFlow<PantallaState>(PantallaState())
    val state = _state.asStateFlow()


    private val _text = MutableStateFlow("")
    val text = _text.asStateFlow()

    fun changeText(texto: String)
    {
        _state.value = PantallaState(texto = texto)
        _text.value = texto
    }

    fun changeNumber(numero: Int)
    {
        _state.value = _state.value.copy(numero = numero)
    }


}