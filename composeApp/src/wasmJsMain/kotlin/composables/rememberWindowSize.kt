package composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.browser.window
import org.w3c.dom.events.Event

@Composable
fun rememberWindowSize(): State<WindowSize> {
    val windowSize = remember { mutableStateOf(WindowSize.COMPACT) }

    DisposableEffect(Unit) {
        val listener: (Event) -> Unit = {
            windowSize.value = WindowSize.calculate(window.innerWidth)
            println("Window size on Px: ${window.innerWidth}")
        }

        window.addEventListener("resize", listener)
        println("Initial set Window size on Px: ${window.innerWidth}")
        windowSize.value = WindowSize.calculate(window.innerWidth) // Initial set

        onDispose {
            window.removeEventListener("resize", listener)
        }
    }

    return windowSize
}

enum class WindowSize {
    COMPACT,
    MEDIUM,
    EXPANDED;

    companion object {
        fun calculate(width: Int): WindowSize {
            return when {
                width < 600 -> COMPACT
                width < 840 -> MEDIUM
                else -> EXPANDED
            }
        }
    }
}
