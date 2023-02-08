package com.example.myapplication

import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.PantallaState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class PantallaViewModel @Inject constructor() : ViewModel() {


    private val _state = MutableStateFlow<PantallaState>(PantallaState())
    val state = _state.asStateFlow()


    fun provocaError() {
        if (Random.nextBoolean())
            _state.update { it.copy(error = "Error ${LocalDateTime.now().second}") }
    }

    fun changeText(texto: String) {
        _state.update {
            it.copy(
                persona = it.persona.copy(
                    nombre = texto
                )
            )
        }
    }

    fun limpiaError() {
        _state.update {
            _state.value.copy(
                error = null
            )
        }
    }

    fun changeNumber(numero: Int) {
        _state.value = _state.value.copy(numero = numero)
    }


}