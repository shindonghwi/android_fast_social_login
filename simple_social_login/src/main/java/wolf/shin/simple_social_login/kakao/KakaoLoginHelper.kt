package wolf.shin.simple_social_login.kakao

import android.app.Activity
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.AuthError
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import wolf.shin.simple_social_login.BuildConfig
import wolf.shin.simple_social_login.model.LoginState
import wolf.shin.simple_social_login.model.LogoutState
import wolf.shin.simple_social_login.model.UnlinkState


class KakaoLoginHelper(
    private val activity: Activity
) : iKakaoLoginApi {

    init {
        KakaoSdk.init(activity, BuildConfig.KAKAO_APP_KEY)
    }

    /** 카카오 로그인 */
    override suspend fun doKakaoLogin(loginFlow: MutableStateFlow<LoginState>) {
        loginFlow.emit(LoginState.Loading)
        with(activity) {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error: Throwable? ->
                    if (error != null) {
                        if (error is AuthError && error.statusCode == 302) {
                            runBlocking { loginFlow.emit(LoginState.Error(message = "$error")) }
                            return@loginWithKakaoTalk
                        }
                        return@loginWithKakaoTalk
                    }

                    if (token != null) {
                        runBlocking {  LoginState.Success(token = token.accessToken) }
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this) { token, error: Throwable? ->
                    if (error != null) {
                        runBlocking { loginFlow.emit(LoginState.Error(message = "$error")) }
                        return@loginWithKakaoAccount
                    }
                    if (token != null) {
                        runBlocking {  LoginState.Success(token = token.accessToken) }
                    }
                }
            }
        }
    }

    /** 카카오 로그아웃 */
    override suspend fun doKakaoLogout(logoutFlow: MutableStateFlow<LogoutState>) {
        logoutFlow.value = LogoutState.Loading
        UserApiClient.instance.logout { error: Throwable? ->
            if (error != null) {
                logoutFlow.value = LogoutState.Error(message = error.message)
                return@logout
            }
            logoutFlow.value = LogoutState.Success
        }
    }

    /** 카카오 연결해제 */
    override suspend fun doUnlink(unlinkFlow: MutableStateFlow<UnlinkState>) {
        unlinkFlow.value = UnlinkState.Loading
        UserApiClient.instance.unlink { error: Throwable? ->
            if (error != null) {
                unlinkFlow.value = UnlinkState.Error(message = error.message)
                return@unlink
            }
            unlinkFlow.value = UnlinkState.Success
        }
    }

}