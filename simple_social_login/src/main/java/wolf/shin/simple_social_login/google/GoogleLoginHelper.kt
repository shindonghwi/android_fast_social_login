package wolf.shin.simple_social_login.google

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import wolf.shin.simple_social_login.interfaces.IActivityResultCallback
import wolf.shin.simple_social_login.model.ActivityCallbackState
import wolf.shin.simple_social_login.model.LoginState
import wolf.shin.simple_social_login.model.LogoutState
import wolf.shin.simple_social_login.model.UnlinkState


class GoogleLoginHelper(
    private val context: Context
) : IGoogleLoginApi {

    /** 구글 로그인 */
    override fun doGoogleLogin(loginFlow: MutableStateFlow<LoginState<String>>) {
        GoogleSignInActivity.startActivityForResult(context, object : IActivityResultCallback {
            override fun <T> callbackState(state: ActivityCallbackState<T>) {

                when(state){
                    is ActivityCallbackState.Init -> {
                        loginFlow.value = LoginState.Init
                    }
                    is ActivityCallbackState.Loading -> {
                        loginFlow.value = LoginState.Loading
                    }
                    is ActivityCallbackState.Success -> {
                        loginFlow.value = LoginState.Success(token = state.token)
                    }
                    is ActivityCallbackState.Error -> {
                        loginFlow.value = LoginState.Error(message = state.message)
                    }
                }
            }

        })
    }


    /** 구글 로그아웃 */
    override fun doGoogleLogout(logoutFlow: MutableStateFlow<LogoutState>) {
        TODO("Not yet implemented")
    }

    /** 구글 연결해제 */
    override fun doUnlink(unlinkFlow: MutableStateFlow<UnlinkState>) {
        TODO("Not yet implemented")
    }
}
