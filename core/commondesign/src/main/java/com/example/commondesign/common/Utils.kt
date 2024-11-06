package com.example.commondesign.common

import android.text.format.DateUtils
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object Utils {

    fun formatNumber(amount: Int): String {
        val formattedCurrency: String
        try {
            val formatter: NumberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
            formattedCurrency = formatter.format(amount)
        } catch (e: NumberFormatException) {
            // The number has probably been formatted already
            return amount.toString()
        }
        return formattedCurrency
    }

    fun formatRelativeDate(dateStr: String): String {
        val date = try {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",).parse(dateStr)
        }catch (e: Exception){
            Date()
        }
        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val dateToDisplay: String
        val tomorrowCal = Calendar.getInstance()
        val yesterdayCal = Calendar.getInstance()

        tomorrowCal.add(Calendar.DAY_OF_YEAR, 1)
        yesterdayCal.add(Calendar.DAY_OF_YEAR, -1)

        val tomorrow = tomorrowCal.time
        val yesterday = yesterdayCal.time

        val isToday = DateUtils.isToday(date.time)
        val isYesterday = dateFormat.format(date) == dateFormat.format(yesterday)
        val isTomorrow = dateFormat.format(date) == dateFormat.format(tomorrow)

        dateToDisplay = when {
            isToday -> "Today"
            isYesterday -> "Yesterday"
            isTomorrow -> "Tomorrow"
            else -> SimpleDateFormat("EEE, d MMM", Locale.getDefault()).format(date)
        }
        return dateToDisplay
    }
}