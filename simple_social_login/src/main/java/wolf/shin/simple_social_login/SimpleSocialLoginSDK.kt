package wolf.shin.simple_social_login

import android.content.Context
import android.os.StatFs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import wolf.shin.simple_social_login.model.LoginState

class SimpleSocialLoginSDK{

    class Builder(private val context: Context) {

        // 로그인 상태를 관찰 할 수 있는 Flow 생성
        private val _loginStateFlow = MutableStateFlow<LoginState>(LoginState.Init)
        val loginStateFlow: StateFlow<LoginState> get() = _loginStateFlow

    }
}