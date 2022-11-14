package com.example.hiltmenu.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.hiltmenu.R
import com.example.hiltmenu.databinding.ActivityMainBinding
import com.example.hiltmenu.data.PersonaRepository
import com.example.roomviewmodel.data.PersonaRoomDatabase

import com.example.hiltmenu.domain.Cosa
import com.example.hiltmenu.domain.Persona
import com.example.hiltmenu.usecases.personas.GetPersonas
import com.example.hiltmenu.usecases.personas.GetPersonasDes
import com.example.hiltmenu.usecases.personas.InsertPersona
import com.example.hiltmenu.usecases.personas.InsertPersonaWithCosas
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable
import java.time.LocalDate

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var personasAdapter: PersonaAdapter


    private val viewModel: MainViewModel by viewModels()


    // añade el menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // Handle item selection
        return when (item.itemId) {
            R.id.action_action1 -> {


                true
            }
            R.id.action_action2 -> {


                true
            }
            R.id.action_action3 -> {


                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        personasAdapter = PersonaAdapter()
        binding.rvPersonas.adapter = personasAdapter

        binding.button.setOnClickListener {
            val cosas = listOf(Cosa(getString(R.string.cosa1), 22))
            viewModel.insertPersonaWithCosas(Persona(0, "nombre", LocalDate.now(), cosas))
            viewModel.getPersonasDes()
        }


        viewModel.personas.observe(this) { personas ->

            personasAdapter.submitList(personas)
        }
        viewModel.error.observe(this) {

            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

        }

    }
}