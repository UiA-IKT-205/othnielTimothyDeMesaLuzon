package no.uia.ikt205.pomodoro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
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
            startCountDown(it)
        }
        add30 = findViewById<Button>(R.id.add30Button)
        add30.setOnClickListener(){
            changeCountDown(30)
        }

        add60 = findViewById<Button>(R.id.add60Button)
        add60.setOnClickListener(){
            changeCountDown(60)
        }
        add90 = findViewById<Button>(R.id.add90Button)
        add90.setOnClickListener(){
            changeCountDown(90)
        }
        add120 = findViewById<Button>(R.id.add120Button)
        add120.setOnClickListener(){
            changeCountDown(120)
        }

        countdownDisplay = findViewById<TextView>(R.id.countDownView)

    }

    fun startCountDown(v: View){

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

    fun changeCountDown(Multiplier:Long){
        realTime = timeToCountDownInMs/5 * 60 * Multiplier
    }

}