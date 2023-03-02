package com.techholding.featureflagsample.firebase_config

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.techholding.featureflagsample.BuildConfig
import com.techholding.featureflagsample.R
import com.techholding.featureflagsample.databinding.ActivityFirebaseConfigBinding

class FirebaseConfigActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirebaseConfigBinding

    private lateinit var remoteConfig: FirebaseRemoteConfig
    private lateinit var configSettings: FirebaseRemoteConfigSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirebaseConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRemoteConfig()
    }

    private fun initRemoteConfig() {
        remoteConfig = FirebaseRemoteConfig.getInstance()
        configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(if (BuildConfig.DEBUG) 0 else 3600)
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)  //You missed this line
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    remoteConfig.activate()
                    val updated = task.result
                    Log.d(
                        TAG,
                        "Config params updated: $updated. Fetch and activate succeeded"
                    ) // updated = false
                    Log.e(
                        "=========",
                        "Param : First Key [[${remoteConfig.getString("first_key")}]]"
                    )
                    binding.txtFlagStatus.text = "Param : First Key [[${remoteConfig.getString("first_key")}]]"
                } else {
                    Log.d(TAG, "Fetch failed")
                }
            }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}