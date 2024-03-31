package payback.group.imagefinder.ui.screen.search.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import payback.group.domain.usecases.SearchImageUseCase
import payback.group.model.Search
import payback.group.model.Word


interface SearchPagingFactory {
    fun pagingDataFlow(term: Word): Flow<PagingData<Search.Hit>>
    fun invalidate()

}

class SearchPagingFactoryImpl(
    private val searchImageUseCase: SearchImageUseCase,
) : SearchPagingFactory {

    private var searchPagingSource: SearchPagingSource? = null

    override fun pagingDataFlow(term: Word): Flow<PagingData<Search.Hit>> {
        return Pager(config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { SearchPagingSource(searchImageUseCase, term = term).also { searchPagingSource = it } }
        ).flow
    }

    override fun invalidate() {
        searchPagingSource?.invalidate()
    }

}