package wolf.shin.simplesociallogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking
import wolf.shin.simple_social_login.SimpleSocialLoginSDK
import wolf.shin.simple_social_login.model.LoginState
import wolf.shin.simplesociallogin.ui.theme.SimpleSocialLoginTheme

class MainActivity : ComponentActivity() {

    private val simpleSocialLoginSDK by lazy { SimpleSocialLoginSDK.Builder(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleSocialLoginTheme {

                val loginVM = viewModel<SimpleLoginViewModel>()

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(all = 24.dp)
                                .background(Color.Yellow)
                                .padding(all = 12.dp)
                                .clickable {
                                    loginVM.kakaoLoginState = simpleSocialLoginSDK.doKakaoLogin()
                                },
                            text = "카카오로그인",
                            style = MaterialTheme.typography.h4,
                            color = Color.Black
                        )

                        Text(
                            modifier = Modifier
                                .background(Color.Yellow)
                                .clickable {
                                    runBlocking {
                                        simpleSocialLoginSDK.doKakaoUnlink()
                                    }
                                },
                            text = "연결해제",
                            style = MaterialTheme.typography.h4,
                            color = Color.Black
                        )
                    }
                    KakaoLoginStateView(loginVM.kakaoLoginState)
                }
            }
        }
    }
}

@Composable
private fun KakaoLoginStateView(kakaoLoginState: StateFlow<LoginState>) {
    with(kakaoLoginState.collectAsState().value) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "로그인상태: ${this}",
            style = MaterialTheme.typography.h5,
            color = Color.Black
        )
    }

}
