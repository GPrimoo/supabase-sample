package com.gprimo.supabase_sample.data.business

import com.gprimo.supabase_sample.data.repository.ContactRepository
import com.gprimo.supabase_sample.model.ContactModel

class GetAllContactsBusiness(private val repository: ContactRepository) {
    suspend fun getAll(): List<ContactModel>? = repository.getAllContacts()
}