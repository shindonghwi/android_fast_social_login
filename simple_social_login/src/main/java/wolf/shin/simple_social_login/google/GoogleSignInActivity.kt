package wolf.shin.simple_social_login.google

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import wolf.shin.simple_social_login.model.ActivityRequest
import wolf.shin.simple_social_login.model.OnActivityResultListener

class GoogleSignInActivity : AppCompatActivity() {

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setFinishOnTouchOutside(false)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            Log.d("asdasd","$result")
            activityRequest?.listener?.onActivityResult(result.resultCode, result.data)
            finish()
        }

        resultLauncher.launch(activityRequest?.intent)
    }



    companion object {
        private var activityRequest: ActivityRequest? = null

        fun launch(context: Context, signInIntent: Intent, listener: OnActivityResultListener) {
            activityRequest = ActivityRequest(signInIntent, listener)
            context.startActivity(Intent(context, GoogleSignInActivity::class.java))
        }
    }
}