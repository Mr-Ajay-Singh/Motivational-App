package com.invictus.motivationalquotes.ui.homeScreens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.invictus.common.utils.UnitConverter.DP
import com.invictus.common.utils.UnitConverter.SP
import com.invictus.motivationalquotes.R
import com.invictus.motivationalquotes.data.SelectedTopicsList
import com.invictus.motivationalquotes.db.MotivationSharedPreferences
import com.invictus.motivationalquotes.ui.main.MainScreenIdentifier
import com.invictus.motivationalquotes.ui.onboarding.TitleBackComponent

@Composable
fun CategoriesSelectionPage(selectedPage: MutableState<MainScreenIdentifier>) {

    if (selectedPage.value != MainScreenIdentifier.CATEGORIES) return


    val categorySelection = SelectedTopicsList.getList()
    val selectionList = remember {
        SelectedTopicsList.getSelectedList().toMutableList()
    }

    BackHandler {
        selectedPage.value = MainScreenIdentifier.HOME_PAGE
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier.padding(16.DP)
        ) {
            TitleBackComponent(stringResource(id = R.string.categories)) {
                MotivationSharedPreferences.SELECTED_TOPICS = selectionList.joinToString("#")
                selectedPage.value = MainScreenIdentifier.HOME_PAGE
            }

            Spacer(modifier = Modifier.height(24.DP))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.DP),
                verticalArrangement = Arrangement.spacedBy(10.DP)
            ) {
                categorySelection.forEach {
                    item {
                        CategoriesItem(it,selectionList.contains(it)) { isSelected, value ->
                            if (isSelected) selectionList.add(value)
                            else selectionList.remove(value)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoriesItem(text: String,isSelect: Boolean, callback: (Boolean, String) -> Unit) {
    val isSelected = remember { mutableStateOf(isSelect) }
    Box(
        modifier = Modifier
            .width(175.DP)
            .height(118.DP)
            .background(
                color = colorResource(id = if (isSelected.value) R.color.selectedTopicColor else R.color.categoriesBackground),
                RoundedCornerShape(10.DP)
            )
            .clickable {
                isSelected.value = !isSelected.value
                callback(isSelected.value, text)
            }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h5.copy(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.SP
            ),
            color = colorResource(id = R.color.white),
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}