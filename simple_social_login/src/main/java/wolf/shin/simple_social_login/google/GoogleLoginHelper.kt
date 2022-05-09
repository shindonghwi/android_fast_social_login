package wolf.shin.simple_social_login.google

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.flow.MutableStateFlow
import wolf.shin.simple_social_login.model.LoginState
import wolf.shin.simple_social_login.model.LogoutState
import wolf.shin.simple_social_login.model.OnActivityResultListener
import wolf.shin.simple_social_login.model.UnlinkState


class GoogleLoginHelper(
    private val context: Context
) : IGoogleLoginApi {

    private var gso: GoogleSignInOptions? = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()

    private val googleSignInClient = GoogleSignIn.getClient(context, gso!!)

    /** 구글 로그인 */
    override fun doGoogleLogin(loginFlow: MutableStateFlow<LoginState<String>>) {
        GoogleSignInActivity.launch(context, googleSignInClient.signInIntent, object : OnActivityResultListener {
            override fun onActivityResult(resultCode: Int, intent: Intent?) {

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
