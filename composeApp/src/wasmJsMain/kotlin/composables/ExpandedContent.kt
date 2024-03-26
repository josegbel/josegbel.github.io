package composables

import LocalWindowSize
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import utils.Colors
import utils.calculateSizeForScreen

@Composable
fun ExpandedContent(modifier: Modifier) {
    var showContent by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        delay(300L)
        showContent = true
    }

    val columnSpacerSize = 8.calculateSizeForScreen(LocalWindowSize.current.width).dp

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp), // Add padding if you want some spacing from the screen edges
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val height = LocalWindowSize.current.height

        AnimatedVisibility(
            visible = showContent,
            enter = slideInVertically(
                initialOffsetY = { -height / 2 }, // Start above the screen
                animationSpec = tween(durationMillis = 1000)
            ),
            exit = slideOutVertically(targetOffsetY = { -it })
        ) {
            ResponsiveText(
                "Jose Garcia",
                color = Colors.TextColor,
                baseTextSize = 24,
                screenWidth = LocalWindowSize.current.width,
            )
        }

        Spacer(modifier = Modifier.height(8.dp)) // Space between Text 1 and Text 2

        AnimatedVisibility(
                visible = showContent,
                enter = fadeIn(animationSpec = tween(6500)),
            ) {
            ResponsiveText(
                "mobile developer",
                color = Colors.TextColor,
                baseTextSize = 10,
                screenWidth = LocalWindowSize.current.width,
            )
        }

        Spacer(modifier = Modifier.height(columnSpacerSize)) // Space between Text 2 and Text 3

        AnimatedVisibility(
            visible = showContent,
            enter = slideInVertically(
                initialOffsetY = { height / 2 }, // Start below the screen
                animationSpec = tween(durationMillis = 1000)
            ),
            exit = slideOutVertically(targetOffsetY = { it })
        ) {
            val iconSize = 14.calculateSizeForScreen(LocalWindowSize.current.width).dp
            val spacerSize = 14.calculateSizeForScreen(LocalWindowSize.current.width).dp

            Socials(iconSize, spacerSize, iconColor = Colors.TextColor)
        }
    }
}
