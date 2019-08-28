package tech.ntic.utils

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.NonNull
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import android.location.LocationManager
import android.os.Environment
import tech.ntic.R
import tech.ntic.utils.AppConstants.Companion.CHANNEL_ID
import tech.ntic.utils.AppConstants.Companion.NOTIFICATION_ID
import tech.ntic.utils.AppConstants.Companion.VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
import tech.ntic.utils.AppConstants.Companion.VERBOSE_NOTIFICATION_CHANNEL_NAME
import java.io.File


fun isNetworkConnected(context: Context): Boolean {
    val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    if (manager != null) {
        val networkInfo = manager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected && networkInfo.isAvailable) {
            return true
        }
    }
    return false
}

fun hideSoftInput(activity: Activity) {
    var view = activity.currentFocus
    if (view == null) view = View(activity)
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun showSoftInput(edit: EditText, context: Context) {
    edit.isFocusable = true
    edit.isFocusableInTouchMode = true
    edit.requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(edit, 0)
}

fun Snackbar.customMake(
    @NonNull view: View, @NonNull text: CharSequence, actionText: CharSequence,
    duration: Int = Snackbar.LENGTH_LONG, listener: View.OnClickListener
): Snackbar {

    Snackbar.make(view, text, duration).setAction(actionText) {
        listener.onClick(view)
        this.dismiss()
    }.setActionTextColor(ContextCompat.getColor(context, R.color.md_yellow_500)).show()

    return this
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun displayNotification(task: String, desc: String, context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        val name = VERBOSE_NOTIFICATION_CHANNEL_NAME
        val description = VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance)
        channel.description = description

        // Add the channel
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        notificationManager?.createNotificationChannel(channel)
    }

    // Create the notification
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle(task)
        .setContentText(desc)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setVibrate(LongArray(0))

    // Show the notification
    NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())


}


fun isLocationServiceEnabled(context: Context): Boolean {
    var locationManager: LocationManager? = null
    var gpsEnabled = false
    var networkEnabled = false

    if (locationManager == null)
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
    try {
        gpsEnabled = locationManager.run { this!!.isProviderEnabled(LocationManager.GPS_PROVIDER) }
    } catch (ex: Exception) {
        //do nothing...
    }

    try {
        networkEnabled = locationManager.run { this!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER) }
    } catch (ex: Exception) {
        //do nothing...
    }

    return gpsEnabled || networkEnabled

}

fun createDirectory(directoryName: String) {
    if (!isDirectoryExists(directoryName)) {
        val file = File(Environment.getExternalStorageDirectory().toString(), directoryName)
        file.mkdir()
    }
}

fun isDirectoryExists(directoryName: String): Boolean {
    val file = File(Environment.getExternalStorageDirectory().toString() + "/" + directoryName)
    return file.isDirectory && file.exists()
}