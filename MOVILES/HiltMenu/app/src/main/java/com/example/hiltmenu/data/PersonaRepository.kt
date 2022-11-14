package com.example.hiltmenu.data

import com.example.hiltmenu.data.modelo.PersonaEntity
import com.example.hiltmenu.data.modelo.PersonaWithCosas
import com.example.hiltmenu.data.modelo.toPersona
import javax.inject.Inject

class PersonaRepository @Inject constructor(private val personaDao: PersonaDao) {

    suspend fun getPersonas() = personaDao.getPersonas().map { it.toPersona() }

    suspend fun getCosas() = personaDao.getCosas()

    suspend fun getPersonasDes() = personaDao.getPersonasDes()

    suspend fun getPersonaWithCosas(id:Int) = personaDao.getPersonaWithCosas(id)

    suspend fun getPersonaWithCosas() = personaDao.getPersonaWithCosas()

    suspend fun insertPersona(persona: PersonaEntity) = personaDao.insert(persona)

    suspend fun insertPersonaEntera(persona: PersonaWithCosas) = personaDao.createTransaction(persona)
}