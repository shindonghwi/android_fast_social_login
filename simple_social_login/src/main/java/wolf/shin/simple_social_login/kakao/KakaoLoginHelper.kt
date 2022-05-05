package wolf.shin.simple_social_login.kakao

import com.kakao.sdk.auth.model.OAuthToken

class KakaoLoginHelper {

    val kakaoCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            com.kakao.sdk.auth.AuthCodeHandlerActivity().intent
        // fail
        } else if (token != null) {
            // success
        }
    }

    // kakao Login
    fun kakaoLogin() {
    }

    // kakao Logout
    fun kakaoLogout() {

    }

}