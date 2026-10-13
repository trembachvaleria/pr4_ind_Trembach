package com.example.pr4_ind_trembach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class OutputFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_output, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = PrefsHelper(requireContext())
        val textOutput = view.findViewById<TextView>(R.id.textOutput)
        val btnBack = view.findViewById<Button>(R.id.btnBack)

        val list = prefs.getEntries()

        if (list.isEmpty()) {
            textOutput.text = "Записей пока нет."
        } else {
            val sb = StringBuilder()
            list.forEachIndexed { index, w ->
                sb.append("${index + 1}) ${w.date}\n")
                sb.append("   ${w.temperature}°C, ${w.condition}\n\n")
            }
            textOutput.text = sb.toString()
        }

        btnBack.setOnClickListener {
            (activity as? MainActivity)?.goToInput()
        }
    }
}
