package dev.isaacy.dicedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import dev.isaacy.dicedemo.databinding.ActivityMainBinding
import kotlin.random.Random

/**
 * Main activity
 *
 * @author Isaac Young
 */
class MainActivity : AppCompatActivity() {

    /**
     * The view binding for this Activity.
     */
    private lateinit var binding: ActivityMainBinding

    /**
     * The die range.
     */
    private var dieRange: Int = 6

    /**
     * The current die number.
     */
    private var currentDieNumber: Int? = null

    /**
     * The last die number
     */
    private var lastDieNumber: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the view from the view binding.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Update the UI for the first time
        updateUI()

        addButtonOnClicks()
    }

    /**
     * Add button on click listeners.
     */
    private fun addButtonOnClicks() {
        binding.buttonRollDie.setOnClickListener {
            // set the text of the textview_die to a random number from 1 to dieRange inclusive
            lastDieNumber = currentDieNumber
            currentDieNumber = Random.nextInt(1, dieRange + 1)
            updateUI()
        }

        binding.buttonDieRangeUp.setOnClickListener {
            dieRange += 1
            updateUI()
        }

        binding.buttonDieRangeDown.setOnClickListener {
            dieRange -= 1
            updateUI()
        }
    }

    /**
     * Update UI
     */
    private fun updateUI() {
        binding.textviewDieRange.text = dieRange.toString()
        binding.textviewLastDie.text = if (lastDieNumber != null) "Last: ${lastDieNumber.toString()}" else ""
        binding.textviewDie.text =
            if (currentDieNumber != null) currentDieNumber.toString() else getString(R.string.button_roll_die_text)
    }
}