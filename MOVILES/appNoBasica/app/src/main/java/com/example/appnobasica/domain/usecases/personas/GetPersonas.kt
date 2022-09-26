package com.example.appnobasica.domain.usecases.personas

import com.example.appnobasica.data.Repository

class GetPersonas {

    fun invoke() = Repository.createSingleton().getPersonas()
}