package com.invictus.motivationalquotes.utils.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.invictus.motivationalquotes.R
import com.invictus.motivationalquotes.data.QuotesList
import com.invictus.motivationalquotes.ui.main.MainActivity
import kotlin.random.Random

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendReminderNotification(
            applicationContext = context,
            channelId = context.getString(R.string.reminders_notification_channel_id)
        )

        val reminderId = intent?.getIntExtra("reminderId",149) ?:149
        val reminderTime = intent?.getStringExtra("reminderTime") ?: "22:00"

        ReminderManager.startReminder(context.applicationContext,reminderTime,reminderId)
    }
}

fun NotificationManager.sendReminderNotification(
    applicationContext: Context,
    channelId: String,
) {
    val contentIntent = Intent(applicationContext, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(
        applicationContext,
        1,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val quoteList = QuotesList.getQuotesList()
    val quote = if(quoteList.isNotEmpty()) quoteList[Random.nextInt(quoteList.size-1)] else ""

    val builder = NotificationCompat.Builder(applicationContext, channelId)
        .setContentTitle(applicationContext.getString(R.string.motivation))
        .setContentText(quote)
        .setSmallIcon(R.mipmap.app_image2)
//        .setStyle(
//            NotificationCompat.BigTextStyle()
//                .bigText(applicationContext.getString(R.string.description_notification_reminder))
//        )
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    notify(NOTIFICATION_ID, builder.build())
}

const val NOTIFICATION_ID = 1