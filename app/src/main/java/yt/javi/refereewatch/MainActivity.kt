package yt.javi.refereewatch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import yt.javi.refereewatch.databinding.ActivityMainBinding

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findViewById<Button>(R.id.r_timer).setOnClickListener {
            val intent = Intent(this, RefereeTimerActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.bj_timer).setOnClickListener {
            val intent = Intent(this, BackJudgeTimerActivity::class.java)
            startActivity(intent)
        }
    }
}