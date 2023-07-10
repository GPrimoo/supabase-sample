package com.gprimo.supabase_sample.util

interface Converter<RESPONSE, MODEL> {
    fun convertToModel(response: RESPONSE) : MODEL
    fun convertToResponse(model: MODEL) : RESPONSE
}