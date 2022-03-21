package dev.isaacy.dicedemo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dev.isaacy.dicedemo.databinding.ActivityMainBinding

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

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the view from the view binding.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addButtonOnClicks()

        // observe the viewmodel
        observeViewModel()
    }

    /**
     * Observe the view model, updating the UI automatically.
     */
    private fun observeViewModel() {
        // observe the die range
        viewModel.dieRange.observe(this) {
            binding.textviewDieRange.text = it.toString()
        }

        // observe the last die number
        viewModel.lastDieNumber.observe(this) { lastDieNumber ->
            binding.textviewLastDie.text = if (lastDieNumber != null) "Last: $lastDieNumber" else ""
        }

        // observe the current die number
        viewModel.currentDieNumber.observe(this) { currentDieNumber ->
            binding.textviewDie.text =
                currentDieNumber?.toString() ?: getString(R.string.button_roll_die_text)

        }
    }

    /**
     * Add button on click listeners.
     */
    private fun addButtonOnClicks() {
        binding.buttonRollDie.setOnClickListener {
            // set the text of the textview_die to a random number from 1 to dieRange inclusive
            viewModel.rollDie()
        }

        binding.buttonDieRangeUp.setOnClickListener {
            viewModel.increaseDieRange()
        }

        binding.buttonDieRangeDown.setOnClickListener {
            viewModel.decreaseDieRange()
        }
    }
}