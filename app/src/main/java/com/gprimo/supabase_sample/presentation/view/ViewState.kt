package com.gprimo.supabase_sample.presentation.view

import com.gprimo.supabase_sample.model.ContactModel

data class ViewState(
    val loading: Boolean = true,
    val success: Boolean = false,
    val error: Boolean = false,
    val contact: ContactModel? = null
) {
    fun setLoading() = copy(
        loading = true
    )

    fun setSuccess() = copy(
        loading = false,
        success = true
    )

    fun setError() = copy(
        loading = false,
        error = true
    )

    fun setContact(contact: ContactModel) = copy(
        loading = false,
        contact = contact
    )
}