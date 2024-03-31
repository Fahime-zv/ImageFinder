package payback.group.imagefinder.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import payback.group.domain.di.domainDiModule
import payback.group.imagefinder.ui.screen.detail.DetailViewModel
import payback.group.imagefinder.ui.screen.search.SearchViewModel

val appDiModule = module {
    includes(domainDiModule)



    viewModel{
        SearchViewModel(searchImageUseCase = get(), dispatcherProvider = get())
    }

    viewModel{parameters->
        DetailViewModel(itemId = parameters.get(), detailUseCaseImpl = get(), dispatcherProvider = get())
    }
}