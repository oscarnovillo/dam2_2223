package com.example.hiltmenu.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.example.hiltmenu.domain.Persona
import com.example.hiltmenu.usecases.personas.GetPersonas
import com.example.hiltmenu.usecases.personas.GetPersonasDes
import com.example.hiltmenu.usecases.personas.InsertPersona
import com.example.hiltmenu.usecases.personas.InsertPersonaWithCosas
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getPersonas: GetPersonas,
                                        private val insertPersona: InsertPersona,
                                        private val insertPersonaWithCosas: InsertPersonaWithCosas,
                                        private val getPersonasDes: GetPersonasDes,) : ViewModel(){

    private val _personas = MutableLiveData<List<Persona>>()
    val personas: LiveData<List<Persona>> get() = _personas

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


    init {
        getPersonas()
    }


    fun getPersonas()
    {

        viewModelScope.launch {
            _personas.value = getPersonas.invoke()

        }

    }



    fun getPersonasDes()
    {

        viewModelScope.launch {
            val personas = getPersonasDes.invoke()

            val test = getPersonasDes.invoke()

            val test2 = getPersonasDes.invokeCosas()

           _personas.value = personas

        }


    }



    fun insertPersona(persona: Persona)
    {
        viewModelScope.launch {
            insertPersona.invoke(persona)
            _personas.value = getPersonas.invoke()
            //_personas.value = getPersonasDes.invoke(1)
        }
    }

//    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
//        Log.e("TAG:::","test",throwable)
//    }
    fun insertPersonaWithCosas(persona: Persona)
    {
        viewModelScope.launch() {
            try {
                insertPersona.invoke(persona)
                println(persona.id)
            }
            catch(e:Exception)
            {
                _error.value = e.message
                Log.e("TAG:::",e.message,e)
            }
            //_personas.value = getPersonasDes.invoke(1)
        }
    }

}

