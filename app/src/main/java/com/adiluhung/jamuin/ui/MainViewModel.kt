package com.adiluhung.jamuin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adiluhung.jamuin.data.local.UserPreferences

class MainViewModel(private val pref: UserPreferences) : ViewModel() {

    fun getLoggedInUser() : LiveData<String?> = pref.getUserToken().asLiveData()
}