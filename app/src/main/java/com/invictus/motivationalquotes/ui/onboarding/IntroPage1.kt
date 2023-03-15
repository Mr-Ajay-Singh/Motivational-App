package com.invictus.motivationalquotes.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.invictus.common.utils.UnitConverter.DP
import com.invictus.common.utils.UnitConverter.SP
import com.invictus.motivationalquotes.R
import com.invictus.motivationalquotes.ui.main.MainScreenIdentifier

@Composable
fun IntroPage1(selectedPage: MutableState<MainScreenIdentifier>) {

    if(selectedPage.value != MainScreenIdentifier.INTRO_PAGE1) return

    val noOfTimes = remember { mutableStateOf(10) }
    val startTime = remember { mutableStateOf(10) }
    val endTime = remember { mutableStateOf(10) }

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
                        painter = painterResource(id = R.drawable.yoga_image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(272.DP)
                    )

                    Row(
                        modifier = Modifier
                            .width(290.DP)
                            .height(71.DP)
                            .align(alignment = Alignment.BottomCenter)
                            .padding(bottom = 17.DP)
                            .background(color = Color.White, RoundedCornerShape(10.DP)),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Box(
                            modifier = Modifier
                                .size(55.DP)
                                .padding(10.DP)
                                .background(
                                    color = colorResource(id = R.color.bellBackgrounImage),
                                    RoundedCornerShape(10.DP)
                                )
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.bell_image),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(42.DP)
                                    .align(alignment = Alignment.Center)
                            )
                        }

                        Text(
                            text = stringResource(id = R.string.in_two_month_greatful),
                            style = MaterialTheme.typography.h5.copy(
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.SP
                            ),
                            color = Color.Black,
                        )


                    }

                }

                Spacer(modifier = Modifier.height(20.DP))
            }

            item{
                TitleComponent(stringResource(id = R.string.daily_motivation_reminders))

                Spacer(modifier = Modifier.height(36.DP))
            }

            item{
                Box(modifier = Modifier
                    .padding(16.DP,0.DP)
                    .height(48.DP)
                    .fillMaxWidth()
                ){
                    DailyMotivationItem(stringResource(id = R.string.no_of_times),"${noOfTimes.value}X"){
                        if(it && noOfTimes.value < 16) noOfTimes.value +=1
                        if(!it && noOfTimes.value > 1) noOfTimes.value -=1
                    }
                }
                Spacer(modifier = Modifier.height(14.DP))
            }

            item{
                Box(modifier = Modifier
                    .padding(16.DP,0.DP)
                    .height(48.DP)
                    .fillMaxWidth()
                ){
                    DailyMotivationItem(stringResource(id = R.string.start_time),"${startTime.value}"){

                    }
                }
                Spacer(modifier = Modifier.height(14.DP))
            }

            item{
                Box(modifier = Modifier
                    .padding(16.DP,0.DP)
                    .height(48.DP)
                    .fillMaxWidth()
                ){
                    DailyMotivationItem(stringResource(id = R.string.end_time),"${endTime.value}"){

                    }
                }
                Spacer(modifier = Modifier.height(14.DP))
            }
            
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.BottomCenter)
                .background(color = colorResource(id = R.color.backgroundColor))
                .padding(16.DP)
                .clickable { selectedPage.value = MainScreenIdentifier.INTRO_PAGE2 }
        ){
            ButtonComponent(text = stringResource(id = R.string.allow_and_save))
        }

    }



}

@Composable
fun DailyMotivationItem(text:String,innerText: String, callback: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth().shadow(2.DP, RoundedCornerShape(5.DP))
            .background(
                color = colorResource(id = R.color.dailyMotivationItemColor),
                RoundedCornerShape(5.DP)
            )
            .border(
                1.DP,
                color = colorResource(id = R.color.primaryColor),
                RoundedCornerShape(5.DP)
            )

            .padding(15.DP),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = text,
            style = MaterialTheme.typography.h5.copy(
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Normal,
                fontSize = 16.SP
            ),
            color = colorResource(id = R.color.primaryColor),
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            IncrementDecrementButton("-"){
                callback(false)
            }

            Spacer(modifier = Modifier.width(15.DP))

            Text(
                text = innerText,
                style = MaterialTheme.typography.h5.copy(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.SP
                ),
                color = colorResource(id = R.color.primaryColor),
            )

            Spacer(modifier = Modifier.width(15.DP))

            IncrementDecrementButton("+"){
                callback(true)
            }
        }
    }
}

@Composable
fun IncrementDecrementButton(text: String,callback:()->Unit) {
    Row(
        modifier = Modifier
            .size(24.DP)
            .clickable { callback() }
            .background(color = colorResource(id = R.color.dailyMotivationItemColor), RoundedCornerShape(8.DP))
            .border(1.DP, color = colorResource(id = R.color.primaryColor), RoundedCornerShape(8.DP)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = text,
            style = MaterialTheme.typography.h5.copy(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                fontSize = 12.SP,
                lineHeight = 19.SP
            ),
            color = colorResource(id = R.color.primaryColor),
        )
    }
}