package wolf.shin.simple_social_login.model

sealed class UnlinkState{
    object Init: UnlinkState()
    object Loading: UnlinkState()
    object Success: UnlinkState()
    data class Error(val message: String?): UnlinkState()
}
