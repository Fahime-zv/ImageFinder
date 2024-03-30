package payback.group.core.network.di

import org.koin.dsl.module
import payback.group.core.network.ServiceProvider
import payback.group.core.network.service.SearchService
import payback.group.shared.DefaultDispatcherProvider
import payback.group.shared.DispatcherProvider

val networkDiModule = module {

    factory<DispatcherProvider> { DefaultDispatcherProvider() }

    single { ServiceProvider() }

    factory { get<ServiceProvider>().provide(SearchService::class) }
}