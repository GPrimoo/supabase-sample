package com.gprimo.supabase_sample.presentation.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gprimo.supabase_sample.data.business.DeleteContactBusiness
import com.gprimo.supabase_sample.model.ContactModel
import kotlinx.coroutines.launch

class ViewViewModel(
    val contact: ContactModel,
    private val deleteContactBusiness: DeleteContactBusiness
): ViewModel() {
    val state: MutableLiveData<ViewState> by lazy {
        MutableLiveData<ViewState>()
    }

    fun init() {
        state.postValue(getState().setContact(contact))
    }

    fun deleteContact(contact: ContactModel) {
        viewModelScope.launch {
            state.postValue(getState().setLoading())
            val response = deleteContactBusiness.delete(contact)
            response?.let {
                state.postValue(getState().setSuccess())
            } ?: run {
                state.postValue(getState().setError())
            }
        }
    }

    private fun getState() = state.value ?: ViewState()
}