package newsapp.sonia.com.cfnewsapp.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

class Utils {

    companion object {

        fun showLogE(TAG: String, message: String) {
            if (Constants.DEBUG) {
                Log.e(TAG, message)
            }
        }

        fun showToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}