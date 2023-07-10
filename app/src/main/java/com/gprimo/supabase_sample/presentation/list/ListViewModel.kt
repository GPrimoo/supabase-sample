package com.gprimo.supabase_sample.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gprimo.supabase_sample.data.business.GetAllContactsBusiness
import kotlinx.coroutines.launch

class ListViewModel(
    private val getAllContactsBusiness: GetAllContactsBusiness
): ViewModel() {
    val state: MutableLiveData<ListState> by lazy {
        MutableLiveData<ListState>()
    }

    fun init() {
        getContacts()
    }

    private fun getContacts() {
        viewModelScope.launch {
            state.postValue(getState().setLoading())
            val response = getAllContactsBusiness.getAll()
            response?.let { list ->
                state.postValue(getState().setList(list))
            } ?: run {
                state.postValue(getState().setError())
            }
        }
    }

    private fun getState() = state?.value ?: ListState()
}