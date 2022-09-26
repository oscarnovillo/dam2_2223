package com.example.appnobasica.ui.pantallaMain

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.appnobasica.databinding.ActivityMainBinding
import com.example.appnobasica.domain.usecases.personas.AddPersona
import com.example.appnobasica.domain.usecases.personas.GetPersonas
import com.example.appnobasica.utils.StringProvider

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            StringProvider.instance(this),
            AddPersona(),
            GetPersonas(),
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            editTextTextPersonName.setText("Hola")
        }

        viewModel.uiState.observe(this) { state ->
            state.error?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                viewModel.errorMostrado()
            }
            if (state.error == null)
                binding.editTextTextPersonName.setText(state.persona.nombre)

        }


    }
}