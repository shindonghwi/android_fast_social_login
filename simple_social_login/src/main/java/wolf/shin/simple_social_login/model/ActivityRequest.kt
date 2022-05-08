package wolf.shin.simple_social_login.model

import android.content.Intent

data class ActivityRequest(
    val intent: Intent,
    val listener: OnActivityResultListener
)