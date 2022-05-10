package wolf.shin.simple_social_login.google

import android.content.Context
import android.content.Intent
import kotlinx.coroutines.flow.MutableStateFlow
import wolf.shin.simple_social_login.model.LoginState
import wolf.shin.simple_social_login.model.LogoutState
import wolf.shin.simple_social_login.model.UnlinkState


class GoogleLoginHelper(
    private val context: Context
) : IGoogleLoginApi {

    /** 구글 로그인 */
    override fun doGoogleLogin(loginFlow: MutableStateFlow<LoginState<String>>) {
        val intent = Intent(context, GoogleSignInActivity::class.java)
        context.startActivity(intent)
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
