package com.invictus.common.ui.theme.animations

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import java.lang.reflect.Modifier

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ZoomInAnimation(
    content: @Composable () -> Unit
){
    var visible by remember{ mutableStateOf(false) }

    LaunchedEffect(Unit){
        visible = true
    }

    val animationDuration = 1500

    AnimatedVisibility(
        visible = visible,
        enter = scaleIn(animationSpec = tween(durationMillis = animationDuration)),
        exit = scaleOut(animationSpec = tween(durationMillis = animationDuration))
    ) {
        content()
    }
}