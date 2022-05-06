package wolf.shin.simplesociallogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import wolf.shin.simple_social_login.SimpleSocialLoginSDK
import wolf.shin.simplesociallogin.social_component.kakao.KakaoButton
import wolf.shin.simplesociallogin.ui.theme.SimpleSocialLoginTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleSocialLoginTheme {

                val socialLoginVM: SimpleLoginViewModel = viewModel<SimpleLoginViewModel>().apply {
                    simpleSocialLoginSDK = SimpleSocialLoginSDK.Builder(this@MainActivity)
                }

                KakaoButton(socialLoginVM.simpleSocialLoginSDK)

            }
        }
    }
}
