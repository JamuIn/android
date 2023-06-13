package com.adiluhung.jamuin.ui.screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adiluhung.jamuin.di.Injection
import com.adiluhung.jamuin.ui.MainViewModel
import com.adiluhung.jamuin.ui.screen.auth.AuthViewModel
import com.adiluhung.jamuin.ui.screen.customer.cart.CartViewModel
import com.adiluhung.jamuin.ui.screen.customer.checkout.CheckoutViewModel
import com.adiluhung.jamuin.ui.screen.customer.detailArticle.DetailArticleViewModel
import com.adiluhung.jamuin.ui.screen.customer.detailProduct.DetailProductViewModel
import com.adiluhung.jamuin.ui.screen.customer.editAddress.EditAddressViewModel
import com.adiluhung.jamuin.ui.screen.customer.editProfile.EditProfileViewModel
import com.adiluhung.jamuin.ui.screen.customer.favorite.FavoriteViewModel
import com.adiluhung.jamuin.ui.screen.customer.home.HomeViewModel
import com.adiluhung.jamuin.ui.screen.customer.profile.ProfileViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(Injection.provideUserPreference(context)) as T
        }
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(Injection.provideUserPreference(context)) as T
        }
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(Injection.provideUserPreference(context)) as T
        }
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(Injection.provideUserPreference(context)) as T
        }
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(Injection.provideUserPreference(context)) as T
        }
        if (modelClass.isAssignableFrom(EditProfileViewModel::class.java)) {
            return EditProfileViewModel(Injection.provideUserPreference(context)) as T
        }
        if (modelClass.isAssignableFrom(DetailArticleViewModel::class.java)) {
            return DetailArticleViewModel(Injection.provideUserPreference(context)) as T
        }
        if (modelClass.isAssignableFrom(DetailProductViewModel::class.java)) {
            return DetailProductViewModel(Injection.provideUserPreference(context)) as T
        }
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(Injection.provideUserPreference(context)) as T
        }
        if(modelClass.isAssignableFrom(CheckoutViewModel::class.java)) {
            return CheckoutViewModel(Injection.provideUserPreference(context)) as T
        }
        if(modelClass.isAssignableFrom(EditAddressViewModel::class.java)) {
            return EditAddressViewModel(Injection.provideUserPreference(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}