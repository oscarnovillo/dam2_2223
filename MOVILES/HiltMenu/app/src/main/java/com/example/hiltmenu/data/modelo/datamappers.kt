package com.example.hiltmenu.data.modelo

import com.example.hiltmenu.domain.Cosa
import com.example.hiltmenu.domain.Persona

fun PersonaEntity.toPersona() : Persona {
    return Persona(this.id,this.nombre,this.nacimiento)
}
fun PersonaWithCosas.toPersona() : Persona {
    return Persona(this.persona.id,this.persona.nombre,this.persona.nacimiento,this.cosa?.map { it.toCosa() })
}
fun CosaEntity.toCosa(): Cosa {
    return Cosa(this.nombre,this.id)
}

fun Persona.toPersonaEntity(): PersonaEntity = PersonaEntity(this.nombre,this.nacimiento,this.id)

fun Persona.toPersonaWithCosas(): PersonaWithCosas = PersonaWithCosas(this.toPersonaEntity(),this.cosas?.map{it.toCosaEntity(this.id)})

fun Cosa.toCosaEntity(personaId:Int =0): CosaEntity = CosaEntity(nombre,personaId, id)