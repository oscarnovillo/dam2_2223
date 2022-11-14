package com.example.hiltmenu.usecases.personas

import com.example.hiltmenu.data.PersonaRepository
import com.example.hiltmenu.data.modelo.toPersonaEntity
import com.example.hiltmenu.domain.Persona
import javax.inject.Inject

class InsertPersona @Inject constructor(val personasRepository: PersonaRepository) {

    suspend fun invoke(persona: Persona) = personasRepository.insertPersona(persona.toPersonaEntity())
}