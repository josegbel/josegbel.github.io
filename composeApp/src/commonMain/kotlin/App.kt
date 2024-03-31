
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntSize
import composables.ExpandedContent
import composables.WindowType
import composables.rememberWindowSize
import composables.rememberWindowType
import jgweb.composeapp.generated.resources.Res
import jgweb.composeapp.generated.resources.background
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    val windowType = rememberWindowType()
    val windowSize = rememberWindowSize()
    CompositionLocalProvider(
        LocalWindowType provides windowType.value,
        LocalWindowSize provides windowSize.value
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(Res.drawable.background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop // Adjust the scaling to fit/fill as required
            )
            ExpandedContent(Modifier.fillMaxSize())
        }
    }
}

val LocalWindowType = compositionLocalOf { WindowType.COMPACT }
val LocalWindowSize = compositionLocalOf { IntSize(0, 0) }
