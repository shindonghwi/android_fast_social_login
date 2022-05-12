package wolf.shin.simple_social_login

import android.content.Context
import kotlinx.coroutines.flow.StateFlow
import wolf.shin.simple_social_login.common.CommonHelper
import wolf.shin.simple_social_login.google.GoogleLoginHelper
import wolf.shin.simple_social_login.google.IGoogle
import wolf.shin.simple_social_login.kakao.IKakao
import wolf.shin.simple_social_login.kakao.KakaoLoginHelper
import wolf.shin.simple_social_login.model.LoginState
import wolf.shin.simple_social_login.model.LogoutState
import wolf.shin.simple_social_login.model.UnlinkState


class SimpleSocialLoginSDK {

    class Builder(
        private val context: Context
    ) : IKakao, IGoogle {

        private val kakaoLoginHelper: KakaoLoginHelper = KakaoLoginHelper(context)
        private val googleLoginHelper: GoogleLoginHelper = GoogleLoginHelper(context)


        /**
         * #########################
         * #### C O M M O N
         * #########################
         * */

        fun getDebugHashKey(): String? {
            return CommonHelper.getDebugHashKey(context)
        }

        /**
         * #########################
         * #### K A K A O
         * #########################
         * */

        val kakaoState = kakaoLoginHelper.kakaoFlowData

        override fun doKakaoLogin(): StateFlow<LoginState<String>> {
            return kakaoLoginHelper.run {
                doKakaoLogin()
                kakaoFlowData.loginFlow
            }
        }

        override fun doKakaoLogout(): StateFlow<LogoutState> {
            return kakaoLoginHelper.run {
                doKakaoLogout()
                kakaoFlowData.logoutFlow
            }
        }

        override fun doKakaoUnlink(): StateFlow<UnlinkState> {
            return kakaoLoginHelper.run {
                doKakaoUnlink()
                kakaoFlowData.unlinkFlow
            }
        }

        /**
         * #########################
         * #### G O O G L E
         * #########################
         * */

        val googleState = googleLoginHelper.googleFlowData

        override fun doGoogleLogin(): StateFlow<LoginState<String>> {
            return googleLoginHelper.run {
                doGoogleLogin()
                googleFlowData.loginFlow
            }
        }

        override fun doGoogleLogout(): StateFlow<LogoutState> {
            return googleLoginHelper.run {
                doGoogleLogout()
                googleFlowData.logoutFlow
            }
        }

        override fun doGoogleUnlink(): StateFlow<UnlinkState> {
            return googleLoginHelper.run {
                doGoogleUnlink()
                googleFlowData.unlinkFlow
            }
        }

    }


    companion object {
        val TAG = "WOLF_LOG"
    }
}
