package com.invictus.motivationalquotes.ui.settingScreens

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.invictus.common.ui.theme.extensions.noRippleClickable
import com.invictus.common.utils.UnitConverter.DP
import com.invictus.common.utils.UnitConverter.SP
import com.invictus.motivationalquotes.R
import com.invictus.motivationalquotes.db.MotivationSharedPreferences
import com.invictus.motivationalquotes.ui.main.MainScreenIdentifier
import com.invictus.motivationalquotes.ui.onboarding.TitleBackComponent
import com.invictus.motivationalquotes.ui.utils.TypographyList

@Composable
fun SettingScreen(selectedPage: MutableState<MainScreenIdentifier>) {
    val context = LocalContext.current

    var fontSize by remember { mutableStateOf(MotivationSharedPreferences.TEXT_SIZE.toFloat()) }
    val fontColor = remember { mutableStateOf(MotivationSharedPreferences.TEXT_COLOR) }
    val isColorSelectedPageVisible = remember { mutableStateOf(false) }

    val settingList = remember {
        arrayListOf(
            Pair(
                R.drawable.privacy_policy,
                Pair(
                    context.getString(R.string.privacy_policy),
                    "https://doc-hosting.flycricket.io/motivational-quotes-privacy-policy/6a7b3356-05cf-4512-91c8-ad1ea31c4da3/privacy"
                )
            ),
            Pair(
                R.drawable.terms_conditions,
                Pair(
                    context.getString(R.string.terms_and_conditions),
                    "https://doc-hosting.flycricket.io/motivational-quotes-terms-of-use/34993005-e136-4af4-a062-2f6132dcbd27/terms"
                )
            ),
            Pair(
                R.drawable.rate_us_icon,
                Pair(
                    context.getString(R.string.rate_us),
                    "https://play.google.com/store/apps/details?id=com.invictus.motivationalquotes"
                )
            ),
//            Pair(R.drawable.rate_us_icon, context.getString(R.string.rate_us)),
//            Pair(R.drawable.share_icon, context.getString(R.string.share_out_app)),
//            Pair(R.drawable.feedback_icon, context.getString(R.string.feedback)),
        )
    }
    val typographyList = remember { TypographyList.getTypographyList() }

    BackHandler {
        selectedPage.value = MainScreenIdentifier.HOME_PAGE
    }

    LaunchedEffect(key1 = isColorSelectedPageVisible.value) {
        fontColor.value = MotivationSharedPreferences.TEXT_COLOR
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier.padding(16.DP)
        ) {
            TitleBackComponent(stringResource(id = R.string.setting)) {
                selectedPage.value = MainScreenIdentifier.HOME_PAGE
            }

            Spacer(modifier = Modifier.height(24.DP))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
            ) {
                settingList.forEach {
                    item {
                        SettingItem(image = it.first, text = it.second.first) {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(it.second.second)
                                )
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(30.DP))

                    Text(
                        text = stringResource(id = R.string.font_setting),
                        style = MaterialTheme.typography.h5.copy(
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.SP
                        ),
                        color = colorResource(id = R.color.primaryColor),
                    )

                    Spacer(modifier = Modifier.height(20.DP))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().noRippleClickable {
                            isColorSelectedPageVisible.value = true
                        },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = stringResource(id = R.string.font_color),
                                style = MaterialTheme.typography.h5.copy(
                                    textAlign = TextAlign.Left,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.SP
                                ),
                                color = colorResource(id = R.color.primaryColor),
                            )

                            Spacer(modifier = Modifier.width(16.DP))

                            Box(
                                modifier = Modifier
                                    .size(24.DP)
                                    .background(
                                        color = Color(fontColor.value),
                                        RoundedCornerShape(50.DP)
                                    )
                                    .border(1.DP, color = colorResource(id = R.color.primaryColor), RoundedCornerShape(50.DP))
                            )
                        }

                        Image(
                            painter = painterResource(id = R.drawable.right_setting),
                            contentDescription = null,
                            modifier = Modifier.width(20.DP)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.DP))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.font_size),
                            style = MaterialTheme.typography.h5.copy(
                                textAlign = TextAlign.Left,
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.SP
                            ),
                            color = colorResource(id = R.color.primaryColor),
                        )

                        Spacer(modifier = Modifier.width(10.DP))

                        Box(
                            modifier = Modifier.size(25.DP)
                        ) {
                            Text(
                                text = fontSize.toInt().toString(),
                                style = MaterialTheme.typography.h5.copy(
                                    textAlign = TextAlign.Left,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.SP
                                ),
                                color = colorResource(id = R.color.primaryColor),
                                modifier = Modifier.align(alignment = Alignment.Center)
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(12.DP))

                    Slider(value = fontSize, modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                        valueRange = 20f..40f,
                        steps = 5,
                        onValueChange = { newValue ->
                            fontSize = newValue
                            MotivationSharedPreferences.TEXT_SIZE = newValue.toInt()
                        }
                    )


                }

                item {
                    Spacer(modifier = Modifier.height(10.DP))

                    Text(
                        text = stringResource(id = R.string.select_font),
                        style = MaterialTheme.typography.h5.copy(
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.SP
                        ),
                        color = colorResource(id = R.color.primaryColor),
                    )

                    LazyRow {
                        typographyList.forEachIndexed { index, value ->
                            item {
                                Text(
                                    text = "Font$index",
                                    style = value.h5.copy(
                                        textAlign = TextAlign.Left,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.SP
                                    ),
                                    color = colorResource(id = R.color.black),
                                    modifier = Modifier.clickable {
                                        MotivationSharedPreferences.SELECTED_TYPOGRAPHY = index
                                        selectedPage.value = MainScreenIdentifier.HOME_PAGE
                                    }
                                )
                                Spacer(modifier = Modifier.width(12.DP))
                            }
                        }
                    }


                }
            }
        }

        Crossfade(targetState = isColorSelectedPageVisible.value) {
            if (it) ColorPickerComponent(isColorSelectedPageVisible)
        }

    }
}

@Composable
fun SettingItem(image: Int, text: String, callback: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .noRippleClickable { callback() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.width(24.DP)
            )

            Spacer(modifier = Modifier.width(24.DP))

            Text(
                text = text,
                style = MaterialTheme.typography.h5.copy(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.SP
                ),
                color = colorResource(id = R.color.primaryColor),
            )

        }

        Image(
            painter = painterResource(id = R.drawable.right_setting),
            contentDescription = null,
            modifier = Modifier.width(24.DP)
        )
    }

    Spacer(modifier = Modifier.height(20.DP))
}