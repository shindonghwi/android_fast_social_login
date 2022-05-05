package wolf.shin.simple_social_login.model

sealed class LogoutState{
    object Init: LogoutState()
    object Loading: LogoutState()
    object Success: LogoutState()
    data class Error(val message: String?): LogoutState()
}
