package com.example.pr4_ind_trembach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editUser = view.findViewById<EditText>(R.id.editUser)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val textError = view.findViewById<TextView>(R.id.textError)

        val prefs = PrefsHelper(requireContext())

        btnLogin.setOnClickListener {
            val name = editUser.text.toString().trim()
            if (name.isEmpty()) {
                textError.text = "Введите имя!"
                return@setOnClickListener
            }
            prefs.saveUser(name)
            (activity as? MainActivity)?.goToInput()
        }
    }
}