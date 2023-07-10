package com.gprimo.supabase_sample.presentation.list

import com.gprimo.supabase_sample.model.ContactModel

data class ListState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val list: List<ContactModel> = listOf()
) {
    fun setLoading() = copy(
        loading = true
    )

    fun setError() = copy(
        error = true,
        loading = false
    )

    fun setList(list: List<ContactModel>) = copy(
        loading = false,
        error = false,
        list = list
    )
}
