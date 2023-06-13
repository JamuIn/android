package com.adiluhung.jamuin.helper

import android.os.Bundle
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun Int.toRupiah(): String {
    val df = DecimalFormat("###,##0")

    val customSymbol = DecimalFormatSymbols()
    customSymbol.groupingSeparator = '.'
    df.decimalFormatSymbols = customSymbol
    return "Rp." + df.format(this)
}

fun String.titlecaseFirstChar() = replaceFirstChar(Char::titlecase)