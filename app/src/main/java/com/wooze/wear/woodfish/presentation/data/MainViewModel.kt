package com.wooze.wear.woodfish.presentation.data

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.wooze.wear.woodfish.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStoreManager = DataStoreManager(application.applicationContext)


    private val _selectedColor = mutableStateOf(Color.Companion.White)
    val selectedColor: State<Color> = _selectedColor

    private val _volumeLevel = mutableIntStateOf(4)
    val volumeLevel: State<Int> = _volumeLevel

    private val _newText = mutableStateOf("功德")
    val newText: State<String> = _newText

    private val _selectedSoundEffect = mutableStateOf("清脆")
    val selectedSoundEffect: State<String> = _selectedSoundEffect

    private val _countNumber = mutableIntStateOf(0)
    val countNumber: State<Int> = _countNumber

    private val _isVibrateOpen = mutableStateOf(true)
    val isVibrateOpen: State<Boolean> = _isVibrateOpen

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _newText.value = dataStoreManager.readTextFlow.first()
            _selectedSoundEffect.value = dataStoreManager.readSoundFlow.first()
            _countNumber.intValue = dataStoreManager.readCountFlow.first()
            _selectedColor.value = Color(dataStoreManager.readColorFlow.first())
            _isVibrateOpen.value = dataStoreManager.readIsVibrateOpenFlow.first()
            _volumeLevel.intValue = dataStoreManager.readVolumeFLow.first()
        }
    }

    fun addCountNumber() {
        _countNumber.intValue = _countNumber.intValue + 1
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreManager.saveCount(_countNumber.intValue)
        }
    }

    fun clearCountNumber() {
        _countNumber.intValue = 0
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreManager.saveCount(_countNumber.intValue)
        }
    }

    fun updateColor(color: Color) {
        _selectedColor.value = color
        viewModelScope.launch(Dispatchers.IO) {
            val colorData = color.toArgb()
            dataStoreManager.saveColor(colorData)
        }
    }

    fun updateVolumeLevel(level: Int) {
        _volumeLevel.intValue = level
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreManager.saveVolume(level)
        }
    }

    fun getVolume(): Float {
        val levels = listOf(0f, 0.25f, 0.5f, 0.75f, 1f)
        return levels[volumeLevel.value]
    }

    fun updateSoundEffect(soundName: String) {
        _selectedSoundEffect.value = soundName
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreManager.saveSound(soundName)
        }
    }

    fun updateText(newText: String) {
        _newText.value = newText
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreManager.saveText(newText)
        }
    }

    fun updateIsVibrateOpen(boolean: Boolean) {
        _isVibrateOpen.value = boolean
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreManager.saveVibrate(boolean)
        }
    }

    fun soundEffectFileId(): Int {
        return when (_selectedSoundEffect.value) {
            "清脆" -> R.raw.muyu_sound_normal
            "沉重" -> R.raw.muyu_sound_heavy
            "坤叫" -> R.raw.muyu_sound_ikun
            "Ciallo～(∠·ω< )⌒★" -> R.raw.muyu_sound_ciallo
            else -> R.raw.muyu_sound_normal
        }
    }
}