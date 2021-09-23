package com.example.clothes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton: Button = findViewById(R.id.addButton)
        val rollButton: Button = findViewById(R.id.rollButton)
        val secondActivityButton: Button = findViewById(R.id.secondActivityButton)
        val googleButton: Button = findViewById(R.id.googleButton)

        // Add a random dice image at launch
        rollDice1()
        rollDice2()

        addButton.setOnClickListener {
            val num1text: EditText = findViewById(R.id.firstNumEditText)
            val num2text: EditText = findViewById(R.id.secondNumEditText)
            val num1 = Integer.parseInt(num1text.text.toString())
            val num2 = Integer.parseInt(num2text.text.toString())

            val sum = num1 + num2
            val resultTextView: TextView = findViewById(R.id.resultTextView)
            resultTextView.text = sum.toString()

        }

        rollButton.setOnClickListener {
            rollDice1()
            rollDice2()
        }

        secondActivityButton.setOnClickListener {
           val launchSecondActivity = Intent(applicationContext, SecondActivity::class.java)
            launchSecondActivity.putExtra("secondActivityExtra", "Heippa maailma!")
            startActivity(launchSecondActivity)
        }

        googleButton.setOnClickListener {
            val googleAddress = Uri.parse("https://www.google.fi")
            val launchGoogle = Intent(Intent.ACTION_VIEW, googleAddress)
            //if statement
            if (launchGoogle.resolveActivity(packageManager) != null) {
                startActivity(launchGoogle)
            }
        }


    }

    /**
     * Roll the dice and update the screen with the result.
     */
    private fun rollDice1() {

        // Create new Dice object with 6 sides and roll it
        val dice = Dice(6)
        val diceRoll = dice.roll()

        // Update the screen with the dice roll
        val rollResultTextView1: TextView = findViewById(R.id.rollResultTextView2)
        // Find the ImageView in the layout
        val diceImage1: ImageView = findViewById(R.id.imageView1)
        // Determine which drawable resource ID to use based on the dice roll
        val drawableResource1 = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        // Update the ImageView with the correct drawable resource ID
        diceImage1.setImageResource(drawableResource1)
        // Update the content description
        diceImage1.contentDescription = diceRoll.toString()


        rollResultTextView1.text = diceRoll.toString()
    }
    private fun rollDice2() {

        // Create new Dice object with 6 sides and roll it
        val dice = Dice(6)
        val diceRoll = dice.roll()

        // Update the screen with the dice roll
        val rollResultTextView2: TextView = findViewById(R.id.rollResultTextView1)
        val diceImage2: ImageView = findViewById(R.id.imageView2)
        val drawableResource2 = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage2.setImageResource(drawableResource2)
        diceImage2.contentDescription = diceRoll.toString()


        rollResultTextView2.text = diceRoll.toString()
    }

}
 // Make a dice object "generator"
class Dice(private val numSides: Int) {

    fun roll(): Int {
        return (1..numSides).random()
    }
}
