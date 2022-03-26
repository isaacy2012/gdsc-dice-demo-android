package dev.isaacy.dicedemo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.composethemeadapter.MdcTheme
import dev.isaacy.dicedemo.view.MainScreen

/**
 * Main activity
 *
 * @author Isaac Young
 */
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MdcTheme {
                MainScreen(viewModel)
            }
        }
    }
}

