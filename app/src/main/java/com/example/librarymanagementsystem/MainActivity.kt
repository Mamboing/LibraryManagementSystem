package com.example.librarymanagementsystem

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.librarymanagementsystem.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView

class MainActivity: AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(dataBinding.root)

        val navView = dataBinding.navView
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(navView, navController)
    }
}