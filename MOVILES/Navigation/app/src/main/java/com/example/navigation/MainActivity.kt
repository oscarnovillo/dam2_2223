package com.example.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.example.navigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController( )
        //navController = findNavController(R.id.fragmentContainerView)

      //  navController = Navigation.findNavController(this,R.id.fragmentContainerView)

//        val appBarConfiguration = AppBarConfiguration
//            .Builder()
//            .setFallbackOnNavigateUpListener {
//                onBackPressed()
//                true
//            }.build()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.blankFragment,R.id.blankFragment2
            ), binding.drawerLayout
        ) {
            onBackPressed()
            true
        }

        //NavigationUI.setupActionBarWithNavController(this,navController, appBarConfiguration)
        setSupportActionBar(binding.topAppBar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

       // configAppBar()
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp()
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_uno, menu)
        return true
    }

    private fun configAppBar() {
//        binding.topAppBar.setNavigationOnClickListener {
//             binding.drawerLayout.open()
//        }

        //binding.topAppBar.setNavigationIcon(R.drawable.ic_launcher_foreground)
//        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.blankFragment,R.id.blankFragment2 -> {
//                    // Handle favorite icon press
//                    menuItem.onNavDestinationSelected(navController)
//                    //navController.navigate(R.id.action_blankFragment_to_blankFragment22)
//                    //findNavController().navigate()
//                    true
//                }
//                R.id.search -> {
//                    // Handle more item (inside overflow menu) press
//                    true
//                }
//                else -> false
//            }
//
//
//        }
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
                R.id.blankFragment,R.id.blankFragment2 -> {
                    // Handle favorite icon press
                    menuItem.onNavDestinationSelected(navController)
                    //navController.navigate(R.id.action_blankFragment_to_blankFragment22)
                    //findNavController().navigate()
                    true
                }
                R.id.search -> {
                    // Handle more item (inside overflow menu) press
                    true
                }
                else -> false
            }
        return super.onOptionsItemSelected(menuItem)
    }

}