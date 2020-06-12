package com.fahmisbas.notes.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fahmisbas.notes.R
import com.fahmisbas.notes.notes.NotesListFragment
import com.fahmisbas.notes.tasks.TasksListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_tasks -> {
                    replaceFragment(TasksListFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_notes -> {
                    replaceFragment(NotesListFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        replaceFragment(TasksListFragment.newInstance())
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private fun replaceFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHolder,fragment)
            .commit()
    }
}