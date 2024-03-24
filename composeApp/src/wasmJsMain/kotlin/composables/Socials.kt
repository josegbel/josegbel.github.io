package composables

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.Dp
import icons.Github
import icons.Gmail
import icons.Instagram
import icons.Linkedin
import icons.Medium
import icons.SocialMediaPack
import utils.Colors
import utils.openLink
import utils.transparentClickable


@Composable
fun Socials(iconSize: Dp, spacerSize: Dp) {
    var isGithubHover by remember { mutableStateOf(false) }
    var isLinkedinHover by remember { mutableStateOf(false) }
    var isMediumHover by remember { mutableStateOf(false) }
    var isGmailHover by remember { mutableStateOf(false) }
    var isInstagramHover by remember { mutableStateOf(false) }

    val gitHubScale by animateFloatAsState(
        targetValue = if (isGithubHover) 1.2f else 1f,
        animationSpec = TweenSpec(durationMillis = 300)
    )
    val linkedinScale by animateFloatAsState(
        targetValue = if (isLinkedinHover) 1.2f else 1f,
        animationSpec = TweenSpec(durationMillis = 300)
    )
    val mediumScale by animateFloatAsState(
        targetValue = if (isMediumHover) 1.2f else 1f,
        animationSpec = TweenSpec(durationMillis = 300)
    )
    val gmailScale by animateFloatAsState(
        targetValue = if (isGmailHover) 1.2f else 1f,
        animationSpec = TweenSpec(durationMillis = 300)
    )
    val instagramScale by animateFloatAsState(
        targetValue = if (isInstagramHover) 1.2f else 1f,
        animationSpec = TweenSpec(durationMillis = 300)
    )

    val githubTint = if (isGithubHover) Colors.GitHub else MaterialTheme.colors.onBackground
    val linkedInTint = if (isLinkedinHover) Colors.LinkedIn else MaterialTheme.colors.onBackground
    val mediumTint = if (isMediumHover) Colors.Medium else MaterialTheme.colors.onBackground
    val gmailTint = if (isGmailHover) Colors.Gmail else MaterialTheme.colors.onBackground
    val instagramTint = if (isInstagramHover) Colors.Instagram else MaterialTheme.colors.onBackground

    Row {
        Icon(
            imageVector = SocialMediaPack.Github,
            contentDescription = null,
            tint = githubTint,
            modifier = Modifier
                .size(iconSize)
                .transparentClickable {
                    openLink("https://github.com/josegbel")
                }
                .scale(gitHubScale)
                .onPointerEvent(
                    eventType = PointerEventType.Enter,
                    onEvent = {
                        isGithubHover = true
                    }
                )
                .onPointerEvent(
                    eventType = PointerEventType.Exit,
                    onEvent = {
                        isGithubHover = false
                    }
                )
        )
        Spacer(modifier = Modifier.size(spacerSize))
        Icon(
            imageVector = SocialMediaPack.Linkedin,
            contentDescription = null,
            tint = linkedInTint,
            modifier = Modifier
                .size(iconSize)
                .transparentClickable {
                    openLink("https://www.linkedin.com/in/josegbel/")
                }
                .scale(linkedinScale)
                .onPointerEvent(
                    eventType = PointerEventType.Enter,
                    onEvent = {
                        isLinkedinHover = true
                    }
                )
                .onPointerEvent(
                    eventType = PointerEventType.Exit,
                    onEvent = {
                        isLinkedinHover = false
                    }
                )
        )
        Spacer(modifier = Modifier.size(spacerSize))
        Icon(
            imageVector = SocialMediaPack.Medium,
            contentDescription = null,
            tint = mediumTint,
            modifier = Modifier
                .size(iconSize)
                .transparentClickable {
                    openLink("https://medium.com/@jose.gbel")
                }
                .scale(mediumScale)
                .onPointerEvent(
                    eventType = PointerEventType.Enter,
                    onEvent = {
                        isMediumHover = true
                    }
                )
                .onPointerEvent(
                    eventType = PointerEventType.Exit,
                    onEvent = {
                        isMediumHover = false
                    }
                )
        )
        Spacer(modifier = Modifier.size(spacerSize))
        Icon(
            imageVector = SocialMediaPack.Gmail,
            contentDescription = null,
            tint = gmailTint,
            modifier = Modifier
                .size(iconSize)
                .transparentClickable {
                    openLink("mailto:jose.gbel@gmail.com")
                }
                .scale(gmailScale)
                .onPointerEvent(
                    eventType = PointerEventType.Enter,
                    onEvent = {
                        isGmailHover = true
                    }
                )
                .onPointerEvent(
                    eventType = PointerEventType.Exit,
                    onEvent = {
                        isGmailHover = false
                    }
                )
        )
        Spacer(modifier = Modifier.size(spacerSize))
        Icon(
            imageVector = SocialMediaPack.Instagram,
            contentDescription = null,
            tint = instagramTint,
            modifier = Modifier
                .size(iconSize)
                .transparentClickable {
                    openLink("https://www.instagram.com/ypical.jose/")
                }
                .scale(instagramScale)
                .onPointerEvent(
                    eventType = PointerEventType.Enter,
                    onEvent = {
                        isInstagramHover = true
                    }
                )
                .onPointerEvent(
                    eventType = PointerEventType.Exit,
                    onEvent = {
                        isInstagramHover = false
                    }
                )
        )
    }
}
