-keep class wolf.shin.simple_social_login.model.LoginState

# Kakao Login
-keep class com.kakao.sdk.**.model.* { <fields>; }
-keep class * extends com.google.gson.TypeAdapter