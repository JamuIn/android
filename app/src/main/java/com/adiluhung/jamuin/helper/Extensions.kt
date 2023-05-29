package com.adiluhung.jamuin.helper

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun Int.toRupiah(): String {
    val df = DecimalFormat("###,##0")

    val customSymbol = DecimalFormatSymbols()
    customSymbol.groupingSeparator = '.'
    df.decimalFormatSymbols = customSymbol
    return "Rp." + df.format(this)
}