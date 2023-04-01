package com.invictus.motivationalquotes.ui.homeScreens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.invictus.common.utils.UnitConverter.DP
import com.invictus.motivationalquotes.R
import com.invictus.motivationalquotes.data.ImageList
import com.invictus.motivationalquotes.db.MotivationSharedPreferences
import com.invictus.motivationalquotes.ui.main.MainScreenIdentifier
import com.invictus.motivationalquotes.ui.onboarding.TitleBackComponent

@Composable
fun SelectWallpaperPage(selectedPage: MutableState<MainScreenIdentifier>) {

    val imageList = ImageList.imageList()

    BackHandler {
        selectedPage.value = MainScreenIdentifier.HOME_PAGE
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier.padding(16.DP)
        ) {
            TitleBackComponent(stringResource(id = R.string.wallpaper)) {
                selectedPage.value = MainScreenIdentifier.HOME_PAGE
            }

            Spacer(modifier = Modifier.height(24.DP))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.DP),
                verticalArrangement = Arrangement.spacedBy(10.DP)
            ) {
                imageList.forEachIndexed { index,item->
                    item {
                        WallpaperPreviewComponent(index,item) {
                            MotivationSharedPreferences.SELECTED_IMAGE = it
                            selectedPage.value = MainScreenIdentifier.HOME_PAGE
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WallpaperPreviewComponent(index: Int,imageLink: String, callback: (Int) -> Unit) {

    AsyncImage(
        model = imageLink,
        contentDescription = null,
        modifier = Modifier
            .width(175.DP)
            .height(220.DP)
            .background(
                color = colorResource(id = R.color.imageBackground),
                RoundedCornerShape(10.DP)
            )
            .clip(RoundedCornerShape(10.DP))
            .clickable { callback(index) },
        contentScale = ContentScale.Crop,

    )

}