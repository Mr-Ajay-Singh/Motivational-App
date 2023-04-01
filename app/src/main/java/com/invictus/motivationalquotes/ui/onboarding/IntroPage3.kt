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
import com.invictus.motivationalquotes.data.SelectedTopics
import com.invictus.motivationalquotes.db.MotivationSharedPreferences
import com.invictus.motivationalquotes.ui.main.MainScreenIdentifier

@Composable
fun IntroPage3(selectedPage: MutableState<MainScreenIdentifier>) {

    val optionsList = arrayListOf(
        SelectedTopics.STAY_STRONG,
        SelectedTopics.CONFIDENCE,
        SelectedTopics.FOCUS,
        SelectedTopics.BREAKUP,
        SelectedTopics.FRIENDSHIP,
        SelectedTopics.LIFE,
    )

    val selectedIndex = arrayListOf<String>()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(272.DP)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.scenerie_background),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(272.DP)
                    )
                }

                Spacer(modifier = Modifier.height(20.DP))
            }

            item {
                TitleComponent(stringResource(id = R.string.select_the_fields))

                Spacer(modifier = Modifier.height(30.DP))
            }

            optionsList.chunked(2).forEach {
                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .padding(11.DP, 0.DP)
                            .fillMaxWidth()
                    ) {
                        SelectionButtonComponent(it[0].value) {
                            if (selectedIndex.contains(it)) {
                                selectedIndex.remove(it)
                            } else {
                                selectedIndex.add(it)
                            }
                        }

                        SelectionButtonComponent(
                            it[1].value,
                        ) {
                            if (selectedIndex.contains(it)) {
                                selectedIndex.remove(it)
                            } else {
                                selectedIndex.add(it)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(15.DP))
                }
            }

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.backgroundColor))
                .padding(16.DP)
                .align(alignment = Alignment.BottomCenter)
                .clickable {
                    if (selectedIndex.size > 0) MotivationSharedPreferences.SELECTED_TOPICS =
                        selectedIndex.joinToString("#")
                    selectedPage.value = MainScreenIdentifier.HOME_PAGE
                    MotivationSharedPreferences.FIRST_TIME_USER = false
                }

        ) {
            ButtonComponent(text = stringResource(id = R.string.continue_text))
        }

    }


}