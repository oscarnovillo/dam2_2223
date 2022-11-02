package com.example.roomviewmodel.ui.main

import com.example.roomviewmodel.domain.modelo.Persona

sealed class MainEvent(val persona: Persona? = null) {

    class InsertPersona(persona: Persona) : MainEvent(persona)
    object GetPersonas : MainEvent()
    object ErrorVisto : MainEvent()
}