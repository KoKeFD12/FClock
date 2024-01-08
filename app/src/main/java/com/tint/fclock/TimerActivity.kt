package com.tint.fclock

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.core.app.NotificationCompat
import com.tint.fclock.databinding.ActivityTimerBinding
import java.util.Locale

class TimerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding
    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupMinutesNP()
        setupSecondsNP()

        binding.playTimerBtn.setOnClickListener { startCountdown() }
        binding.stopTimerBtn.setOnClickListener { stopCountdown() }
    }

    private fun stopCountdown() {
        timer.cancel()
        binding.countDownTV.visibility = View.INVISIBLE
        binding.timerSeparatorTV.visibility = View.VISIBLE
        binding.minutesNP.visibility = View.VISIBLE
        binding.secondsNP.visibility = View.VISIBLE
        binding.stopTimerBtn.isEnabled = false
        binding.playTimerBtn.isEnabled = true
    }

    private fun startCountdown() {
        binding.playTimerBtn.isEnabled = false
        binding.stopTimerBtn.isEnabled = true

        var selectedMinutes = binding.minutesNP.value
        var selectedSeconds = binding.secondsNP.value

        var selectedMilis = (selectedMinutes * 60000) + (selectedSeconds * 1000)

        binding.countDownTV.visibility = View.VISIBLE
        binding.timerSeparatorTV.visibility = View.INVISIBLE
        binding.minutesNP.visibility = View.INVISIBLE
        binding.secondsNP.visibility = View.INVISIBLE

        timer = object : CountDownTimer(selectedMilis.toLong(), 1) {

            override fun onTick(remaining: Long) {
                var minutes = remaining / 60000
                var seconds = (remaining/1000)%60
                binding.countDownTV.text = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                binding.countDownTV.text = "00:00"
                binding.countDownTV.visibility = View.INVISIBLE
                binding.timerSeparatorTV.visibility = View.VISIBLE
                binding.minutesNP.visibility = View.VISIBLE
                binding.secondsNP.visibility = View.VISIBLE
                binding.stopTimerBtn.isEnabled = false
                binding.playTimerBtn.isEnabled = true
                sendNotification()
            }

        }

        timer.start()
    }

    private fun setupSecondsNP() {
        binding.secondsNP.minValue = 1
        binding.secondsNP.maxValue = 59
        binding.secondsNP.wrapSelectorWheel = true
    }

    private fun setupMinutesNP() {
        binding.minutesNP.minValue = 0
        binding.minutesNP.maxValue = 59
        binding.minutesNP.wrapSelectorWheel = true
    }

    fun sendNotification() {

        // Notification channel values
        val channelId = "timer_notifications"
        val channelName = "Timer Notifications"
        val channelImportance = NotificationManager.IMPORTANCE_DEFAULT

        // Notification channel configuration
        val notificationChannel = NotificationChannel(channelId, channelName, channelImportance)
        notificationChannel.description = "Used as the main channel for the timer notifications." // Adding a description for the channel

        // Notification manager build
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // Channel build using the notification manager
        notificationManager.createNotificationChannel(notificationChannel)
        // Notification build
        val notification = NotificationCompat.Builder(this.baseContext, channelId)
            .setSmallIcon(R.drawable.baseline_circle_notifications_24)
            .setContentTitle("FClock")
            .setContentText("Timer has finished")
            .build()

        // Notification usage
        notificationManager.notify(1, notification)
    }

}