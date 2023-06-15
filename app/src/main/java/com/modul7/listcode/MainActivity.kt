package com.andrirahmadani.listcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.andrirahmadani.listcode.databinding.ActivityMainBinding

import com.andrirahmadani.listcode.fragment.CodeFragment
import com.andrirahmadani.listcode.fragment.ProfilFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(Menu.NONE, 1 , Menu.NONE, "Dark Mode")
        menu?.add(Menu.NONE, 2 , Menu.NONE, "Light Mode")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            2 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        return super.onOptionsItemSelected(item)
    }


    val fragmentHewan: Fragment = CodeFragment()
    val fragmentProfil: Fragment = ProfilFragment()
    val fm: FragmentManager = supportFragmentManager
    var active : Fragment = fragmentHewan

    private lateinit var bottomNavigationId: BottomNavigationView
    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem


    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonNavigation()
    }

    private fun buttonNavigation() {
        fm.beginTransaction().add(R.id.container, fragmentHewan).show(fragmentHewan).commit()

        fm.beginTransaction().add(R.id.container, fragmentProfil).hide(fragmentProfil).commit()

        bottomNavigationId = binding.navView
        menu = bottomNavigationId.menu

        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationId.setOnNavigationItemSelectedListener {item ->
            when(item.itemId) {
                R.id.navigation_hewan -> {
                    callFragment(0, fragmentHewan)
                }



                R.id.navigation_profil -> {
                    callFragment(1, fragmentProfil)
                }
            }
            false
        }
    }

    private fun callFragment(index: Int, fragment: Fragment) {
        menuItem  = menu.getItem(index)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}