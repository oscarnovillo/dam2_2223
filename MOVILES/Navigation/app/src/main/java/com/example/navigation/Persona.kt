package com.example.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Persona(
    val id: Int,
    val nombre: String
) : Parcelable
