package com.techholding.featureflagsample

import android.app.Application
import com.apptimize.Apptimize
import com.posthog.android.PostHog
import com.posthog.android.Properties
import java.util.concurrent.TimeUnit

class MainApp : Application() {

    companion object {
        private val POSTHOG_API_KEY = "phc_4QWGeB86GbiKhmZjNQL5Q6FFXiVpTmA273tPksIxthh"
    }

    override fun onCreate() {
        super.onCreate()

        //Initialise Apptimize SDK
        Apptimize.setup(this, "AXPFCnet3J6A3NV3gcgYTmnB2rjRp7E")

        // Initialize a new instance of the PostHog client.
        val builder: PostHog.Builder =
            PostHog.Builder(this, POSTHOG_API_KEY, "https://app.posthog.com")
                .captureApplicationLifecycleEvents().flushQueueSize(20)
                .flushInterval(15, TimeUnit.SECONDS).recordScreenViews()

        // Set the initialized instance as a globally accessible instance.
        PostHog.setSingletonInstance(builder.build())

        PostHog.with(this).identify(
            "123456", Properties().putValue("name", "My Name").putValue("email", "user@posthog.com")
        )
    }
}