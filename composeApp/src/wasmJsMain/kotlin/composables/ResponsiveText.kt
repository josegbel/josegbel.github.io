package composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import utils.calculateTextSizeForScreen

@Composable
fun ResponsiveText(text: String, screenWidth: Int, baseTextSize: Int) {
    val scaledSize = baseTextSize.calculateTextSizeForScreen(screenWidth, text)
    Text(
        text = text,
        style = TextStyle(fontSize = scaledSize.sp, fontFamily = FontFamily.SansSerif)
    )
}