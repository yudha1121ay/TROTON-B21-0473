package com.bangkit.maskcam.settings.receiver

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.bangkit.maskcam.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    companion object{
        private const val  NOTIFICATION_ID = 1
        private const val  ID_REPEATING = 101
        private const val  CHANNEL_ID = "channel_01"
        private const val  CHANNEL_NAME = "Pengingat"
        private const val  TIME_FORMAT = "HH:mm"
        const val EXTRA_MESSAGE = "extra_message"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onReceive(context: Context, intent: Intent) {
        sendNotif(context)
    }
    private fun sendNotif(context: Context){
        val intent = context?.packageManager.getLaunchIntentForPackage("com.bangkit.maskcam")
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_notif)
            .setContentTitle(context.resources.getString(R.string.app_name))
            .setContentText("Ayo periksa protokol siswa agar terhindar dari virus COVID - 19")
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)

            builder.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = builder.build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    fun setRepeatingAlarm(context: Context, type: String, time: String, message:String){
        if (isDateInvalid(time, TIME_FORMAT)) return
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)
        intent.putExtra(EXTRA_TYPE, type)

        val timeFormat = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeFormat[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeFormat[1]))
        calendar.set(Calendar.SECOND, 0)
        val pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING, intent, 0)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
        Toast.makeText(context, "Pengingat telah diatur!", Toast.LENGTH_SHORT).show()
    }

        private fun isDateInvalid(time: String, timeFormat: String): Boolean {
        return try{
            val df = SimpleDateFormat(timeFormat, Locale.getDefault())
            df.isLenient = false
            df.parse(time)
            false
        } catch (e: ParseException){
            true
        }
    }
    fun cancelAlarm(context: Context){
        val alaramManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val requestCode = ID_REPEATING
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        pendingIntent.cancel()
        alaramManager.cancel(pendingIntent)
        Toast.makeText(context, "Pengingat Dimatikan.", Toast.LENGTH_SHORT).show()
    }
}