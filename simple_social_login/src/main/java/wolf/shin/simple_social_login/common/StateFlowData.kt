package wolf.shin.simple_social_login.common

import kotlinx.coroutines.flow.MutableStateFlow
import wolf.shin.simple_social_login.model.LoginState
import wolf.shin.simple_social_login.model.LogoutState
import wolf.shin.simple_social_login.model.UnlinkState

data class StateFlowData(
    val loginFlow: MutableStateFlow<LoginState<String>> = MutableStateFlow(LoginState.Init),
    val logoutFlow: MutableStateFlow<LogoutState> = MutableStateFlow(LogoutState.Init),
    val unlinkFlow: MutableStateFlow<UnlinkState> = MutableStateFlow(UnlinkState.Init)
)
