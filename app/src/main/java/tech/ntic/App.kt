package tech.ntic

import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Bundle
import com.example.ntictask.di.component.DIComponent
import com.example.ntictask.di.component.DaggerDIComponent
import tech.ntic.di.moduls.AppModule
import tech.ntic.utils.AppConstants.Companion.ROOT_DIRECTORY_NAME
import tech.ntic.utils.createDirectory

@Suppress("DEPRECATION")
class App : Application() {

    lateinit var di : DIComponent

    override fun onCreate() {
        super.onCreate()

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {
            }

            override fun onActivityResumed(activity: Activity?) {
            }

            override fun onActivityStarted(activity: Activity?) {
            }

            override fun onActivityDestroyed(activity: Activity?) {
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            }

            override fun onActivityStopped(activity: Activity?) {
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        })

        createDirectory(ROOT_DIRECTORY_NAME)

        di = DaggerDIComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}