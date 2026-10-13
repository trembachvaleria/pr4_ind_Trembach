package com.example.pr4_ind_trembach

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = PrefsHelper(this)

        if (savedInstanceState == null) {
            if (prefs.isLoggedIn()) {
                goToInput()
            } else {
                goToWelcome()
            }
        }
    }

    fun goToWelcome() {
        replaceFragment(WelcomeFragment())
    }

    fun goToInput() {
        replaceFragment(InputFragment())
    }

    fun goToOutput() {
        replaceFragment(OutputFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}