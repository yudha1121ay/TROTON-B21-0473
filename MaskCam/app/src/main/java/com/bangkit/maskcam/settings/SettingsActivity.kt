package com.bangkit.maskcam.settings

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.maskcam.R
import com.bangkit.maskcam.databinding.ActivitySettingsBinding
import com.bangkit.maskcam.settings.model.Reminder
import com.bangkit.maskcam.settings.preference.ReminderPreference
import com.bangkit.maskcam.settings.receiver.AlarmReceiver

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var reminder: Reminder
    private lateinit var alarmReceiver: AlarmReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reminderPreference = ReminderPreference(this)
        if (reminderPreference.getReminder().isReminded) {
            binding.switch1.isChecked = true
        } else {
            binding.switch1.isChecked = false
        }

        alarmReceiver = AlarmReceiver()

        binding.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                saveReminder(true)
                alarmReceiver.setRepeatingAlarm(this, "Pengingat", "07:30", "Pengingat")
            } else {
                saveReminder(false)
                alarmReceiver.cancelAlarm(this)
            }
        }
    }
    private fun saveReminder(state: Boolean){
        val reminderPreference = ReminderPreference(this)
        reminder = Reminder()

        reminder.isReminded = state
        reminderPreference.setReminder(reminder)
    }
}