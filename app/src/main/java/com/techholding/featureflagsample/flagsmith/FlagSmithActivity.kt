package com.techholding.featureflagsample.flagsmith

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.flagsmith.Flagsmith
import com.techholding.featureflagsample.databinding.ActivityFlagSmithBinding

class FlagSmithActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFlagSmithBinding
    lateinit var flagsmith: Flagsmith

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlagSmithBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFlagSmith()

        flagsmith.getFeatureFlags { result ->
            result.fold(
                onSuccess = { flagList ->
                    Log.i("Flagsmith", "Current flags:")
                    flagList.forEach { Log.i("Flagsmith", "- ${it.feature.name} - enabled: ${it.enabled} value: ${it.featureStateValue ?: "not set"}") }
                },
                onFailure = { err ->
                    Log.e("Flagsmith", "Error getting feature flags", err)
                })
        }

//        Get Flag Object by featureId
//        To retrieve a feature flag boolean value by its name
        flagsmith.hasFeatureFlag(forFeatureId = "first_flag") { result ->
            val isEnabled = result.getOrDefault(true)
            Log.i("Flagsmith", "first_flag is enabled? $isEnabled")
            runOnUiThread {
                binding.txtFlagStatus.text = "first_flag is enabled? $isEnabled"
            }
        }
    }

    private fun initFlagSmith() {
        flagsmith = Flagsmith(environmentKey = "R7EdwC5WCAv5pstnDZW3fp", context = this)
    }
}