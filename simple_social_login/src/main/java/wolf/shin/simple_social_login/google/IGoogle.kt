package wolf.shin.simple_social_login.google

import kotlinx.coroutines.flow.StateFlow
import wolf.shin.simple_social_login.model.LoginState
import wolf.shin.simple_social_login.model.LogoutState
import wolf.shin.simple_social_login.model.UnlinkState

interface IGoogle {
    fun doGoogleLogin(): StateFlow<LoginState<String>>
    fun doGoogleLogout(): StateFlow<LogoutState>
    fun doGoogleUnlink(): StateFlow<UnlinkState>
}
