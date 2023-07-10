package com.gprimo.supabase_sample.data.business

import com.gprimo.supabase_sample.data.repository.ContactRepository
import com.gprimo.supabase_sample.model.ContactModel

class CreateContactBusiness(private val repository: ContactRepository) {
    suspend fun create(contact: ContactModel): ContactModel? = repository.createContact(contact)
}