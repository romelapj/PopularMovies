package com.romelapj.popularmovies

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.romelapj.popularmovies.ui.MainActivityDelegate
import kotlinx.android.synthetic.main.activity_main.drawerLayout
import kotlinx.android.synthetic.main.activity_main.navHostFragment
import kotlinx.android.synthetic.main.activity_main.view.navView

class MainActivity : AppCompatActivity(), MainActivityDelegate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inflater = navHostFragment.findNavController().navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)
        navHostFragment.findNavController().graph = graph

        Navigation.findNavController(this, R.id.navHostFragment).onHandleDeepLink(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Navigation.findNavController(this, R.id.navHostFragment).onHandleDeepLink(intent)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp() =
            Navigation.findNavController(this, R.id.navHostFragment).navigateUp()




    override fun setupNavDrawer(toolbar: Toolbar) {
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        drawerLayout.navView.setupWithNavController(navHostFragment.findNavController())
    }

    override fun enableNavDrawer(enable: Boolean) {
        drawerLayout.isEnabled = enable
    }
}
