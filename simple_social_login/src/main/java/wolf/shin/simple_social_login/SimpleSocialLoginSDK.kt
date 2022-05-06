package wolf.shin.simple_social_login

import android.app.Activity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import wolf.shin.simple_social_login.common.CommonHelper
import wolf.shin.simple_social_login.common.StateFlowData
import wolf.shin.simple_social_login.google.IGoogle
import wolf.shin.simple_social_login.kakao.IKakao
import wolf.shin.simple_social_login.kakao.KakaoLoginHelper
import wolf.shin.simple_social_login.model.LoginState
import wolf.shin.simple_social_login.model.LogoutState
import wolf.shin.simple_social_login.model.UnlinkState


class SimpleSocialLoginSDK {

    class Builder(
        private val activity: Activity
    ) : IKakao, IGoogle {
        private val kakaoLoginHelper: KakaoLoginHelper = KakaoLoginHelper(activity)

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

        val kakaoFlowData = StateFlowData(
            loginFlow = MutableStateFlow(LoginState.Init),
            logoutFlow = MutableStateFlow(LogoutState.Init),
            unlinkFlow = MutableStateFlow(UnlinkState.Init),
        )

        override fun doKakaoLogin(): StateFlow<LoginState<String>> {
            kakaoLoginHelper.doKakaoLogin(kakaoFlowData.loginFlow)
            return kakaoFlowData.loginFlow
        }

        override fun doKakaoLogout(): StateFlow<LogoutState> {
            kakaoLoginHelper.doKakaoLogout(kakaoFlowData.logoutFlow)
            return kakaoFlowData.logoutFlow
        }

        override fun doKakaoUnlink(): StateFlow<UnlinkState> {
            kakaoLoginHelper.doKakaoUnlink(kakaoFlowData.unlinkFlow)
            return kakaoFlowData.unlinkFlow
        }

        /**
         * #########################
         * #### G O O G L E
         * #########################
         * */

        val googleFlowData = StateFlowData(
            loginFlow = MutableStateFlow(LoginState.Init),
            logoutFlow = MutableStateFlow(LogoutState.Init),
            unlinkFlow = MutableStateFlow(UnlinkState.Init),
        )

        override fun doGoogleLogin(): StateFlow<LoginState<String>> {
//            kakaoLoginHelper.doKakaoLogin(_loginStateFlow)
            return googleFlowData.loginFlow
        }

        override fun doGoogleLogout(): StateFlow<LogoutState> {
//            kakaoLoginHelper.doKakaoLogout(_logoutStateFlow)
            return googleFlowData.logoutFlow
        }

        override fun doGoogleUnlink(): StateFlow<UnlinkState> {
//            kakaoLoginHelper.doKakaoUnlink(_unlinkStateFlow)
            return googleFlowData.unlinkFlow
        }

    }
}
