package utils

import kotlin.math.log10

external fun openLink(url: String)

/*
 * This function calculates the text size based on the screen width.
 * The received Int is the base text size. (E.g. 16px)
 */
fun Int.calculateSizeForScreen(screenWidth: Int): Float {
    // Base screen width for comparison (e.g., 480) and minimum adjustment factor to avoid logarithm of zero
    val baseScreenWidth = 480
    val minAdjustmentFactor = 1.0  // Keeping it minimal for the logarithmic part

    // Linear scaling factor - adjust this to control how quickly the text size decreases
    val linearScalingFactor = 0.5 / baseScreenWidth

    // Calculate linear component
    val linearComponent = screenWidth * linearScalingFactor

    // Calculate logarithmic component using a minimal adjustment to ensure non-zero input
    val logComponent = log10((screenWidth + minAdjustmentFactor)) / log10((baseScreenWidth + minAdjustmentFactor))

    // Combine the components with weights to balance their effects. Adjust these weights as needed.
    val combinedMultiplier = (logComponent * 0.75) + (linearComponent * 0.25)

    // Apply the combined multiplier to scale the base text size
    return (this * combinedMultiplier * 2.5).toFloat()
}
