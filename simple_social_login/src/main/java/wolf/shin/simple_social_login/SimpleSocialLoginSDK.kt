package wolf.shin.simple_social_login

import android.app.Activity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import wolf.shin.simple_social_login.common.CommonHelper
import wolf.shin.simple_social_login.kakao.KakaoLoginHelper
import wolf.shin.simple_social_login.model.LoginState
import wolf.shin.simple_social_login.model.LogoutState
import wolf.shin.simple_social_login.model.UnlinkState


class SimpleSocialLoginSDK {

    class Builder(
        private val activity: Activity
    ) {
        private val kakaoLoginHelper: KakaoLoginHelper = KakaoLoginHelper(activity)

        // 로그인 상태를 관찰 할 수 있는 Flow 생성
        private val _loginStateFlow = MutableStateFlow<LoginState<String>>(LoginState.Init)
        val loginStateFlow: StateFlow<LoginState<String>> get() = _loginStateFlow

        // 로그아웃 상태를 관찰 할 수 있는 Flow 생성
        private val _logoutStateFlow = MutableStateFlow<LogoutState>(LogoutState.Init)
        val logoutStateFlow: StateFlow<LogoutState> get() = _logoutStateFlow

        // 연결해제 상태를 관찰 할 수 있는 Flow 생성
        private val _unlinkStateFlow = MutableStateFlow<UnlinkState>(UnlinkState.Init)
        val unlinkStateFlow: StateFlow<UnlinkState> get() = _unlinkStateFlow

        /**
         * #########################
         * #### C O M M O N
         * #########################
         * */

        fun getDebugHashKey(): String? {
            return CommonHelper.getDebugHashKey(activity)
        }

        /**
         * #########################
         * #### K A K A O
         * #########################
         * */

        fun doKakaoLogin(): StateFlow<LoginState<String>> {
            kakaoLoginHelper.doKakaoLogin(_loginStateFlow)
            return loginStateFlow
        }

        fun doKakaoLogout(): StateFlow<LogoutState> {
            kakaoLoginHelper.doKakaoLogout(_logoutStateFlow)
            return logoutStateFlow
        }

        fun doKakaoUnlink(): StateFlow<UnlinkState> {
            kakaoLoginHelper.doUnlink(_unlinkStateFlow)
            return unlinkStateFlow
        }

    }
}
