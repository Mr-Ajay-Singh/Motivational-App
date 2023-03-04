package com.invictus.motivationalquotes.ui.homeScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.invictus.common.utils.UnitConverter.DP
import com.invictus.common.utils.UnitConverter.SP
import com.invictus.motivationalquotes.ui.main.MainScreenIdentifier
import com.invictus.motivationalquotes.R
import com.invictus.motivationalquotes.data.ImageList
import com.invictus.motivationalquotes.data.QuotesList
import com.invictus.motivationalquotes.data.quotes.ConfidenceQuotes
import com.invictus.motivationalquotes.db.MotivationSharedPreferences
import com.invictus.motivationalquotes.utils.PagerState
import com.invictus.motivationalquotes.utils.PagingScreen

@Composable
fun HomeScreen(selectedPage: MutableState<MainScreenIdentifier>) {

    if (selectedPage.value != MainScreenIdentifier.HOME_PAGE) return

    val image = ImageList.getSelectedImage()

    val quote = ConfidenceQuotes.getConfidenceQuotes()
    val fontSize = remember { mutableStateOf(MotivationSharedPreferences.TEXT_SIZE) }
    val fontColor = remember { mutableStateOf(MotivationSharedPreferences.TEXT_COLOR) }

    val pagerState: PagerState = run {
        remember {
            PagerState(0, 0, quote.size - 1)
        }
    }

    LaunchedEffect(key1 = quote) {
        pagerState.maxPage = quote.size
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
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