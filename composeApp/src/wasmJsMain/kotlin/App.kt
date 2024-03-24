
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import composables.CompactContent
import composables.ExpandedContent
import composables.WindowSize
import composables.rememberWindowSize
import jgweb.composeapp.generated.resources.Res
import jgweb.composeapp.generated.resources.background
import org.jetbrains.compose.resources.painterResource

@Composable
fun App() {
    val windowSize = rememberWindowSize()
    CompositionLocalProvider(LocalWindowSize provides windowSize.value) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(Res.drawable.background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop // Adjust the scaling to fit/fill as required
            )
            if (LocalWindowSize.current == WindowSize.COMPACT) {
                CompactContent()
            } else {
                ExpandedContent()
            }
        }
    }
}

val LocalWindowSize = compositionLocalOf { WindowSize.COMPACT }
