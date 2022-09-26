package com.example.appnobasica.data

import com.example.appnobasica.domain.modelo.Persona

object RespositoryDos {

    private val personas = mutableListOf<Persona>()

    fun addPersona(persona: Persona) {
        personas.add(persona)
    }

    fun getPersonas(): List<Persona> {
        return personas
    }
}