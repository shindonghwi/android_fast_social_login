package wolf.shin.simple_social_login.interfaces

import wolf.shin.simple_social_login.model.ActivityCallbackState

interface IActivityResultCallback {
    fun <T> callbackState(state: ActivityCallbackState<T>)
}