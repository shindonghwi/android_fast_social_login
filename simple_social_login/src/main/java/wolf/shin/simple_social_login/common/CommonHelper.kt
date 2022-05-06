package wolf.shin.simple_social_login.common

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.util.Base64
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


object CommonHelper {

    /** Debug Hash Key 가져오기 */
    fun getDebugHashKey(context: Context): String? {

        with(context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val information =
                    packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES)
                val signatures = information.signingInfo.apkContentsSigners
                for (signature in signatures) {
                    val md: MessageDigest = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())
                    return String(Base64.encode(md.digest(), 0))
                }
            } else {
                var packageInfo: PackageInfo? = null
                try {
                    packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
                } catch (e: PackageManager.NameNotFoundException) {
                    e.printStackTrace()
                }
                for (signature in packageInfo!!.signatures) {
                    try {
                        val md = MessageDigest.getInstance("SHA")
                        md.update(signature.toByteArray())
                        return Base64.encodeToString(md.digest(), Base64.DEFAULT)
                    } catch (e: NoSuchAlgorithmException) {
                    }
                }
            }
        }

        return null
    }
}
