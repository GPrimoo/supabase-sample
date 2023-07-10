package com.gprimo.supabase_sample.util

fun Int?.orZero(): Int {
    return this ?: 0
}

fun Int?.nullOrNoZero(): Int? {
    return if (this != 0) this else null
}