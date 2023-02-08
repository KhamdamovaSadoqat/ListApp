package com.software.listapp.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.software.listapp.R
import com.software.listapp.database.RoomDatabase
import com.software.listapp.databinding.ActivityMainBinding
import com.software.listapp.utils.Connection
import com.software.listapp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var roomDatabase: RoomDatabase
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController
        val inflater = navController.navInflater
        val graph = inflater.inflate(R.navigation.mobile_navigation)
        graph.setStartDestination(R.id.productFragment)
        navController.graph = graph

        val connectionLiveData = Connection(this)
        connectionLiveData.observe(this) { isNetworkAvailable ->
            isNetworkAvailable?.let { if (!it) this.showToast("Internet unavailable!") }
        }
    }


}