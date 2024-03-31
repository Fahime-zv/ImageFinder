package payback.group.imagefinder.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import payback.group.domain.di.domainDiModule
import payback.group.imagefinder.ui.screen.detail.DetailViewModel
import payback.group.imagefinder.ui.screen.search.SearchViewModel
import payback.group.imagefinder.ui.screen.search.paging.SearchPagingFactory
import payback.group.imagefinder.ui.screen.search.paging.SearchPagingFactoryImpl

val appDiModule = module {
    includes(domainDiModule)

    factory<SearchPagingFactory> {
        SearchPagingFactoryImpl(searchImageUseCase = get())
    }

    viewModel {
        SearchViewModel(searchPagingFactory = get())
    }

    viewModel { parameters ->
        DetailViewModel(itemId = parameters.get(), detailUseCaseImpl = get(), dispatcherProvider = get())
    }

}