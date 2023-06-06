package com.adiluhung.jamuin.di

import android.content.Context
import com.adiluhung.jamuin.data.local.UserPreferences

object Injection {
    fun provideUserPreference(context: Context): UserPreferences {
        return UserPreferences(context)
    }
}