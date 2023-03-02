package com.techholding.featureflagsample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apptimize.Apptimize
import com.techholding.featureflagsample.apptimize.ApptimizeActivity
import com.techholding.featureflagsample.config_cat.ConfigCatActivity
import com.techholding.featureflagsample.databinding.ActivityMainBinding
import com.techholding.featureflagsample.firebase_config.FirebaseConfigActivity
import com.techholding.featureflagsample.flagsmith.FlagSmithActivity
import com.techholding.featureflagsample.launchdarkly.LaunchDarklyActivity
import com.techholding.featureflagsample.posthog.PostHogActivity
import com.techholding.featureflagsample.split.SplitActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLaunchDarkly.setOnClickListener {
            val intent = Intent(this, LaunchDarklyActivity::class.java)
            startActivity(intent)
        }

        binding.btnSplit.setOnClickListener {
            val intent = Intent(this, SplitActivity::class.java)
            startActivity(intent)
        }

        binding.btnFirebaseConfig.setOnClickListener {
            val intent = Intent(this, FirebaseConfigActivity::class.java)
            startActivity(intent)
        }

        binding.btnConfigCat.setOnClickListener {
            val intent = Intent(this, ConfigCatActivity::class.java)
            startActivity(intent)
        }

        binding.btnFlagSmith.setOnClickListener {
            val intent = Intent(this, FlagSmithActivity::class.java)
            startActivity(intent)
        }

        binding.btnApptimize.setOnClickListener {
            val intent = Intent(this, ApptimizeActivity::class.java)
            startActivity(intent)
        }

        binding.btnPostHog.setOnClickListener {
            val intent = Intent(this, PostHogActivity::class.java)
            startActivity(intent)
        }
    }
}