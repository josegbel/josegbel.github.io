package composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import utils.calculateSizeForScreen

@Composable
fun ResponsiveText(text: String, color: Color = Color.Black, screenWidth: Int, baseTextSize: Int) {
    val scaledSize = baseTextSize.calculateSizeForScreen(screenWidth)
    Text(
        text = text,
        style = TextStyle(fontSize = scaledSize.sp, fontFamily = FontFamily.SansSerif, color = color)
    )
}