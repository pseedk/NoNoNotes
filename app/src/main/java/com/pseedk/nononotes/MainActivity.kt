package com.pseedk.nononotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.pseedk.nononotes.databinding.ActivityMainBinding
import com.pseedk.nononotes.utilits.APP_ACTIVITY
import com.pseedk.nononotes.utilits.AppPreference
import com.pseedk.nononotes.utilits.VIEW_FOR_SNACKBAR

class MainActivity : AppCompatActivity() {

    lateinit var mToolbar: Toolbar
    lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP_ACTIVITY = this
        VIEW_FOR_SNACKBAR = binding.mainLayout
        mToolbar = binding.toolbar
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setSupportActionBar(mToolbar)
        title = getString(R.string.title_main)
        AppPreference.getPreference(this)
    }

}