package com.software.listapp.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.software.listapp.R
import com.software.listapp.database.RoomDatabase
import com.software.listapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var roomDatabase: RoomDatabase
    private lateinit var binding: ActivityMainBinding
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}