package com.adiluhung.jamuin.ui.screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adiluhung.jamuin.di.Injection
import com.adiluhung.jamuin.ui.MainViewModel
import com.adiluhung.jamuin.ui.screen.auth.AuthViewModel
import com.adiluhung.jamuin.ui.screen.customer.cart.CartViewModel
import com.adiluhung.jamuin.ui.screen.customer.home.HomeViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(Injection.provideUserPreference(context)) as T
        }
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(Injection.provideUserPreference(context)) as T
        }
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(Injection.provideUserPreference(context)) as T
        }
        if(modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(Injection.provideUserPreference(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}