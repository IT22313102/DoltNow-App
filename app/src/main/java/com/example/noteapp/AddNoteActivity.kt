package com.example.noteapp

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TimePicker
import android.widget.Toast
import com.example.noteapp.databinding.ActivityAddNoteBinding
import com.example.noteapp.databinding.ActivityMainBinding
import java.util.Calendar

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NotesDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = NotesDatabaseHelper(this)


        binding.updatesaveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val time = binding.timeEditText.text.toString() // Corrected to use the timeEditText field

            val note = Note (0,title,content,time)
            db.insertNote(note)
            finish()
            Toast.makeText(this,"Note Saved", Toast.LENGTH_SHORT).show()
        }

        // Call showTimePickerDialog when the "Select Time" button is clicked
        binding.selectTimeButton.setOnClickListener {
            showTimePickerDialog()
        }
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _: TimePicker, selectedHour: Int, selectedMinute: Int ->
                // Handle time selection
                // Here you can save the selected time to your Note object or any other required logic
                val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                Toast.makeText(this, "Selected Time: $selectedTime", Toast.LENGTH_SHORT).show()
                binding.timeEditText.setText(selectedTime) // Set the selected time to the timeEditText field

            },
            hour,
            minute,
            true
        )
        timePickerDialog.show()
    }


}