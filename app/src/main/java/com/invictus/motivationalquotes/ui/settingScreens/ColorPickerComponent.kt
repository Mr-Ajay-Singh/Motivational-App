package com.invictus.motivationalquotes.ui.settingScreens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.invictus.common.utils.UnitConverter.DP
import com.invictus.motivationalquotes.R
import com.invictus.motivationalquotes.db.MotivationSharedPreferences
import com.invictus.motivationalquotes.ui.main.MainScreenIdentifier
import com.invictus.motivationalquotes.ui.onboarding.TitleBackComponent

@Composable
fun ColorPickerComponent(isColorSelectedPageVisible:MutableState<Boolean>) {
    val controller = rememberColorPickerController()

    BackHandler {
        isColorSelectedPageVisible.value = false
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        color = Color.White
    ) {
        Column(
            modifier= Modifier
                .fillMaxWidth()
                .padding(16.DP)
        ){
            TitleBackComponent(stringResource(id = R.string.choose_text_color)) {
                isColorSelectedPageVisible.value = false
            }

            Spacer(modifier = Modifier.height(24.DP))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AlphaTile(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.DP)
                        .clip(RoundedCornerShape(6.DP)),
                    controller = controller
                )
            }

            HsvColorPicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.DP)
                    .padding(10.DP),
                controller = controller,
                onColorChanged = {
                    MotivationSharedPreferences.TEXT_COLOR = it.color.toArgb()
                }
            )
            AlphaSlider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.DP)
                    .height(35.DP),
                controller = controller,
                tileOddColor = Color.White,
                tileEvenColor = Color.Black
            )
            BrightnessSlider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.DP)
                    .height(35.DP),
                controller = controller,
            )
        }
    }

}