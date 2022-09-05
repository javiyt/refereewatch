package yt.javi.refereewatch

import android.app.Activity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Vibrator
import android.view.View
import android.widget.ToggleButton
import androidx.media.app.NotificationCompat
import yt.javi.refereewatch.databinding.ActivityRefereeTimerBinding
import java.time.Duration.ofMinutes
import java.time.Duration.ofSeconds


class RefereeTimerActivity : Activity() {
    private lateinit var binding: ActivityRefereeTimerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRefereeTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val playClockShortButton = findViewById<ToggleButton>(R.id.playClockShort)
        val playClockLongButton = findViewById<ToggleButton>(R.id.playClockLong)
        val timeOutButton = findViewById<ToggleButton>(R.id.timeOutClock)

        val playClockShortCountDown = RefereeCountDown(
            playClockShortButton,
            ofSeconds(25).toMillis(),
            1000
        )
        val playClockLongCountDown = RefereeCountDown(
            playClockLongButton,
            ofSeconds(40).toMillis(),
            1000
        )
        val timeOutCountDown = RefereeCountDown(
            timeOutButton,
            ofMinutes(1).toMillis(),
            1000
        )

        playClockShortButton.setOnClickListener {
            playClockShortCountDown.start()
            playClockLongCountDown.cancel()
            timeOutCountDown.cancel()

            playClockLongButton.isChecked = false
            timeOutButton.isChecked = false
        }

        playClockLongButton.setOnClickListener {
            playClockShortCountDown.cancel()
            playClockLongCountDown.start()
            timeOutCountDown.cancel()

            playClockShortButton.isChecked = false
            timeOutButton.isChecked = false
        }

        timeOutButton.setOnClickListener {
            playClockShortCountDown.cancel()
            playClockLongCountDown.cancel()
            timeOutCountDown.start()

            playClockShortButton.isChecked = false
            playClockLongButton.isChecked = false
        }
    }

    class RefereeCountDown(
        private val button: View,
        startTime: Long,
        interval: Long
    ) : CountDownTimer(startTime, interval) {
        override fun onTick(millisUntilFinished: Long) {
            (button as ToggleButton).text = "${(millisUntilFinished / 1000)}s"
        }

        override fun onFinish() {
            (button as ToggleButton).toggle()
        }
    }
}