package wolf.shin.simple_social_login.google

import android.app.Activity
import android.content.Intent
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.flow.MutableStateFlow
import wolf.shin.simple_social_login.model.LoginState
import wolf.shin.simple_social_login.model.LogoutState
import wolf.shin.simple_social_login.model.UnlinkState


class GoogleLoginHelper(
    private val activity: Activity
) : IGoogleLoginApi {

    private var gso: GoogleSignInOptions? = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()

    private val googleSignInClient = GoogleSignIn.getClient(activity.applicationContext, gso!!)


    /** 구글 로그인 */
    override fun doGoogleLogin(loginFlow: MutableStateFlow<LoginState<String>>) {
        val signInIntent: Intent = googleSignInClient.signInIntent
        startActivityForResult(activity, signInIntent, 100, null)
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
