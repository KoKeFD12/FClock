package com.tint.fclock

import android.app.AlarmManager
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.core.app.AlarmManagerCompat
import androidx.core.app.AlarmManagerCompat.setAlarmClock
import androidx.fragment.app.DialogFragment
import java.util.Calendar
import kotlin.math.min

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it.
        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        // Do something with the time the user picks.

        // TODO: Finish the alarms settings.
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        val time:Int = (((hour*60)+minute)*60)*1000
//        var info:AlarmManager.AlarmClockInfo = AlarmManager.AlarmClockInfo(time.toLong(), Intent(this, ))
//        setAlarmClock(info);
    }
}