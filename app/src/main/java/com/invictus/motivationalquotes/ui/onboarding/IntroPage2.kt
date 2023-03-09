package com.invictus.motivationalquotes.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.invictus.common.utils.UnitConverter.DP
import com.invictus.motivationalquotes.R
import com.invictus.motivationalquotes.ui.main.MainScreenIdentifier

@Composable
fun IntroPage2(selectedPage: MutableState<MainScreenIdentifier>) {

    if(selectedPage.value != MainScreenIdentifier.INTRO_PAGE2) return

    Box(
        modifier = Modifier.fillMaxSize()
    ){

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item{
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(272.DP)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.fox_image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(272.DP)
                    )
                }

                Spacer(modifier = Modifier.height(20.DP))
            }

            item{
                TitleComponent(stringResource(id = R.string.intro_title))

                Spacer(modifier = Modifier.height(30.DP))
            }

            item{
                TitleDescriptionComponent(stringResource(id = R.string.intro_desc))

            }

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.backgroundColor))
                .padding(16.DP)
                .align(alignment = Alignment.BottomCenter)
                .clickable { selectedPage.value = MainScreenIdentifier.INTRO_PAGE3 }
        ){
            ButtonComponent(text = stringResource(id = R.string.got_it))
        }

    }



}