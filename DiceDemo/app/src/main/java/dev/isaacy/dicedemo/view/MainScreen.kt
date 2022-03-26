package dev.isaacy.dicedemo.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.isaacy.dicedemo.MainViewModel
import dev.isaacy.dicedemo.R
import java.util.*

@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    val dieNumber by viewModel.currentDieNumber.observeAsState()
    val lastNumber by viewModel.lastDieNumber.observeAsState()
    val dieRange by viewModel.dieRange.observeAsState()
    val configuration = LocalConfiguration.current

    @Composable
    fun dieArea(modifier: Modifier) = DieArea(
        modifier = modifier,
        dieNumber = dieNumber,
        lastNumber = lastNumber
    ) { viewModel.rollDie() }

    @Composable
    fun configurationPane(modifier: Modifier) = ConfigurationPane(
        modifier = modifier,
        dieRange = dieRange!!,
        onClickIncreaseDieRange = { viewModel.increaseDieRange() },
        onClickDecreaseDieRange = { viewModel.decreaseDieRange() }
    )

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Row {
                dieArea(Modifier.weight(1f))
                VerticalDivider()
                configurationPane(Modifier.weight(1f))
            }
        }
        else -> {
            Column {
                dieArea(Modifier.weight(1f))
                Divider()
                configurationPane(Modifier.weight(1f))
            }
        }
    }

}

@Composable
private fun DieArea(
    modifier: Modifier = Modifier,
    dieNumber: Int?,
    lastNumber: Int?,
    onClickRollDie: () -> Unit
) = Column(
    modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
) {
    Text(
        dieNumber?.toString() ?: stringResource(R.string.press_roll_die),
        style = MaterialTheme.typography.h4
    )
    Text(
        lastNumber?.let { "Last: $it" } ?: "",
        modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
    )
    Button(onClickRollDie) {
        Text(stringResource(R.string.button_roll_die_text).uppercase(Locale.getDefault()))
    }
}


@Composable
private fun ConfigurationPane(
    modifier: Modifier = Modifier,
    dieRange: Int,
    onClickIncreaseDieRange: () -> Unit,
    onClickDecreaseDieRange: () -> Unit
) = Column(
    modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
) {
    Text(
        stringResource(R.string.die_range_text),
        modifier = Modifier
            .align(Alignment.Start)
            .padding(16.dp),
        style = MaterialTheme.typography.h5
    )
    Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
        Text(dieRange.toString(), style = MaterialTheme.typography.h4)
    }
    Row {
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp, end = 8.dp, bottom = 16.dp),
            onClick = onClickDecreaseDieRange
        ) {
            Text(stringResource(R.string.down_text).uppercase(Locale.getDefault()))
        }
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 16.dp, bottom = 16.dp),
            onClick = onClickIncreaseDieRange
        ) {
            Text(stringResource(R.string.up_text).uppercase(Locale.getDefault()))
        }
    }
}

@Preview
@Composable
private fun MyScreenPreview() {
    MainScreen(MainViewModel().apply {
        rollDie()
    })
}
