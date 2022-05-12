package wolf.shin.simplesociallogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import wolf.shin.simple_social_login.SimpleSocialLoginSDK
import wolf.shin.simplesociallogin.social_component.google.GoogleView
import wolf.shin.simplesociallogin.social_component.kakao.KakaoView
import wolf.shin.simplesociallogin.ui.theme.SimpleSocialLoginTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleSocialLoginTheme {

                val socialLoginVM: SimpleLoginViewModel = viewModel<SimpleLoginViewModel>().apply {
                    simpleSocialLoginSDK = SimpleSocialLoginSDK.Builder(this@MainActivity)
                }

                Scaffold(modifier = Modifier.padding(all = 16.dp)) {
                    Column {
                        KakaoView(socialLoginVM.simpleSocialLoginSDK)

                        GoogleView(socialLoginVM.simpleSocialLoginSDK)
                    }
                }


            }
        }
    }
}
