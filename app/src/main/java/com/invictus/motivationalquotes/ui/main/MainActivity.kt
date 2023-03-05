package com.invictus.motivationalquotes.ui.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.core.content.ContextCompat
import com.invictus.common.ui.theme.CommonApplicationTheme
import com.invictus.motivationalquotes.R
import com.invictus.motivationalquotes.ui.homeScreens.CategoriesSelectionPage
import com.invictus.motivationalquotes.ui.homeScreens.HomeScreen
import com.invictus.motivationalquotes.ui.homeScreens.SelectWallpaperPage
import com.invictus.motivationalquotes.ui.homeScreens.SettingScreen
import com.invictus.motivationalquotes.ui.onboarding.IntroPage1
import com.invictus.motivationalquotes.ui.onboarding.IntroPage2
import com.invictus.motivationalquotes.ui.onboarding.IntroPage3
import com.invictus.motivationalquotes.utils.notification.ReminderManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationsChannels(this)
        ReminderManager.startReminder(this)
        setContent {
            CommonApplicationTheme {
                HomePage()
            }
        }
    }
}

@Composable
fun HomePage() {

    val selectedPage = remember{ mutableStateOf(MainScreenIdentifier.HOME_PAGE) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.backgroundColor)
    ) {
        IntroPage1(selectedPage)
        IntroPage2(selectedPage)
        IntroPage3(selectedPage)
        HomeScreen(selectedPage)
        SelectWallpaperPage(selectedPage)
        CategoriesSelectionPage(selectedPage)
        SettingScreen(selectedPage)
    }

}


private fun createNotificationsChannels(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            context.getString(R.string.reminders_notification_channel_id),
            context.getString(R.string.reminders_notification_channel_name),
            NotificationManager.IMPORTANCE_HIGH
        )
        ContextCompat.getSystemService(context, NotificationManager::class.java)
            ?.createNotificationChannel(channel)
    }
}