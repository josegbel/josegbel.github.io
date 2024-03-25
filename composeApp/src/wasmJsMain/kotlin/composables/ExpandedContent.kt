package composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import kotlinx.coroutines.delay

@Composable
fun ExpandedContent(modifier: Modifier) {
    var showContent by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        delay(300L)
        showContent = true
    }

    // Initially, we don't show Text 1 and Text 3. They will be animated into visibility.
    val showText1 by remember { mutableStateOf(true) }
    val showText3 by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp), // Add padding if you want some spacing from the screen edges
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Text 1 slides down from the top
        AnimatedVisibility(
            visible = showContent,
            enter = slideInVertically(
                initialOffsetY = { -it }, // Start above the screen
                animationSpec = tween(durationMillis = 1000)
            ),
            exit = slideOutVertically(targetOffsetY = { -it })
        ) {
            Text("Text 1")
        }

        Spacer(modifier = Modifier.height(20.dp)) // Space between Text 1 and Text 2

        // Text 2, always visible in the center
        Text("Text 2")

        Spacer(modifier = Modifier.height(20.dp)) // Space between Text 2 and Text 3

        // Text 3 slides up from the bottom
        AnimatedVisibility(
            visible = showContent,
            enter = slideInVertically(
                initialOffsetY = { it }, // Start below the screen
                animationSpec = tween(durationMillis = 1000)
            ),
            exit = slideOutVertically(targetOffsetY = { it })
        ) {
            Text("Text 3")
        }
    }
//    BoxWithConstraints {
//        val screenHeight = maxHeight
//        val topSpacerHeight = -screenHeight / 4
//        val bottomSpacerHeight = screenHeight / 4
//
//        println("Screen Height: $screenHeight")
//        println("Local Height: ${LocalWindowSize.current.height}")
//
//        AnimatedVisibility(
//            visible = showContent,
//            enter = slideInVertically(
//                initialOffsetY = { -it * 5 },
//                animationSpec = tween(1000)
//            ) + fadeIn(tween(1000)),
//            modifier = Modifier.align(Alignment.TopCenter)
//        ) {
//            Column {
//                Spacer(modifier = Modifier.height(topSpacerHeight))
//                ResponsiveText(
//                    "Jose Garcia",
//                    baseTextSize = 24,
//                    screenWidth = LocalWindowSize.current.width,
//                )
//            }
//        }
//
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
//            modifier = Modifier.align(Alignment.Center)
//        ) {
//            AnimatedVisibility(
//                visible = showContent,
//                enter = fadeIn(animationSpec = tween(6500)),
//            ) {
//                ResponsiveText(
//                    "mobile developer",
//                    baseTextSize = 10,
//                    screenWidth = LocalWindowSize.current.width,
//                )
//            }
//        }
//
//        AnimatedVisibility(
//            visible = showContent,
//            enter = slideInVertically(
//                // Start from below the screen
//                initialOffsetY = { it * 5 },
//                animationSpec = tween(1000)
//            ) + fadeIn(animationSpec = tween(1000)),
//            modifier = Modifier.align(Alignment.BottomCenter)
//        ) {
//            Column {
//                Socials(42.dp, 48.dp)
//                Spacer(modifier = Modifier.height(bottomSpacerHeight))
//            }
//        }
//    }
}
