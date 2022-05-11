package wolf.shin.simple_social_login.kakao

import kotlinx.coroutines.flow.MutableStateFlow
import wolf.shin.simple_social_login.model.LoginState
import wolf.shin.simple_social_login.model.LogoutState
import wolf.shin.simple_social_login.model.UnlinkState

interface IKakaoLoginApi {
    fun doKakaoLogin()
    fun doKakaoLogout(logoutFlow: MutableStateFlow<LogoutState>)
    fun doKakaoUnlink(unlinkFlow: MutableStateFlow<UnlinkState>)
}
