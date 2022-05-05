package wolf.shin.simple_social_login.kakao

import kotlinx.coroutines.flow.MutableStateFlow
import wolf.shin.simple_social_login.model.LoginState
import wolf.shin.simple_social_login.model.LogoutState
import wolf.shin.simple_social_login.model.UnlinkState

interface iKakaoLoginApi {
    suspend fun doKakaoLogin(loginFlow: MutableStateFlow<LoginState>)
    suspend fun doKakaoLogout(logoutFlow: MutableStateFlow<LogoutState>)
    suspend fun doUnlink(unlinkFlow: MutableStateFlow<UnlinkState>)
}