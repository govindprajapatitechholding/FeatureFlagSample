package com.techholding.featureflagsample.apptimize

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.apptimize.Apptimize
import com.techholding.featureflagsample.databinding.ActivityApptimizeBinding

class ApptimizeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityApptimizeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApptimizeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO Initialise this SDK in application class. Currently Apptimize is initialise at MainActivity for just testing purpose

        if (Apptimize.isFeatureFlagOn("new_feature_flag_variable")) {
            // ON
            Log.e("================", "*********ON*************")

            binding.txtFlagStatus.text = "Flag Status : ON"
        } else {
            // OFF
            Log.e("================", "*********OFF*************")
            binding.txtFlagStatus.text = "Flag Status : OFF"
        }
    }
}