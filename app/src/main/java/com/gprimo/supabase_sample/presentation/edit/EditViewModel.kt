package com.gprimo.supabase_sample.presentation.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gprimo.supabase_sample.data.business.EditContactBusiness
import com.gprimo.supabase_sample.model.ContactModel
import kotlinx.coroutines.launch

class EditViewModel(
    private val contact: ContactModel,
    private val editContactBusiness: EditContactBusiness
): ViewModel() {
    val state: MutableLiveData<EditState> by lazy {
        MutableLiveData<EditState>()
    }

    fun init() {
        state.postValue(getState().setContact(contact))
    }

    fun editContact(contact: ContactModel) {
        viewModelScope.launch {
            state.postValue(getState().setLoading())
            val response = editContactBusiness.edit(contact)
            response?.let { contact ->
                state.postValue(getState().setSuccess(contact))
            } ?: run {
                state.postValue(getState().setError())
            }
        }
    }

    private fun getState() = state.value ?: EditState()
}