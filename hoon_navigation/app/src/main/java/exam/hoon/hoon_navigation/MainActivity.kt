package exam.hoon.hoon_navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigation()
    }

    private fun initNavigation() {
        bottomNavigation = findViewById(R.id.bottom_nav)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        bottomNavigation.setupWithNavController(navController)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    Log.d("MainActivity", "onNavigationItemSelected, item = homeFragment")
                    moveFragment(R.id.action_global_homeFragment)
                }

                R.id.favoriteFragment -> {
                    Log.d("MainActivity", "onNavigationItemSelected, item = favoriteFragment")
                    moveFragment(R.id.action_global_favoriteFragment)
                }

                R.id.settingFragment -> {
                    Log.d("MainActivity", "onNavigationItemSelected, item = settingFragment")
                    moveFragment(R.id.action_global_settingFragment)
                }
            }
            false
        }
    }

    private fun moveFragment(action: Int) {
        navController.navigate(action)
    }
}