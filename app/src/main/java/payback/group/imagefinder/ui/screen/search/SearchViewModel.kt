package payback.group.imagefinder.ui.screen.search

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import payback.group.imagefinder.architecture.BaseViewModel
import payback.group.imagefinder.ui.screen.search.paging.SearchPagingFactory
import payback.group.model.Search
import payback.group.model.Word

class SearchViewModel(
    private val searchPagingFactory: SearchPagingFactory,
) : BaseViewModel<Search, SearchAction>() {

    private val term: Word = Word("fruits")

    val pagingFlow = searchPagingFactory.pagingDataFlow(term).cachedIn(viewModelScope)

    override fun dispatch(action: SearchAction) {
        super.dispatch(action)

        when (action) {
            is SearchAction.DoingSearch -> {
                term.keyword = action.term
                searchPagingFactory.invalidate()
            }
        }

    }

}

