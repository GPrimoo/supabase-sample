package com.gprimo.supabase_sample.data.converter

import com.gprimo.supabase_sample.data.response.ContactResponse
import com.gprimo.supabase_sample.model.ContactModel
import com.gprimo.supabase_sample.util.Converter
import com.gprimo.supabase_sample.util.nullOrNoZero
import com.gprimo.supabase_sample.util.orZero

class ContactConverter: Converter<ContactResponse, ContactModel> {
    override fun convertToModel(response: ContactResponse) = ContactModel(
        id = response.id.orZero(),
        name = response.name.orEmpty(),
        telephone = response.telephone.orEmpty(),
        email = response.email.orEmpty(),
        createdAt = response.createdAt.orEmpty()
    )

    override fun convertToResponse(model: ContactModel) = ContactResponse(
        id = model.id.nullOrNoZero(),
        name = model.name,
        telephone = model.telephone,
        email = model.email,
        createdAt = model.createdAt
    )
}