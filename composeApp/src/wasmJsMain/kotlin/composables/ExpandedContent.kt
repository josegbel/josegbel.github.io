package composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun ExpandedContent() {
    var showContent by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        delay(300L)
        showContent = true
    }

    BoxWithConstraints (modifier = Modifier.fillMaxSize()) {

        val screenHeight = maxHeight
        val topSpacerHeight = screenHeight * 0.35f
        val bottomSpacerHeight = screenHeight * 0.40f

        AnimatedVisibility(
            visible = showContent,
            enter = slideInVertically(
                initialOffsetY = { -it * 5 },
                animationSpec = tween(1000)
            ) + fadeIn(tween(1000)),
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Column {
                Spacer(modifier = Modifier.height(topSpacerHeight))
                Text(
                    "Jose Garcia",
                    fontSize = 100.sp,
                    // Style adjustments
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            AnimatedVisibility(
                visible = showContent,
                enter = fadeIn(animationSpec = tween(6500)),
            ) {
                Text(
                    "mobile developer",
                    fontSize = 36.sp,
                    fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
                    letterSpacing = 0.5.sp
                )
            }
        }

        AnimatedVisibility(
            visible = showContent,
            enter = slideInVertically(
                // Start from below the screen
                initialOffsetY = { it * 5 },
                animationSpec = tween(1000)
            ) + fadeIn(animationSpec = tween(1000)),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Column {
                Socials(42.dp, 48.dp)
                Spacer(modifier = Modifier.height(bottomSpacerHeight))
            }
        }
    }
}
