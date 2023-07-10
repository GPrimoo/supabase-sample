package com.gprimo.supabase_sample.presentation.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gprimo.supabase_sample.data.business.CreateContactBusiness
import com.gprimo.supabase_sample.model.ContactModel
import kotlinx.coroutines.launch

class AddViewModel(
    private val createContactBusiness: CreateContactBusiness
): ViewModel() {
    val state: MutableLiveData<AddState> by lazy {
        MutableLiveData<AddState>()
    }

    fun createContact(contact: ContactModel) {
        viewModelScope.launch {
            state.postValue(getState().setLoading())
            val response = createContactBusiness.create(contact)
            response?.let {
                state.postValue(getState().setSuccess())
            } ?: run {
                state.postValue(getState().setError())
            }
        }
    }

    private fun getState() = state?.value ?: AddState()
}