package composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.IntSize
import kotlinx.browser.window
import org.w3c.dom.events.Event

@Composable
fun rememberWindowType(): State<WindowType> {
    val windowSize = remember { mutableStateOf(WindowType.COMPACT) }

    DisposableEffect(Unit) {
        val listener: (Event) -> Unit = {
            windowSize.value = WindowType.calculate(window.innerWidth)
            println("Window size on Px: ${window.innerWidth}")
        }

        window.addEventListener("resize", listener)
        println("Initial set Window size on Px: ${window.innerWidth}")
        windowSize.value = WindowType.calculate(window.innerWidth) // Initial set

        onDispose {
            window.removeEventListener("resize", listener)
        }
    }

    return windowSize
}
@Composable
fun rememberWindowSize(): State<IntSize> {
    val windowSize = remember { mutableStateOf(IntSize(0,0)) }

    DisposableEffect(Unit) {
        val listener: (Event) -> Unit = {
            windowSize.value = IntSize(window.innerWidth, window.innerHeight)
        }

        window.addEventListener("resize", listener)
        windowSize.value = IntSize(window.innerWidth, window.innerHeight) // Initial set

        onDispose {
            window.removeEventListener("resize", listener)
        }
    }

    return windowSize
}

enum class WindowType {
    COMPACT,
    MEDIUM,
    EXPANDED;

    companion object {
        fun calculate(width: Int): WindowType {
            return when {
                width < 600 -> COMPACT
                width < 840 -> MEDIUM
                else -> EXPANDED
            }
        }
    }
}
