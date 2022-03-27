package dev.isaacy.dicedemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

/**
 * Main view model.
 *
 * @author Isaac Young
 */
class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val preferencesRepository = PreferencesRepository(getApplication())


    /**
     * The die range live data.
     */
    var dieRange: LiveData<Int> = preferencesRepository.dieRange


    /**
     * The current die number mutable live data backing field.
     */
    private var _currentDieNumber: MutableLiveData<Int?> = MutableLiveData(null)

    /**
     * Current die number live data.
     */
    var currentDieNumber: LiveData<Int?> = _currentDieNumber

    /**
     * The last die number mutable live data backing field.
     */
    private var _lastDieNumber: MutableLiveData<Int?> = MutableLiveData(null)

    /**
     * The last die number.
     */
    var lastDieNumber: LiveData<Int?> = _lastDieNumber

    /**
     * Roll the die
     */
    fun rollDie() {
        _lastDieNumber.value = currentDieNumber.value
        _currentDieNumber.value = Random.nextInt(1, dieRange.value!! + 1)
    }

    /**
     * Increase die range
     */
    fun increaseDieRange() = preferencesRepository.increaseDieRange()

    /**
     * Decrease die range
     */
    fun decreaseDieRange() = preferencesRepository.decreaseDieRange()

}