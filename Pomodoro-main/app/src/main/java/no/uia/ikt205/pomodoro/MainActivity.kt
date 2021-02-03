package no.uia.ikt205.pomodoro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import no.uia.ikt205.pomodoro.util.millisecondsToDescriptiveTime

class MainActivity : AppCompatActivity() {

    lateinit var timer:CountDownTimer
    lateinit var startButton:Button
    lateinit var add30:Button
    lateinit var add60:Button
    lateinit var add90:Button
    lateinit var add120:Button
    lateinit var countdownDisplay:TextView

    var realTime: Long = 0L
    var flag = false

    val timeToCountDownInMs = 5000L
    val timeTicks = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById<Button>(R.id.startCountdownButton)
        startButton.setOnClickListener(){
            startCountDown()
        }

        val seek = findViewById<SeekBar>(R.id.timeSeek)
        seek?.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?,
                                           progress: Int, fromUser: Boolean) {
                // nothing yet
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // nothing yet
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // nothing yet
                Toast.makeText(this@MainActivity, "Time set:g " + seek.progress + "seconds",
                Toast.LENGTH_SHORT).show()

                changeCountDown(seek.progress)
            }
        })


        countdownDisplay = findViewById<TextView>(R.id.countDownView)

    }

    fun startCountDown(){

        if(flag){
            timer.cancel()
        }

        flag = true

        timer = object : CountDownTimer(realTime,timeTicks) {
            override fun onFinish() {
                flag = false
                Toast.makeText(this@MainActivity,"Arbeidsøkt er ferdig", Toast.LENGTH_SHORT).show()
            }

            override fun onTick(millisUntilFinished: Long) {
               updateCountDownDisplay(millisUntilFinished)
            }
        }

        timer.start()
    }

    fun updateCountDownDisplay(timeInMs:Long){
        countdownDisplay.text = millisecondsToDescriptiveTime(timeInMs)
    }

    fun changeCountDown(Multiplier:Int){
        realTime = timeToCountDownInMs/5 * Multiplier
    }

}