package com.invictus.motivationalquotes.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.invictus.common.utils.UnitConverter.DP
import com.invictus.common.utils.UnitConverter.SP
import com.invictus.motivationalquotes.R

@Composable
fun TitleComponent(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h5.copy(
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.SP
        ),
        color = colorResource(id = R.color.primaryColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(36.DP, 0.DP)
    )
}

@Composable
fun TitleDescriptionComponent(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h5.copy(
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = 14.SP
        ),
        color = colorResource(id = R.color.primaryColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(36.DP, 0.DP)
    )
}

@Composable
fun ButtonComponent(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.primaryColor), RoundedCornerShape(10.DP))
            .padding(10.DP)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h5.copy(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                fontSize = 16.SP
            ),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun SelectionButtonComponent(text: String, isSelected: Boolean,callback: (String) -> Unit) {
    Box(
        modifier = Modifier
            .width(186.DP)
            .clickable { callback(text) }
            .background(
                color = if (isSelected) colorResource(id = R.color.primaryColor) else Color.Transparent,
                shape = RoundedCornerShape(10.DP)
            )
            .border(
                2.DP,
                color = colorResource(id = R.color.primaryColor),
                shape = RoundedCornerShape(10.DP)
            )
            .padding(0.DP,10.DP)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h5.copy(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                fontSize = 16.SP
            ),
            color = if (isSelected) Color.White else colorResource(id = R.color.primaryColor),
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
fun TitleBackComponent(text: String,callback: ()->Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ){
        Image(
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = null,
            modifier = Modifier
                .size(24.DP)
                .clickable { callback() }
        )

        Spacer(modifier = Modifier.width(20.DP))

        Text(
            text = text,
            style = MaterialTheme.typography.h5.copy(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.SP
            ),
            color = colorResource(id = R.color.primaryColor),
        )

    }
}