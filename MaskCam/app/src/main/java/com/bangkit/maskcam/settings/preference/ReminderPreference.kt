package com.bangkit.maskcam.settings.preference

import android.content.Context
import com.bangkit.maskcam.settings.model.Reminder

class ReminderPreference(context: Context) {
    companion object{
        const val  PREFS = "reminder_pref"
        private const val REMINDER = "isRemind"
    }

    private val preference = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    fun setReminder(value: Reminder){
        val editor = preference.edit()
        editor.putBoolean(REMINDER, value.isReminded)
        editor.apply()
    }

    fun getReminder(): Reminder{
        val model = Reminder()
        model.isReminded = preference.getBoolean(REMINDER, false)
        return model
    }
}