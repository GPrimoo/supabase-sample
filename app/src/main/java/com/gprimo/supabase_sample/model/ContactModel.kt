package com.gprimo.supabase_sample.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactModel(
    val id: Int? = 0,
    val name: String,
    val telephone: String,
    val email: String,
    val createdAt: String? = null
): Parcelable
