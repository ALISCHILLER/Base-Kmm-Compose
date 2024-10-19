package org.msa.basekmm.domain.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.msa.basekmm.ui.screen.home.HomeViewModel

val viewModelModule = module {

    viewModelOf(::HomeViewModel)
}