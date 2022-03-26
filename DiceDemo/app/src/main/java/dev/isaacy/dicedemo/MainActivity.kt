package dev.isaacy.dicedemo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.composethemeadapter.MdcTheme
import dev.isaacy.dicedemo.composeview.MainScreen

/**
 * Main activity
 *
 * @author Isaac Young
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MdcTheme {
                MainScreen()
            }
        }
    }
}

