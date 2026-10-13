package com.example.pr4_ind_trembach

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.Calendar

class InputFragment : Fragment() {

    private var selectedDate: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = PrefsHelper(requireContext())

        val textHello = view.findViewById<TextView>(R.id.textHello)
        val btnDate = view.findViewById<Button>(R.id.btnDate)
        val editTemp = view.findViewById<EditText>(R.id.editTemp)
        val editCondition = view.findViewById<EditText>(R.id.editCondition)
        val btnSave = view.findViewById<Button>(R.id.btnSave)
        val btnGoOutput = view.findViewById<Button>(R.id.btnGoOutput)

        textHello.text = "Здравствуйте, ${prefs.getUser()}!"

        btnDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(
                requireContext(),
                { _, y, m, d ->
                    val monthStr = String.format("%02d", m + 1)
                    val dayStr = String.format("%02d", d)
                    selectedDate = "$dayStr.$monthStr.$y"
                    btnDate.text = selectedDate
                },
                year, month, day
            )
            datePicker.show()
        }

        btnSave.setOnClickListener {
            val temp = editTemp.text.toString().replace(',', '.').toDoubleOrNull() ?: 0.0
            val condition = editCondition.text.toString().trim()

            if (selectedDate.isEmpty()) {
                Toast.makeText(requireContext(), "Выберите дату!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (condition.isEmpty()) {
                Toast.makeText(requireContext(), "Введите погоду!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val list = prefs.getEntries().toMutableList()
            list.add(WeatherEntry(selectedDate, temp, condition))
            prefs.saveEntries(list)

            Toast.makeText(requireContext(), "Сохранено!", Toast.LENGTH_SHORT).show()

            selectedDate = ""
            btnDate.text = "Выбрать дату"
            editTemp.text.clear()
            editCondition.text.clear()
        }

        btnGoOutput.setOnClickListener {
            (activity as? MainActivity)?.goToOutput()
        }
    }
}