package com.techholding.featureflagsample.config_cat

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.appcompat.app.AppCompatActivity
import com.configcat.ConfigCatClient
import com.techholding.featureflagsample.databinding.ActivityConfigCatBinding


class ConfigCatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfigCatBinding
    private lateinit var configCatClient: ConfigCatClient

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigCatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        configCatClient = ConfigCatClient.get("UxnbCFhiY0aNPrMIe12gfg/x-c40Tq1UkCc9nqD0Rr-Lg")
        val isMyFirstFeatureEnabled = configCatClient.getValue(Boolean::class.java, "isMyFirstFeatureEnabled", false)
        val demoBoolean = configCatClient.getValue(Boolean::class.java, "demoBoolean", false)

        binding.txtFlagStatus.text =
            "isMyFirstFeatureEnabled's value from ConfigCat: $isMyFirstFeatureEnabled \n demoBoolean's value from ConfigCat: $demoBoolean"

    }

    override fun onDestroy() {
        super.onDestroy()
        Handler(Looper.getMainLooper()).post {
            configCatClient.close()
        }
    }
}