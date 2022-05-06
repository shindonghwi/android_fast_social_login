package wolf.shin.simple_social_login.model

sealed class LoginState<out T> {
    object Init : LoginState<Nothing>()
    object Loading : LoginState<Nothing>()
    data class Success(val token: String) : LoginState<String>()
    data class Error(val message: String?) : LoginState<Nothing>()
}
