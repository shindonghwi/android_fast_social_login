package wolf.shin.simplesociallogin

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import wolf.shin.simple_social_login.model.LoginState

class SimpleLoginViewModel: ViewModel() {

    var kakaoLoginState: StateFlow<LoginState> = MutableStateFlow(LoginState.Init)


}