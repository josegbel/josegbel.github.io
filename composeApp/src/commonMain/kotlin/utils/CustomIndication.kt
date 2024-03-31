package utils

import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role

val LocalCustomIndication = staticCompositionLocalOf<Indication> { DefaultCustomIndication }

private object DefaultCustomIndication : Indication {
    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        val isPressed = interactionSource.collectIsPressedAsState()
        return remember(interactionSource) {
            CustomIndicationInstance(isPressed)
        }
    }
}

private class CustomIndicationInstance(
    private val isPressed: State<Boolean>
) : IndicationInstance {
    override fun ContentDrawScope.drawIndication() {
        drawContent()
        if (isPressed.value) {
            drawCircle(color = Color.Black.copy(alpha = 0.15f), radius = size.minDimension * 0.9f)
        }
    }
}

fun Modifier.transparentClickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = onClick,
        role = role,
        indication = LocalCustomIndication.current,
        interactionSource = remember { MutableInteractionSource() }
    )
}