package com.example.taskweek3

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var activeStateView = "onCreate"
    var landscapeCounter = 0
    var portraitCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //adding functionality to the button
        var subButton = findViewById<Button>(R.id.btn_AddFragment)
        subButton.setOnClickListener {
            var intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
        }
        //setting delay on the activity state
        var runner = Runnable {
            activityCycleDisplay("On Create")
        }
        var handler = Handler()


        handler.postDelayed(runner, 2000)

        saveOrientationInstance(savedInstanceState)
    }

//start callback
    override fun onStart() {
        super.onStart()
        var runner = Runnable {
            activityCycleDisplay("On Start")
        }
        var handler = Handler()

        handler.postDelayed(runner, 3000)
//        Log.i("Activity State","on start")
    }

//    on resume callback
    override fun onResume() {
        super.onResume()
        var runner = Runnable {
            activityCycleDisplay("On Resume")
        }
        var handler = Handler()

        handler.postDelayed(runner, 4000)
    }

// on pause call back
    override fun onPause() {
        super.onPause()
        activityCycleDisplay("On Pause")
    }

    override fun onStop() {
        super.onStop()
        activityCycleDisplay("On Stop")
    }

    override fun onRestart() {
        super.onRestart()
        activityCycleDisplay("On Restart")
    }

    override fun onDestroy() {
        super.onDestroy()
        activityCycleDisplay("On Destroy")
    }


    private fun activityCycleDisplay(newStr: String){
        tv_State1.text = newStr
    }

//To save instance state when the screen is rotated
    private fun saveOrientationInstance(savedInstanceState: Bundle?) {
        val orientation = resources.configuration.orientation

        when {
            savedInstanceState != null -> {

                activeStateView = savedInstanceState.getString("active_State_View").toString()
                activityCycleDisplay(activeStateView)

                landscapeCounter = savedInstanceState.getInt("landscape_Counter")
                portraitCounter = savedInstanceState.getInt("portrait_Counter")

                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    landscapeCounter++
                    tv_State2.text = "Landscape $landscapeCounter"
                } else {
                    portraitCounter++
                    tv_State2.text = "Portrait $portraitCounter"
                }
            }
        }
    }

    // invoked when the activity may be temporarily destroyed, so we save the instance state here
    override fun onSaveInstanceState(outState: Bundle) {
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState)
        //used the putString and putString is to insert data into outState
        outState.putString("active_State_View", activeStateView )
        outState.putInt("landscape_Counter", landscapeCounter)
        outState.putInt("portrait_Counter", portraitCounter)
    }
}