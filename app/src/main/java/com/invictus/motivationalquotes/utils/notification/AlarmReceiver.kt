package com.invictus.motivationalquotes.utils.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.invictus.motivationalquotes.R
import com.invictus.motivationalquotes.data.QuotesList
import com.invictus.motivationalquotes.ui.main.MainActivity
import timber.log.Timber
import kotlin.random.Random

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager

        Timber.d("==>alarm $intent")

        val reminderId = intent?.getIntExtra("reminderId",149) ?:149
        val reminderTime = intent?.getStringExtra("reminderTime") ?: "22:00"
        notificationManager.sendReminderNotification(
            applicationContext = context,
            channelId = context.getString(R.string.reminders_notification_channel_id),
            requestId = reminderId
        )

        ReminderManager.startReminder(context.applicationContext,reminderTime,reminderId)
    }
}

fun NotificationManager.sendReminderNotification(
    applicationContext: Context,
    channelId: String,
    requestId: Int
) {
    val contentIntent = Intent(applicationContext, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(
        applicationContext,
        requestId,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val quoteList = QuotesList.getQuotesList()
    val quote = if(quoteList.isNotEmpty()) quoteList[Random.nextInt(quoteList.size-1)] else ""



    val builder = NotificationCompat.Builder(applicationContext, channelId)
        .setContentTitle(applicationContext.getString(R.string.motivation))
        .setContentText(quote)
        .setSmallIcon(R.mipmap.app_image2)
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(quote)
        )
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .setVibrate(longArrayOf(1000, 1000))
        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);

    notify(requestId, builder.build())
}
