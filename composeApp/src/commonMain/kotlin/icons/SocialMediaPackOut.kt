package icons

import androidx.compose.ui.graphics.vector.ImageVector
import kotlin.collections.List as ____KtList

public object SocialMediaPack

private var __SocialMediaIcons: ____KtList<ImageVector>? = null

public val SocialMediaPack.SocialMediaIcons: ____KtList<ImageVector>
  get() {
    if (__SocialMediaIcons != null) {
      return __SocialMediaIcons!!
    }
    __SocialMediaIcons = Socialmediapack.SocialMediaIcons + listOf(Medium, Gmail, Github, Instagram,
        Linkedin)
    return __SocialMediaIcons!!
  }
