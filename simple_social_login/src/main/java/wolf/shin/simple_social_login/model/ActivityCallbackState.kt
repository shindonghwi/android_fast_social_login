package wolf.shin.simple_social_login.model

sealed class ActivityCallbackState<out T> {
    object Init : ActivityCallbackState<Nothing>()
    object Loading : ActivityCallbackState<Nothing>()
    data class Success(val token: String) : ActivityCallbackState<String>()
    data class Error(val message: String?) : ActivityCallbackState<Nothing>()
}