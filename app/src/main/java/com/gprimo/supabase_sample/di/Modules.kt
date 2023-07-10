package com.gprimo.supabase_sample.di

import com.gprimo.supabase_sample.data.business.CreateContactBusiness
import com.gprimo.supabase_sample.data.business.DeleteContactBusiness
import com.gprimo.supabase_sample.data.business.EditContactBusiness
import com.gprimo.supabase_sample.data.business.GetAllContactsBusiness
import com.gprimo.supabase_sample.data.converter.ContactConverter
import com.gprimo.supabase_sample.data.repository.ContactRepository
import com.gprimo.supabase_sample.data.repository.ContactRepositoryImpl
import com.gprimo.supabase_sample.data.supabase.Supabase
import com.gprimo.supabase_sample.presentation.add.AddViewModel
import com.gprimo.supabase_sample.presentation.edit.EditViewModel
import com.gprimo.supabase_sample.presentation.list.ListViewModel
import com.gprimo.supabase_sample.presentation.view.ViewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModules = module {
    single<ContactRepository> { ContactRepositoryImpl(get(), get()) }
}

val businessModules = module {
    single { CreateContactBusiness(get()) }
    single { DeleteContactBusiness(get()) }
    single { EditContactBusiness(get()) }
    single { GetAllContactsBusiness(get()) }
}

val viewModelModules = module {
    viewModel { ListViewModel(get()) }
    viewModel { AddViewModel(get()) }
    viewModel { EditViewModel(get(), get()) }
    viewModel { ViewViewModel(get(), get()) }
}

val converterModules = module {
    single { ContactConverter() }
}

val supabaseModules = module {
    single { Supabase() }
}

val appModule = repositoryModules + businessModules + viewModelModules + converterModules + supabaseModules