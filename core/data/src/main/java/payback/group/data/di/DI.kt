package payback.group.data.di

import payback.group.data.remote.repository.SearchRepository
import payback.group.data.remote.repository.SearchRepositoryImpl
import org.koin.dsl.module
import payback.group.core.network.di.networkDiModule

val dataDiModule= module {
    includes(networkDiModule)
    factory<SearchRepository>{ SearchRepositoryImpl(searchService=get()) }
}