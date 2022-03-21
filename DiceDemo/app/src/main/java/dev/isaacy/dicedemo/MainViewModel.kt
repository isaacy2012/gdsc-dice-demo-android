package dev.isaacy.dicedemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

/**
 * Main view model
 *
 * @author Isaac Young
 */
class MainViewModel : ViewModel() {

    /**
     * The die range mutable live data backing field.
     */
    private var _dieRange: MutableLiveData<Int> = MutableLiveData(6)

    /**
     * The die range live data.
     */
    var dieRange: LiveData<Int> = _dieRange


    /**
     * The current die number mutable live data backing field.
     */
    private var _currentDieNumber: MutableLiveData<Int> = MutableLiveData()

    /**
     * Current die number live data.
     */
    var currentDieNumber: LiveData<Int?> = _currentDieNumber

    /**
     * The last die number mutable live data backing field.
     */
    private var _lastDieNumber: MutableLiveData<Int?> = MutableLiveData()

    /**
     * The last die number.
     */
    var lastDieNumber: LiveData<Int?> = _lastDieNumber

    fun rollDie() {
        _lastDieNumber.value = currentDieNumber.value
        _currentDieNumber.value = Random.nextInt(1, _dieRange.value!!)
    }

    fun increaseDieRange() {
        _dieRange.value = _dieRange.value!! + 1
    }

    fun decreaseDieRange() {
        _dieRange.value = _dieRange.value!! - 1
    }

}