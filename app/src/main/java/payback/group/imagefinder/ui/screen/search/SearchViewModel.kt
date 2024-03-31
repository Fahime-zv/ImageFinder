package payback.group.imagefinder.ui.screen.search

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import payback.group.domain.usecases.SearchImageUseCase
import payback.group.imagefinder.architecture.BaseViewModel
import payback.group.model.Search
import payback.group.shared.DispatcherProvider
import payback.group.shared.FResult

class SearchViewModel(
    private val searchImageUseCase: SearchImageUseCase,
    private val dispatcherProvider: DispatcherProvider,
) : BaseViewModel<Search, SearchAction>() {

    private var job: Job? = null

    init {
        dispatch(SearchAction.DoingSearch("fruits"))
    }

    override fun dispatch(action: SearchAction) {
        super.dispatch(action)
        when (action) {
            is SearchAction.DoingSearch -> {

                job?.cancel()

                job = viewModelScope.launch(dispatcherProvider.io) {
                    setLoadingState(onFrontOfContent = true)
                    when (val result = searchImageUseCase(action.term)) {
                        is FResult.Error -> setErrorState(result)
                        is FResult.Success -> {
                            val items = result.value.hits
                            if (items.isNotEmpty())
                                setSuccessState(result.value)
                            else
                                setEmptyState("Nothing to saecrh")
                        }
                    }

                }

            }

        }
    }
}
