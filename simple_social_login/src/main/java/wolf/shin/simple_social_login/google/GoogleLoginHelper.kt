package wolf.shin.simple_social_login.google

import android.content.Context
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import wolf.shin.simple_social_login.common.StateFlowData
import wolf.shin.simple_social_login.interfaces.IActivityResultCallback
import wolf.shin.simple_social_login.model.ActivityCallbackState
import wolf.shin.simple_social_login.model.LoginState
import wolf.shin.simple_social_login.model.LogoutState
import wolf.shin.simple_social_login.model.UnlinkState


class GoogleLoginHelper(
    private val context: Context
) : IGoogleLoginApi {

    val googleFlowData = StateFlowData(
        loginFlow = MutableStateFlow(LoginState.Init),
        logoutFlow = MutableStateFlow(LogoutState.Init),
        unlinkFlow = MutableStateFlow(UnlinkState.Init),
    )

    /** 구글 로그인 */
    override fun doGoogleLogin() {
        GoogleSignInActivity.startActivityForResult(context, object : IActivityResultCallback {
            override fun <T> callbackState(state: ActivityCallbackState<T>) {

                when (state) {
                    is ActivityCallbackState.Init -> {
                        googleFlowData.loginFlow.value = LoginState.Init
                    }
                    is ActivityCallbackState.Loading -> {
                        googleFlowData.loginFlow.value = LoginState.Loading
                    }
                    is ActivityCallbackState.Success -> {
                        googleFlowData.logoutFlow.value = LogoutState.Init
                        googleFlowData.unlinkFlow.value = UnlinkState.Init
                        googleFlowData.loginFlow.value = LoginState.Success(token = state.token)
                    }
                    is ActivityCallbackState.Error -> {
                        googleFlowData.loginFlow.value = LoginState.Error(message = state.message)
                    }
                }
            }

        })
    }


    /** 구글 로그아웃 */
    override fun doGoogleLogout() {
        Firebase.auth.signOut()
        googleFlowData.logoutFlow.value = LogoutState.Success
        googleFlowData.loginFlow.value = LoginState.Init
        googleFlowData.unlinkFlow.value = UnlinkState.Init
    }

    /** 구글 연결해제 */
    override fun doGoogleUnlink() {
        Firebase.auth.currentUser?.run {
            delete()
                .addOnSuccessListener {
                    googleFlowData.loginFlow.value = LoginState.Init
                    googleFlowData.logoutFlow.value = LogoutState.Init
                    googleFlowData.unlinkFlow.value = UnlinkState.Success
                }
                .addOnFailureListener {
                    googleFlowData.unlinkFlow.value = UnlinkState.Error(it.message)
                }
        }?: kotlin.run {
            googleFlowData.unlinkFlow.value = UnlinkState.Error("Firebase Current User Credential Not Found")
        }
    }
}
