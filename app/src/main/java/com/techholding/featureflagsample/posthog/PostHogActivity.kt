package com.techholding.featureflagsample.posthog

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.posthog.android.PostHog
import com.techholding.featureflagsample.databinding.ActivityPostHogBinding
import kotlinx.coroutines.runBlocking


class PostHogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostHogBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostHogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        runBlocking {
            PostHog.with(this@PostHogActivity).reloadFeatureFlags()
        }

        binding.txtFlagStatus.text =
            "App Open Flag Status: ${PostHog.with(this).isFeatureEnabled("appOpenDebug")}"
    }


}