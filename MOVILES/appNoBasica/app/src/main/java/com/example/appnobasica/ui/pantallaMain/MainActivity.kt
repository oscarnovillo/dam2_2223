package com.example.appnobasica.ui.pantallaMain

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.appnobasica.databinding.ActivityMainBinding
import com.example.appnobasica.domain.modelo.Persona
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

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            editTextTextPersonName.setText("Hola")
            button.setOnClickListener {
                viewModel.addPersona(Persona(editTextTextPersonName.text.toString()))
            }

            viewModel.uiState.observe(this@MainActivity) { state ->
                state.error?.let {
                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                    viewModel.errorMostrado()
                }
                if (state.error == null)
                    editTextTextPersonName.setText(state.persona.nombre)


            }
        }







    }

}