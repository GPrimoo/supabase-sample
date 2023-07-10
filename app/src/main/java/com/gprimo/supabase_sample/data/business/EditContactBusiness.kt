package com.gprimo.supabase_sample.data.business

import com.gprimo.supabase_sample.data.repository.ContactRepository
import com.gprimo.supabase_sample.model.ContactModel

class EditContactBusiness(private val repository: ContactRepository) {
    suspend fun edit(contact: ContactModel): ContactModel? = repository.editContact(contact)
}