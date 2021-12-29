package uz.infinityandro.taskguidebook.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import uz.infinityandro.taskguidebook.app.App

class InternetBroadCast : BroadcastReceiver() {
    private var listener: ((Boolean) -> Unit)? = null
    private var netListener:((Boolean)->Unit)?=null
    override fun onReceive(context: Context, intent: Intent) {

        if(isConnectedOrConnecting(App.instance)){
            netListener?.invoke(true)
        }else{
            netListener?.invoke(false)
        }
        when (intent.getIntExtra(
            WifiManager.EXTRA_WIFI_STATE,
            WifiManager.WIFI_STATE_UNKNOWN
        )) {
            WifiManager.WIFI_STATE_ENABLED -> {
                listener?.invoke(true)
            }
            WifiManager.WIFI_STATE_DISABLED -> {
                listener?.invoke(false)
            }
        }
    }

    fun setListener(f: (Boolean) -> Unit) {
        listener = f
    }

    fun setNetwork(a: (Boolean) -> Unit){
        netListener=a
    }

     fun isConnectedOrConnecting(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

}

