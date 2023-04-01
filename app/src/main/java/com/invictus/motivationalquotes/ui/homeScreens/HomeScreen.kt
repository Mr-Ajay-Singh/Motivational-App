package com.invictus.motivationalquotes.ui.homeScreens

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.invictus.common.utils.UnitConverter.DP
import com.invictus.common.utils.UnitConverter.SP
import com.invictus.motivationalquotes.BuildConfig
import com.invictus.motivationalquotes.R
import com.invictus.motivationalquotes.data.ImageList
import com.invictus.motivationalquotes.data.QuotesList
import com.invictus.motivationalquotes.db.MotivationSharedPreferences
import com.invictus.motivationalquotes.ui.homeScreens.ideintifiers.ImageShareIdentifier
import com.invictus.motivationalquotes.ui.main.MainScreenIdentifier
import com.invictus.motivationalquotes.ui.utils.TypographyList
import com.invictus.motivationalquotes.utils.ImageHelper
import com.invictus.motivationalquotes.utils.PagerState
import com.invictus.motivationalquotes.utils.PagingScreen
import com.invictus.motivationalquotes.utils.downloadWallpaper
import com.invictus.motivationalquotes.utils.saveWallpaper
import com.invictus.motivationalquotes.utils.setWallpaper
import com.invictus.motivationalquotes.utils.setWallpaperUsingBitmap
import com.invictus.motivationalquotes.utils.shareFun
import dev.shreyaspatil.capturable.Capturable
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import splitties.init.appCtx
import splitties.toast.toast
import timber.log.Timber


@Composable
fun HomeScreen(selectedPage: MutableState<MainScreenIdentifier>) {

    val image = remember { ImageList.getSelectedImage() }

    val quote = remember { QuotesList.getQuotesList() }
    val fontSize = remember { mutableStateOf(MotivationSharedPreferences.TEXT_SIZE) }
    val fontColor = remember { mutableStateOf(MotivationSharedPreferences.TEXT_COLOR) }
    val screenshotController = rememberCaptureController()
    val isSettingVisible = remember { mutableStateOf(true) }
    val shareImageOptions = remember { mutableStateOf(ImageShareIdentifier.SHARE) }

    val context = LocalContext.current
    val typography =
        remember { TypographyList.getTypographyList()[MotivationSharedPreferences.SELECTED_TYPOGRAPHY] }

    val pagerState: PagerState = run {
        remember {
            PagerState(0, 0, if (quote.isNotEmpty()) quote.size - 1 else 0)
        }
    }

    LaunchedEffect(key1 = quote) {
        pagerState.maxPage = quote.size
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            toast(context.getString(R.string.try_to_download_again))
        } else {
            toast(context.getString(R.string.permission_denied))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primaryColor))
    ) {
        Capturable(
            controller = screenshotController,
            onCaptured = { bitmap, error ->
                isSettingVisible.value = true
                // This is captured bitmap of a content inside Capturable Composable.
                if (bitmap != null) {
                    when (shareImageOptions.value) {
                        ImageShareIdentifier.SHARE -> shareFun(bitmap.asAndroidBitmap(), context)
                        ImageShareIdentifier.SET_WALLPAPER -> setWallpaperUsingBitmap(
                            context,
                            bitmap.asAndroidBitmap()
                        )
                        ImageShareIdentifier.SAVE -> when (PackageManager.PERMISSION_GRANTED) {
                            ContextCompat.checkSelfPermission(
                                context,
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) Manifest.permission.READ_MEDIA_IMAGES
                                else Manifest.permission.WRITE_EXTERNAL_STORAGE
                            ) -> {
                                saveWallpaper(bitmap.asAndroidBitmap())
                            }

                            else -> {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                    launcher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                                } else {
                                    launcher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                }
                            }
                        }
                    }
                }

                if (error != null) {
                    // Error occurred. Handle it!
                }
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            PagingScreen(pagerState) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (quote.isNotEmpty() && commingPage < quote.size) {
                        Text(
                            text = quote[commingPage],
                            style = typography.h5.copy(
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Normal,
                                fontSize = fontSize.value.SP
                            ),
                            color = Color(fontColor.value),
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .padding(30.DP)
                        )
                    }
                }
            }
        }


        if (isSettingVisible.value) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.DP)

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OpenPageButton(
                        R.drawable.collection,
                        stringResource(id = R.string.collection)
                    ) {
                        selectedPage.value = MainScreenIdentifier.CATEGORIES
                    }


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OpenPageButton(R.drawable.edit) {
                            selectedPage.value = MainScreenIdentifier.WALLPAPER
                        }

                        OpenPageButton(R.drawable.setting) {
                            selectedPage.value = MainScreenIdentifier.SETTING
                        }
                    }

                }
            }

            Column(
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd)
                    .padding(end = 23.DP, bottom = 50.DP)
            ) {
                Icon(painter = painterResource(id = R.drawable.share_filled),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.DP)
                        .clickable {
                            GlobalScope.launch(Dispatchers.Default) {
                                try {
                                    shareImageOptions.value = ImageShareIdentifier.SHARE
                                    isSettingVisible.value = false
                                    delay(50)
                                    screenshotController.capture()
                                } catch (e: Exception) {
                                    Timber.d("==>Screenshot issue $e")
                                }
                            }
                        })

                Spacer(modifier = Modifier.height(20.DP))

                Icon(painter = painterResource(id = R.drawable.set_wallpaper),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.DP)
                        .clickable {
                            GlobalScope.launch(Dispatchers.Default) {
                                try {
                                    shareImageOptions.value = ImageShareIdentifier.SET_WALLPAPER
                                    isSettingVisible.value = false
                                    delay(50)
                                    screenshotController.capture()
                                } catch (e: Exception) {
                                    Timber.d("==>Screenshot issue $e")
                                }
                            }
                        })

                Spacer(modifier = Modifier.height(20.DP))

                Icon(painter = painterResource(id = R.drawable.download_icon),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.DP)
                        .clickable {
                            GlobalScope.launch(Dispatchers.Default) {
                                try {
                                    shareImageOptions.value = ImageShareIdentifier.SAVE
                                    isSettingVisible.value = false
                                    delay(50)
                                    screenshotController.capture()
                                } catch (e: Exception) {
                                    Timber.d("==>Screenshot issue $e")
                                }
                            }
                        })

            }
        }

    }


}

@Composable
fun OpenPageButton(image: Int?, text: String = "", callback: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable { callback() }
            .background(
                color = if (text.isNotEmpty()) colorResource(id = R.color.collectionBackground) else Color.Transparent,
                shape = RoundedCornerShape(50.DP)
            )
            .padding(12.DP, 5.DP)
    ) {
        Image(
            painter = painterResource(id = image ?: R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier.size(24.DP)
        )

        if (text.isNotEmpty()) {

            Spacer(modifier = Modifier.width(17.DP))

            Text(
                text = text,
                style = MaterialTheme.typography.h5.copy(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.SP
                ),
                color = colorResource(id = R.color.white),
            )
        }
    }
}

