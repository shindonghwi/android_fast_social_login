package wolf.shin.simplesociallogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import wolf.shin.simple_social_login.SimpleSocialLoginSDK
import wolf.shin.simplesociallogin.ui.theme.SimpleSocialLoginTheme

class MainActivity : ComponentActivity() {

    private val simpleSocialLoginSDK by lazy { SimpleSocialLoginSDK.Builder(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleSocialLoginTheme {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
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

                }
            }
        }
    }
}