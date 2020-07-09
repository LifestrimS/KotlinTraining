package ru.lifestrim.kotlintraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import ru.lifestrim.kotlintraining.common.Constants
import ru.lifestrim.kotlintraining.model.GameUser
import ru.lifestrim.kotlintraining.random.RandomNumberGenerator
import ru.lifestrim.kotlintraining.random.impl.StdRandom
import kotlin.collections.ArrayList
import java.util.*

class MainActivity : AppCompatActivity() {
    val rnd:RandomNumberGenerator = StdRandom()
    var started = false;
    var number = 0;
    var tries = 0;

    var gameUser = GameUser(
        lastName = "Doe",
        firstName = "John",
        userName = "jdoe",
        birthday = "1900-01-01",
        registrationNumber = 0,
        userRank = 0.0
    )

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchSavedInstanceData(savedInstanceState)
        doGuess.isEnabled = started
    }

    override
    fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        putInstanceData(outState)
    }

    fun start(v:View) {
        log("Game started")
        num.setText("")
        started = true
        doGuess.isEnabled = true
        status.text = getString(R.string.guess_hint,
            Constants.LOWER_BOUND,
            Constants.UPPER_BOUND)

        number = rnd.rnd(Constants.LOWER_BOUND, Constants.UPPER_BOUND)
        tries = 0
        val r = Random()
        r.nextInt(7)
    }

    fun guess(v:View) {
        if(num.text.toString() == "") return
        tries++
        log("Guessed " + num.text +
                " (tries:" + tries + ")")
        val g = num.text.toString().toInt()
        when {
            g < number -> {
                status.setText(R.string.status_too_low)
                num.setText("")
            }
            g > number -> {
                status.setText(R.string.status_too_high)
                num.setText("")
            }
            else -> {
                status.text = getString(R.string.status_hit, tries)
                started = false
                doGuess.isEnabled = false
            }
        }
    }

    /////////////////////////////////////////
    /////////////////////////////////////////

    private
    fun putInstanceData(outState: Bundle?) {
        if (outState != null) with (outState) {
            putBoolean("started", started)
            putInt("number", number)
            putInt("tries", tries)
            putString("statusMsg", status.text.toString())
            putStringArrayList("logs", ArrayList(console.text.split("\n")))
        }
    }

    private
    fun fetchSavedInstanceData(
        savedInstanceState: Bundle?) {
        if (savedInstanceState != null)
            with(savedInstanceState) {
                started = getBoolean("started")
                number = getInt("number")
                tries = getInt("tries")
                status.text = getString("statusMsg")
                console.text =
                    getStringArrayList("logs")!!.
                    joinToString("\n")
            }
    }

    private
    fun log(msg:String) {
        Log.d("LOG", msg)
        console.log(msg)
    }
}
