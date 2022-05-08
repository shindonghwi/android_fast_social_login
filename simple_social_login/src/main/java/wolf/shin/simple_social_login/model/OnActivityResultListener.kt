package wolf.shin.simple_social_login.model

import android.content.Intent

interface OnActivityResultListener {
    fun onActivityResult(resultCode: Int, intent: Intent?)
}