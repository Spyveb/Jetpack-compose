package sumit5ue.MyVity.Util

import android.content.Context
import android.widget.Toast
import com.kaopiz.kprogresshud.KProgressHUD


var hud: KProgressHUD? = null

object Utility {

}

fun toast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}

fun showProgress(context: Context) {

    if (hud?.isShowing == true) {
        return
    }
    hud = KProgressHUD.create(context)
        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
        .setCancellable(true)
        .setAnimationSpeed(2)
        .setDimAmount(0.7f)
        .show();

}

fun dismissProgress() {
    if (hud != null) {
        hud?.dismiss()
    }
}
