package com.techholding.featureflagsample.split

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.techholding.featureflagsample.databinding.ActivitySplitBinding
import io.split.android.client.SplitClient
import io.split.android.client.SplitClientConfig
import io.split.android.client.SplitFactoryBuilder
import io.split.android.client.api.Key
import io.split.android.client.events.SplitEvent
import io.split.android.client.events.SplitEventTask


class SplitActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apikey = "e2lqbsr7iqu3s43i41iam47l3l53c4dvamvd"
        val config = SplitClientConfig.builder().build()

        // User Key
        val matchingKey = "fwwsffwewefew"
        val key = Key(matchingKey)

        var splitClient: SplitClient? = null
        try {
            // Create a Split factory
            val splitFactory = SplitFactoryBuilder.build(
                apikey, key, config, applicationContext
            )
            // Get Split Client instance
            splitClient = splitFactory.client()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        splitClient?.on(SplitEvent.SDK_READY, object : SplitEventTask() {
            override fun onPostExecution(client: SplitClient) {
                Log.i("TAG", "Do some NON UI work")
                when (client.getTreatment("test_split")) {
                    "on" -> {
                        Log.i("TAG", "I'm ON    ")
                    }
                    "off" -> {
                        Log.i("TAG", "I'm OFF")
                    }
                    else -> {
                        Log.i("TAG", "CONTROL was returned, there was an error")
                    }
                }
            }

            override fun onPostExecutionView(client: SplitClient) {
                Log.i("TAG", "Do some UI work")
                when (client.getTreatment("test_split")) {
                    "on" -> {
                        binding.txtFlagStatus.text = "I'm ON"
                    }
                    "off" -> {
                        binding.txtFlagStatus.text = "I'm OFF"
                    }
                    else -> {
                        binding.txtFlagStatus.text = "CONTROL was returned, there was an error"
                    }
                }
            }
        })
    }
}