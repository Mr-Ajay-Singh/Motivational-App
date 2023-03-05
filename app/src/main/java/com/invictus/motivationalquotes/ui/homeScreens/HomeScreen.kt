package com.invictus.motivationalquotes.ui.homeScreens

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.invictus.common.utils.UnitConverter.DP
import com.invictus.common.utils.UnitConverter.SP
import com.invictus.motivationalquotes.BuildConfig
import com.invictus.motivationalquotes.R
import com.invictus.motivationalquotes.data.ImageList
import com.invictus.motivationalquotes.data.QuotesList
import com.invictus.motivationalquotes.db.MotivationSharedPreferences
import com.invictus.motivationalquotes.ui.main.MainScreenIdentifier
import com.invictus.motivationalquotes.utils.ImageHelper
import com.invictus.motivationalquotes.utils.PagerState
import com.invictus.motivationalquotes.utils.PagingScreen
import dev.shreyaspatil.capturable.Capturable
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import splitties.init.appCtx
import timber.log.Timber


@Composable
fun HomeScreen(selectedPage: MutableState<MainScreenIdentifier>) {

    if (selectedPage.value != MainScreenIdentifier.HOME_PAGE) return

    val image = remember{ImageList.getSelectedImage()}

    val quote = remember{QuotesList.getQuotesList()}
    val fontSize = remember { mutableStateOf(MotivationSharedPreferences.TEXT_SIZE) }
    val fontColor = remember { mutableStateOf(MotivationSharedPreferences.TEXT_COLOR) }
    val screenshotController = rememberCaptureController()
    val isSettingVisible = remember { mutableStateOf(true) }
    val context = LocalContext.current

    val pagerState: PagerState = run {
        remember {
            PagerState(0, 0, if (quote.isNotEmpty()) quote.size - 1 else 0)
        }
    }

    LaunchedEffect(key1 = quote) {
        pagerState.maxPage = quote.size
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Capturable(
            controller = screenshotController,
            onCaptured = { bitmap, error ->
                isSettingVisible.value = true
                // This is captured bitmap of a content inside Capturable Composable.
                if (bitmap != null) {
                    shareFun(bitmap.asAndroidBitmap(), context)
                    // Bitmap is captured successfully. Do something with it!
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
                            style = MaterialTheme.typography.h5.copy(
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Normal,
                                fontSize = fontSize.value.SP
                            ),
                            color = Color(fontColor.value).copy(alpha = 1f),
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
                    .align(alignment = Alignment.BottomCenter)

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OpenPageButton(R.drawable.collection) {
                        selectedPage.value = MainScreenIdentifier.CATEGORIES
                    }


                    OpenPageButton(R.drawable.edit) {
                        selectedPage.value = MainScreenIdentifier.WALLPAPER
                    }

                    OpenPageButton(R.drawable.setting) {
                        selectedPage.value = MainScreenIdentifier.SETTING
                    }

                }
            }

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd)
                    .padding(end = 23.DP, bottom = 130.DP)
            ) {
                Icon(painter = painterResource(id = R.drawable.share_filled),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(48.DP)
                        .clickable {
                            GlobalScope.launch(Dispatchers.Default) {
                                try {
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
                color = colorResource(id = R.color.tertiaryColor),
                shape = RoundedCornerShape(5.DP)
            )
            .padding(12.DP)
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

fun shareFun(bitmap: Bitmap?, context: Context) {

    GlobalScope.launch(Dispatchers.Main) {
        val imageFile = bitmap
            ?.let { ImageHelper.convertBitmapToFile(it, "purchasePremiumShare.jpg") }
            ?: return@launch

        val uri =
            FileProvider.getUriForFile(appCtx, BuildConfig.APPLICATION_ID + ".provider", imageFile)


        val i = Intent(Intent.ACTION_SEND)

        i.type = "image/*"
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        i.putExtra(
            Intent.EXTRA_STREAM,
            uri
        )

        val chooser = Intent.createChooser(i, "Share File")

        val resInfoList: List<ResolveInfo> = context.packageManager
            .queryIntentActivities(chooser, PackageManager.MATCH_DEFAULT_ONLY)

        for (resolveInfo in resInfoList) {
            val packageName = resolveInfo.activityInfo.packageName
            context.grantUriPermission(
                packageName,
                uri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }

        try {
            context.startActivity(chooser)
        } catch (ex: ActivityNotFoundException) {
            ex.printStackTrace()
        }

//        withContext(Dispatchers.Main) {
//            Timber.d("imageFile==>>${imageFile?.toUri()}")
//            mOnButtonClick?.invoke(imageFile?.toUri())
//            dismiss()
//        }
    }
}