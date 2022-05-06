package wolf.shin.simplesociallogin.social_component.kakao

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
import kotlinx.coroutines.flow.StateFlow
import wolf.shin.simple_social_login.SimpleSocialLoginSDK
import wolf.shin.simple_social_login.model.LoginState

@Composable
fun KakaoButton(simpleSocialLoginSDK: SimpleSocialLoginSDK.Builder) {
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
                        simpleSocialLoginSDK.doKakaoLogin()
                    },
                text = "카카오로그인",
                style = MaterialTheme.typography.h4,
                color = Color.Black
            )

            Text(
                modifier = Modifier
                    .background(Color.Yellow)
                    .clickable {
                        simpleSocialLoginSDK.doKakaoUnlink()
                    },
                text = "연결해제",
                style = MaterialTheme.typography.h4,
                color = Color.Black
            )
        }
        KakaoLoginStateView(simpleSocialLoginSDK.loginStateFlow)
    }
}

@Composable
private fun KakaoLoginStateView(loginStateFlow: StateFlow<LoginState<String>>) {
    with(loginStateFlow.collectAsState().value) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "로그인상태: ${
                when (this) {
                    is LoginState.Loading -> {
                        "Loading"
                    }
                    is LoginState.Success -> {
                        "Success: ${this.token}"
                    }
                    is LoginState.Error -> {
                        "Error: ${this.message}"
                    }
                    else -> {
                        "Init"
                    }
                }
            }",
            style = MaterialTheme.typography.body2,
            color = Color.Black
        )
    }

}
