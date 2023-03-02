package com.techholding.featureflagsample.optimizely

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.optimizely.ab.android.sdk.OptimizelyClient
import com.optimizely.ab.android.sdk.OptimizelyManager
import com.techholding.featureflagsample.databinding.ActivityOptimizelyBinding
import java.util.concurrent.TimeUnit

class OptimizelyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOptimizelyBinding
    private var optimizelyManager: OptimizelyManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptimizelyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Build a manager
        optimizelyManager = OptimizelyManager.builder().withSDKKey("3MZQGR7v8ARXPve5wqucD")
            .withEventDispatchInterval(60L, TimeUnit.SECONDS)
            .withDatafileDownloadInterval(15L, TimeUnit.MINUTES)
            .build(applicationContext)

        // Or, instantiate it asynchronously with a callback
        optimizelyManager?.initialize(this, null) { client: OptimizelyClient ->
            // flag decision
            val user = client.createUserContext("demoUser")
            val decision = user.decide("demo_flag")
            binding.txtFlagStatus.text = "${decision.enabled}"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        optimizelyManager = null
    }
}