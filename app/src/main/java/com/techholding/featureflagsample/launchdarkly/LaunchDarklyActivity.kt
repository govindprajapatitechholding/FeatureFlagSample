package com.techholding.featureflagsample.launchdarkly

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.launchdarkly.sdk.LDContext
import com.launchdarkly.sdk.android.LDClient
import com.launchdarkly.sdk.android.LDConfig
import com.techholding.featureflagsample.databinding.ActivityLaunchDarklyBinding
import java.util.concurrent.ExecutionException

class LaunchDarklyActivity : AppCompatActivity() {
    private val TAG = "LaunchDarklyActivity"
    private val FLAG_KEY = "app-open"
    lateinit var binding: ActivityLaunchDarklyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchDarklyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the LDClient object
        val ldConfig = LDConfig.Builder()
            .mobileKey("mob-9a754bfd-0fd1-41bc-a1b4-3a8ce31ee7bb")
            .build()

        // Set up the evaluation context. This context should appear on your LaunchDarkly contexts
        // dashboard soon after you run the demo.
        val context = LDContext.builder("example-user-key")
            .name("Sandy")
            .build()

        val client = LDClient.init(application, ldConfig, context)
        try {
            val ldClient = client.get()
            val flagValue = ldClient.boolVariation(FLAG_KEY, false)
            Log.i(
                TAG,
                String.format("Feature flag [%s] is [%s] for this context", FLAG_KEY, flagValue)
            )
            binding.txtFlagStatus.text = String.format("Feature flag [%s] is [%s] for this context", FLAG_KEY, flagValue)
            ldClient.flush()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}