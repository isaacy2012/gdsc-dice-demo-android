package dev.isaacy.dicedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.isaacy.dicedemo.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    /**
     * The view binding for this Activity.
     */
    private lateinit var binding: ActivityMainBinding

    /**
     * The die range.
     */
    private var dieRange: Int = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the view from the view binding.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set the text of the textview_die to "Press Roll"
        binding.textviewDie.text = getString(R.string.press_roll_die)
        binding.textviewDieRange.text = dieRange.toString()

        addButtonOnClicks()
    }

    /**
     * Add button on click listeners.
     */
    private fun addButtonOnClicks() {
        binding.buttonRollDie.setOnClickListener {
            // set the text of the textview_die to a random number from 1 to dieRange inclusive
            binding.textviewDie.text = Random.nextInt(1, dieRange + 1).toString()
        }

        binding.buttonDieRangeUp.setOnClickListener {
            dieRange += 1
        }

        binding.buttonDieRangeDown.setOnClickListener {
            dieRange -= 1
        }
    }
}