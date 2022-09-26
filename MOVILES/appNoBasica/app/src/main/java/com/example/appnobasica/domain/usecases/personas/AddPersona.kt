package com.example.appnobasica.domain.usecases.personas

import com.example.appnobasica.data.Repository
import com.example.appnobasica.domain.modelo.Persona

class AddPersona {

    fun invoke(persona: Persona) {
        val personaRepository = Repository.createSingleton()
        personaRepository.addPersona(persona)
    }
}