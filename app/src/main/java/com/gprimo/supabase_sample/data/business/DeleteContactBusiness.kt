package com.gprimo.supabase_sample.data.business

import com.gprimo.supabase_sample.data.repository.ContactRepository
import com.gprimo.supabase_sample.model.ContactModel

class DeleteContactBusiness(private val repository: ContactRepository) {
    suspend fun delete(contact: ContactModel): ContactModel? = repository.deleteContact(contact)
}