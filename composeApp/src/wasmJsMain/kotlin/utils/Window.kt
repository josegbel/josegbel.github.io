package utils

external fun openLink(url: String)

/*
 * This function calculates the text size based on the screen width.
 * The received Int is the base text size. (E.g. 16px)
 */
fun Int.calculateTextSizeForScreen(screenWidth: Int, textContent: String): Float {
    val sizeMultiplier = screenWidth / 480

    // Use TextMeasurer here if you need to measure text bounds; this is a simplified example
    // Assuming you have a way to measure text;
    // val measurer = TextMeasurer()
    // val bounds = measurer.measure(textContent, TextStyle(...))
    // Use bounds or other metrics to adjust text size

    return (this * sizeMultiplier).toFloat()
}
