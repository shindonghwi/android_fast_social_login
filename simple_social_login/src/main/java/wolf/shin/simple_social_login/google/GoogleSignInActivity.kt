package wolf.shin.simple_social_login.google

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import wolf.shin.simple_social_login.BuildConfig
import wolf.shin.simple_social_login.interfaces.IActivityResultCallback
import wolf.shin.simple_social_login.model.ActivityCallbackState

class GoogleSignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var oneTapClient: SignInClient
    private lateinit var signUpRequest: BeginSignInRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setFinishOnTouchOutside(false)

        auth = FirebaseAuth.getInstance()

        oneTapClient = Identity.getSignInClient(this)
        signUpRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID)
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()

        val launcher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {
            val credential = oneTapClient.getSignInCredentialFromIntent(it.data)
            val idToken = credential.googleIdToken

            when (it.resultCode) {
                RESULT_OK -> {
                    firebaseAuthWithGoogle(idToken = idToken.toString())
                }
                else -> {
                    iActivityResultCallback.callbackState(ActivityCallbackState.Error("Not found Credential Info !"))
                    finish()
                }
            }
        }

        oneTapClient.beginSignIn(signUpRequest)
            .addOnSuccessListener(this) { result ->
                try {
                    iActivityResultCallback.callbackState(ActivityCallbackState.Loading)
                    launcher.launch(IntentSenderRequest.Builder(result.pendingIntent.intentSender).build())
                } catch (e: IntentSender.SendIntentException) {
                    iActivityResultCallback.callbackState(ActivityCallbackState.Error(e.message))
                }
            }
            .addOnFailureListener(this) { e ->
                iActivityResultCallback.callbackState(ActivityCallbackState.Error(e.message))
                finish()
            }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    iActivityResultCallback.callbackState(ActivityCallbackState.Success(token = idToken))
                } else {
                    iActivityResultCallback.callbackState(ActivityCallbackState.Error("Firebase Connected Fail: ${task.exception?.message}"))
                }
            }
            .addOnFailureListener {
                iActivityResultCallback.callbackState(ActivityCallbackState.Error("Firebase Connected Fail: ${it.message}"))
            }
            .addOnCompleteListener {
                finish()
            }
    }

    companion object {
        private lateinit var iActivityResultCallback: IActivityResultCallback

        fun startActivityForResult(context: Context, iCallback: IActivityResultCallback) {
            iActivityResultCallback = iCallback.apply {
                callbackState(ActivityCallbackState.Init)
            }
            context.startActivity(Intent(context, GoogleSignInActivity::class.java))
        }
    }
}
