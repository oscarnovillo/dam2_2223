package com.example.hiltmenu.data.modelo

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hiltmenu.data.modelo.CosaEntity
import com.example.hiltmenu.data.modelo.PersonaEntity


data class PersonaWithCosas(
    @Embedded val persona: PersonaEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "personaId"
    )
    val cosa: List<CosaEntity>?

)