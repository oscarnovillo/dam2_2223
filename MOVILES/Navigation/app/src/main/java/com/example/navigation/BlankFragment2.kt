package com.example.navigation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.onNavDestinationSelected
import com.example.navigation.databinding.BlankFragmentBinding
import com.example.navigation.databinding.FragmentBlankBinding

class BlankFragment2 : Fragment(), MenuProvider {

    private var _binding : BlankFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BlankViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  BlankFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args : BlankFragment2Args by navArgs()
        binding.texto.setText(args.persona?.nombre ?: "nulo")
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_dos, menu)

        val actionSearch = menu.findItem(R.id.search).actionView as SearchView

        actionSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                newText?.let {
                    binding.texto.setText(newText)
                }

                return false
            }


        })
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.nuevo -> {
                // Handle favorite icon press
                //menuItem.onNavDestinationSelected(navController)
                findNavController().navigate(R.id.action_blankFragment2_to_blankFragment)

                //findNavController().navigate()

                //Toast.makeText(context, "kkkk", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.nuevo2 -> {
                //menuItem.onNavDestinationSelected(navController)
                // Handle search icon press
                findNavController().navigate(R.id.action_blankFragment2_to_blankFragment3)
                true
            }
            else -> false
        }
    }

}
