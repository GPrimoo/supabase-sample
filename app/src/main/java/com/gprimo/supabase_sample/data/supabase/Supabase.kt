package com.gprimo.supabase_sample.data.supabase

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

class Supabase {
    fun getClient(): SupabaseClient = createSupabaseClient(
        supabaseUrl = URL,
        supabaseKey = KEY
    ) {
        install(Postgrest)
    }

    companion object {
        const val URL = "SUPABASE_URL"
        const val KEY = "SUPABASE_KEY"
    }
}
