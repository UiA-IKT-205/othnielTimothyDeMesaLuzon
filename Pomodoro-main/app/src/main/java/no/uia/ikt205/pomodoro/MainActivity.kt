package no.uia.ikt205.pomodoro

import kotlin.coroutines.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import android.widget.EditText
import no.uia.ikt205.pomodoro.util.millisecondsToDescriptiveTime

class MainActivity : AppCompatActivity() {

    lateinit var timer:CountDownTimer
    lateinit var pause:CountDownTimer
    lateinit var startButton:Button
    lateinit var countdownDisplay:TextView
    lateinit var countdownText:TextView
    lateinit var pauseText:TextView
    lateinit var repText:EditText

    var realTime: Long = 15L // Used to set countdown value
    var pauseTime: Long = 15L // Used to set pause value
    var flag = false // Used to check if a timer is currently ongoing
    var repNumber = 0
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
                countdownText.text = getString(R.string.countdown_text, seek.progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // nothing yet
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // nothing yet
                Toast.makeText(this@MainActivity, "Time set: " + seek.progress +
                        " minutes", Toast.LENGTH_SHORT).show()

                changeCountDown(seek.progress)
            }
        })

        val pause = findViewById<SeekBar>(R.id.pauseSeek)
        pause?.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?,
                                           progress: Int, fromUser: Boolean) {
                pauseText.text = getString(R.string.pause_text, pause.progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // nothing yet
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Toast.makeText(this@MainActivity, "Break set: " + pause.progress +
                        " minutes", Toast.LENGTH_SHORT).show()

                changePause(pause.progress)
            }
        })

        repText = findViewById<EditText>(R.id.repText)
        repText.setOnClickListener(){

            val text = repText.text
            repNumber = repText.text.toString().toInt()
            Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
        }


        countdownDisplay = findViewById<TextView>(R.id.countDownView)
        countdownText = findViewById<TextView>(R.id.timeSeekText)
        pauseText = findViewById<TextView>(R.id.pauseSeekText)


    }

    fun startCountDown(){

        if(flag){
            timer.cancel()
            pause.cancel()
        }

        flag = true

        timer = object : CountDownTimer(realTime,timeTicks) {
            override fun onFinish() {

                Toast.makeText(this@MainActivity,"Arbeidsøkt er ferdig",
                    Toast.LENGTH_SHORT).show()
                pause.start()
            }

            override fun onTick(millisUntilFinished: Long) {
                updateCountDownDisplay(millisUntilFinished)
            }
        }

        pause = object : CountDownTimer(pauseTime, timeTicks) {
            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Pause er ferdig",
                    Toast.LENGTH_SHORT).show()
                if(repNumber > 0){
                    timer.start()
                    repNumber -= 1
                }
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
        realTime = timeTicks * 60 * Multiplier
    }

    fun changePause(Multiplier: Int){
        pauseTime = timeTicks * 60 * Multiplier
    }

}