package com.tint.fclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tint.fclock.databinding.ActivityStopwatchBinding
import kotlin.math.roundToInt

class StopwatchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStopwatchBinding
    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStopwatchBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_stopwatch)

        binding.playImageButton.setOnClickListener { startStopTimer() }
        binding.stopImageButton.setOnClickListener { stopStopTimer() }
        binding.pauseImageButton.setOnClickListener { pauseStopTimer() }

        serviceIntent = Intent(applicationContext, StopWatchTimerService::class.java)
        registerReceiver(updateTime, IntentFilter(StopWatchTimerService.TIMER_UPDATED))
    }

    private fun pauseStopTimer() {
        if (timerStarted) pauseTimer()
    }

    private fun stopStopTimer() {
        pauseTimer()
        time = 0.0
        binding.stopwatchTimeTextView.text = getTimeStringFromDouble(time)
    }

    private fun pauseTimer() {
        stopService(serviceIntent)
        timerStarted = false
    }

    private fun startStopTimer() {
        if (!timerStarted) startTimer()
    }

    private fun startTimer() {
        serviceIntent.putExtra(StopWatchTimerService.TIME_EXTRA, time)
        startService(serviceIntent)
        timerStarted = true
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(StopWatchTimerService.TIME_EXTRA, 0.0)
            binding.stopwatchTimeTextView.text = getTimeStringFromDouble(time)
        }
    }

    private fun getTimeStringFromDouble(time: Double): String {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hours: Int, minutes: Int, seconds: Int): String = String.format("%02d:%02d:%02d", hours, minutes, seconds)



}