package payback.group.domain.di

import org.koin.dsl.module
import payback.group.data.di.dataDiModule
import payback.group.domain.usecases.DetailUseCase
import payback.group.domain.usecases.DetailUseCaseImpl
import payback.group.domain.usecases.SearchImageUseCase
import payback.group.domain.usecases.SearchImageUseCaseImpl


val  domainDiModule = module {
    includes(dataDiModule)

    factory<SearchImageUseCase> {
        SearchImageUseCaseImpl(
            searchRepository = get(),
            dispatcherProvider = get()
        )
    }

    factory<DetailUseCase> {
        DetailUseCaseImpl(
            searchRepository = get(),
            dispatcherProvider = get()
        )
    }
}