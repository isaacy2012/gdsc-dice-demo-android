package dev.isaacy.dicedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.isaacy.dicedemo.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var dieRange: Int = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textviewDie.text = getString(R.string.press_roll_die)
        binding.textviewDieRange.text = dieRange.toString()

        addButtonOnClicks()
    }

    private fun addButtonOnClicks() {
        binding.buttonRollDie.setOnClickListener {
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