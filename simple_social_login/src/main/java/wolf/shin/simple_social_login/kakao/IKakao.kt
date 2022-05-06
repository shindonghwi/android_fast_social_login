package wolf.shin.simple_social_login.kakao

import kotlinx.coroutines.flow.StateFlow
import wolf.shin.simple_social_login.model.LoginState
import wolf.shin.simple_social_login.model.LogoutState
import wolf.shin.simple_social_login.model.UnlinkState

interface IKakao {
    fun doKakaoLogin(): StateFlow<LoginState<String>>
    fun doKakaoLogout(): StateFlow<LogoutState>
    fun doKakaoUnlink(): StateFlow<UnlinkState>
}
