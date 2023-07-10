package com.gprimo.supabase_sample.presentation.edit

import com.gprimo.supabase_sample.model.ContactModel

data class EditState(
    val loading: Boolean = true,
    val error: Boolean = false,
    val success: Boolean = false,
    val contact: ContactModel? = null
) {
    fun setLoading() = copy(
        loading = true
    )

    fun setError() = copy(
        error = true,
        loading = false
    )

    fun setSuccess(contact: ContactModel) = copy(
        loading = false,
        error = false,
        success = true,
        contact = contact
    )

    fun setContact(contact: ContactModel) = copy(
        loading = false,
        contact = contact
    )
}
