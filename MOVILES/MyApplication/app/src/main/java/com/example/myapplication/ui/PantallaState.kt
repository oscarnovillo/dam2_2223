package com.example.myapplication.ui

data class PantallaState(val persona:Persona = Persona(),val error:String? = null, val isLoading:Boolean = false,val numero:Int = 0)

data class Persona(var nombre:String = "sin nombre", var edad:Int = 0)