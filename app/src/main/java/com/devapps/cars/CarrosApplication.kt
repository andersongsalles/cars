package com.devapps.cars

import android.app.Application
import android.util.Log

class CarrosApplication : Application() {
    private val TAG = "CarrosApplication"
    // Chamado quando o Android criar o processo da aplicação
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        // Singleton da classe Application
        private var appInstance: CarrosApplication? = null

        fun getInstance(): CarrosApplication {
            if (appInstance == null) {
            }
            return appInstance!!
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "CarrosApplication.onTerminate()")
    }
}