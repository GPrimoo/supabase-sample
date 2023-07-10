package com.gprimo.supabase_sample.presentation.add

data class AddState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val success: Boolean = false
) {
    fun setLoading() = copy(
        loading = true
    )

    fun setError() = copy(
        loading = false,
        error = true
    )

    fun setSuccess() = copy(
        loading = false,
        error = false,
        success = true
    )
}