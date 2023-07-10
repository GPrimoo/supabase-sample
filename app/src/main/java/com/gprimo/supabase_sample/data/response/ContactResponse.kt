package com.gprimo.supabase_sample.data.response

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ContactResponse(
    val id: Int? = null,
    val name: String? = null,
    val telephone: String? = null,
    val email: String? = null,
    @SerialName("created_at")
    val createdAt: String? = null
)
