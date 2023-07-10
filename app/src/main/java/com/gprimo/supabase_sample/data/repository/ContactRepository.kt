package com.gprimo.supabase_sample.data.repository

import com.gprimo.supabase_sample.model.ContactModel

interface ContactRepository {
    suspend fun getAllContacts(): List<ContactModel>?

    suspend fun createContact(contact: ContactModel): ContactModel?

    suspend fun editContact(contact: ContactModel): ContactModel?

    suspend fun deleteContact(contact: ContactModel): ContactModel?
}