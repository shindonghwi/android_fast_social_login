package wolf.shin.simple_social_login.google

import kotlinx.coroutines.flow.MutableStateFlow
import wolf.shin.simple_social_login.model.LoginState
import wolf.shin.simple_social_login.model.LogoutState
import wolf.shin.simple_social_login.model.UnlinkState

interface IGoogleLoginApi {
    fun doGoogleLogin(loginFlow: MutableStateFlow<LoginState<String>>)
    fun doGoogleLogout(logoutFlow: MutableStateFlow<LogoutState>)
    fun doUnlink(unlinkFlow: MutableStateFlow<UnlinkState>)
}
