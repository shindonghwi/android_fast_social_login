package wolf.shin.simple_social_login.model

sealed class LoginState{
    object Init: LoginState()
    object Loading: LoginState()
    data class Success(val token: String): LoginState()
    data class Error(val message: String?): LoginState()
}
