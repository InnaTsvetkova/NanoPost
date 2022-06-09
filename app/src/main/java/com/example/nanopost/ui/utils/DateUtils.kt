package com.example.nanopost.ui.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(date: Long): String{
    return SimpleDateFormat("MMM d, yyyy hh:mm:ss", Locale.ENGLISH).format(date)
}