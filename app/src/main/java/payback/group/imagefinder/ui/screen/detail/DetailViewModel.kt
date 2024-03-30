package payback.group.imagefinder.ui.screen.detail

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import payback.group.domain.usecases.DetailUseCase
import payback.group.imagefinder.architecture.BaseViewModel
import payback.group.model.Search
import payback.group.shared.DispatcherProvider
import payback.group.shared.FResult

class DetailViewModel(
    private val itemId: String,
    private val detailUseCaseImpl: DetailUseCase,
    private val dispatcherProvider: DispatcherProvider,
) : BaseViewModel<Search.Hit, DetailAction>() {

    init {
        dispatch(DetailAction.GetDetail)
    }

    override fun dispatch(action: DetailAction) {
        super.dispatch(action)
        when (action) {
            is DetailAction.GetDetail -> {
                viewModelScope.launch(dispatcherProvider.io) {
                    setLoadingState(onFrontOfContent = true)
                    when (val result = detailUseCaseImpl(itemId)) {
                        is FResult.Error -> setErrorState(result)
                        is FResult.Success -> setSuccessState(result.value)
                    }
                }
            }
        }
    }
}

