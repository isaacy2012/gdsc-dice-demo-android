package dev.isaacy.dicedemo

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager

const val KEY_DIE_RANGE = "_KEY_DIE_RANGE"

/**
 * Preferences Repository
 *
 * @author Isaac Young
 */
class PreferencesRepository private constructor(private val sharedPreferences: SharedPreferences) {

    /**
     * The die range mutable live data backing field.
     */
    private val _dieRange: MutableLiveData<Int> = MutableLiveData(sharedPreferences.getInt(KEY_DIE_RANGE, 6))

    /**
     * The die range live data.
     */
    val dieRange: LiveData<Int> = _dieRange

    /**
     * Update the die range
     */
    private fun updateDieRange(newValue: Int) {
        sharedPreferences.edit {
            putInt(KEY_DIE_RANGE, newValue)
        }
        _dieRange.value = newValue
    }

    /**
     * Increase die range
     */
    fun increaseDieRange() {
        updateDieRange(_dieRange.value!! + 1)
    }

    /**
     * Decrease die range
     */
    fun decreaseDieRange() {
        updateDieRange(_dieRange.value!! - 1)
    }

    companion object {
        private var instance: PreferencesRepository? = null

        operator fun invoke(context: Context): PreferencesRepository {
            instance?.also {
                return it
            }
            PreferencesRepository(PreferenceManager.getDefaultSharedPreferences(context)).also {
                instance = it
                return it
            }
        }
    }
}