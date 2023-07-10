package com.gprimo.supabase_sample.data.repository

import com.gprimo.supabase_sample.data.converter.ContactConverter
import com.gprimo.supabase_sample.data.response.ContactResponse
import com.gprimo.supabase_sample.data.supabase.Supabase
import com.gprimo.supabase_sample.model.ContactModel
import io.github.jan.supabase.postgrest.postgrest

class ContactRepositoryImpl(
    supabase: Supabase,
    private val contactConverter: ContactConverter,
): ContactRepository {
    private val client = supabase.getClient()

    override suspend fun getAllContacts(): List<ContactModel>? {
        val response = client.postgrest["contacts"].select()
        val data = response.decodeList<ContactResponse>()
        return data.map { contactResponse ->
            contactConverter.convertToModel(contactResponse)
        }
    }

    override suspend fun createContact(contact: ContactModel): ContactModel? {
        val response = client.postgrest["contacts"].insert(contactConverter.convertToResponse(contact)).decodeSingle<ContactResponse>()
        return contactConverter.convertToModel(response)
    }

    override suspend fun editContact(contact: ContactModel): ContactModel? {
        val response = client.postgrest["contacts"].update(
            {
                ContactResponse::name setTo contact.name
                ContactResponse::email setTo contact.email
                ContactResponse::telephone setTo contact.telephone
            }
        ) {
            ContactResponse::id eq contact.id
        }.decodeSingle<ContactResponse>()
        return contactConverter.convertToModel(response)
    }

    override suspend fun deleteContact(contact: ContactModel): ContactModel? {
        val response = client.postgrest["contacts"].delete {
            ContactResponse::id eq contact.id
        }.decodeSingle<ContactResponse>()
        return contactConverter.convertToModel(response)
    }
}