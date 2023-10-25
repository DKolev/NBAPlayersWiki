package com.example.nbaplayerswiki.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbaplayerswiki.BuildConfig
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AboutViewModel : ViewModel() {

    // The internal MutableLiveData that stores the app version
    private val _appVersion = MutableLiveData<String>()

    // The external immutable LiveData for the app version
    val appVersion: LiveData<String> = _appVersion

    init {
        buildAppVersion()
    }

    /**
     * Get the app version name & current date in yyyyMMdd format.
     * Add a title string and then build everything together to be displayed.
     */
    private fun buildAppVersion() {

        viewModelScope.launch {
            val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
            val currentDate = LocalDateTime.now().format(formatter)
            val dot = "."
            val appBuild = BuildConfig.VERSION_NAME
            val appVersionTitle = "App version is: "

            val appVersionString = appVersionTitle + appBuild + dot + currentDate
            _appVersion.value = appVersionString
        }
    }
}