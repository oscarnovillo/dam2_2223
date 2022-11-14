package com.example.hiltmenu.usecases.personas

import com.example.hiltmenu.data.PersonaRepository
import com.example.hiltmenu.data.modelo.toCosa
import com.example.hiltmenu.data.modelo.toPersona
import javax.inject.Inject

class GetPersonasDes @Inject constructor(val personasRepository: PersonaRepository) {

    suspend fun invoke(id:Int) =
        personasRepository.getPersonaWithCosas(id).toPersona()


    suspend fun invoke() =
        personasRepository.getPersonaWithCosas().map { it.toPersona() }


    suspend fun invokeCosas() =
        personasRepository.getCosas().map { it.toCosa() }
}