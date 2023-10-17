package com.jaypuzon.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class GN_MainActivity : AppCompatActivity() {
    private var SubmitBtn: Button? = null
    private var ResetBtn: Button? = null
    private var UserGuess: EditText? = null
    private var Attempts: TextView? = null
    private var CorrectGuess: TextView? = null
    private var correctGuess: Int? = null
    private var attempts: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gn_activity_main)

        // assign the variables to the UI elements
        SubmitBtn = findViewById(R.id.SubmitButton)
        ResetBtn = findViewById(R.id.ResetButton)
        UserGuess = findViewById(R.id.UserGuess)
        CorrectGuess = findViewById(R.id.CorrectGuess)
        Attempts = findViewById(R.id.Attempts)

        // set the click listener
        SubmitBtn!!.setOnClickListener() {
            Guess()
        }

        // set the click listener
        ResetBtn!!.setOnClickListener() {
            Reset()
        }

        // generate a random number
        correctGuess = (1..100).random()
    }

    fun Guess() {
        // get the user input
        val userGuessStr = UserGuess!!.text.toString()

        // check if the user input is empty
        if (userGuessStr.isEmpty()) {
            // display a toast
            UserGuess!!.error = "Please enter a valid number"
            return
        }

        val userGuess = userGuessStr.toInt()

        // check if the user guess is correct
        if (userGuess == correctGuess) {
            // if the user guess is correct, display the correct guess
            CorrectGuess!!.text = "Correct Guess: " + correctGuess.toString()

            // display a toast
            Toast.makeText(this, "Correct!", Toast.LENGTH_LONG).show()

            // disable submit button
            SubmitBtn!!.isEnabled = false

            // disable user guess edit text
            UserGuess!!.isEnabled = false

            // enable reset button
            ResetBtn!!.isEnabled = true
        } else {
            // if the user guess is wrong, display a toast as a hint
            if (userGuess > correctGuess!!)
                Toast.makeText(this, "Too high!", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "Too low!", Toast.LENGTH_SHORT).show()
        }

        // increase the number of attempts
        IncreaseAttempt()

        // check if the user has reached the maximum number of attempts
        if (attempts == 100) {
            // display the correct guess
            CorrectGuess!!.text = "Correct Guess: " + correctGuess.toString()

            // disable submit button
            SubmitBtn!!.isEnabled = false

            // disable user guess edit text
            UserGuess!!.isEnabled = false

            // enable reset button
            ResetBtn!!.isEnabled = true
        }
    }

    fun IncreaseAttempt() {
        // increase the number of attempts
        attempts = attempts?.plus(1)

        // display the number of attempts
        Attempts!!.text = "Attempts: " + attempts.toString()
    }

    fun Reset() {
        // reset the number of attempts
        attempts = 0;
        Attempts!!.text = "Attempts: --"

        // generate a random number
        correctGuess = (1..100).random()
        CorrectGuess!!.text = "Correct Guess: --"

        // clear the user guess edit text
        UserGuess!!.text.clear()

        // disable reset button
        ResetBtn!!.isEnabled = false

        // enable submit button
        SubmitBtn!!.isEnabled = true

        // enable user guess edit text
        UserGuess!!.isEnabled = true
    }
}