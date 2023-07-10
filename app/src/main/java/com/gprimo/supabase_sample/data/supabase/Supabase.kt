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
        const val URL = "https://aguunbhxzbdqoivmnqtb.supabase.co"
        const val KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFndXVuYmh4emJkcW9pdm1ucXRiIiwicm9sZSI6ImFub24iLCJpYXQiOjE2ODYwOTUwOTEsImV4cCI6MjAwMTY3MTA5MX0.6SCDu38ZcMIadk2e66jPV5CAsTtXPjqiztVj_LPosFc"
    }
}