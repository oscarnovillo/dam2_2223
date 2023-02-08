package com.example.myapplication.ui

data class PantallaState(var persona:Persona = Persona(),var error:String? = null, var numero:Int = 0)

data class Persona(var nombre:String = "sin nombre", var edad:Int = 0)