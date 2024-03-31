package payback.group.imagefinder.ui.screen.search.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import payback.group.domain.usecases.SearchImageUseCase
import payback.group.model.Search
import payback.group.model.Word
import payback.group.shared.FResult

private const val PAGE_START_INDEX = 1
const val PAGE_SIZE = 20

class SearchPagingSource(
    private val searchImageUseCase: SearchImageUseCase,
    private val term: Word,
) : PagingSource<Int, Search.Hit>() {

    override fun getRefreshKey(state: PagingState<Int, Search.Hit>): Int? {
        return PAGE_START_INDEX
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Search.Hit> {
        val pageIndex = params.key ?: PAGE_START_INDEX
        return when (val sourceResult = searchImageUseCase.invoke(
            term = term.keyword,
            page = pageIndex,
            perPage = PAGE_SIZE
        )) {
            is FResult.Success -> {
                val nextKey = if (pageIndex * PAGE_SIZE < sourceResult.value.total) pageIndex + 1 else null
                LoadResult.Page(
                    data = sourceResult.value.hits,
                    prevKey = null,
                    nextKey = nextKey,
                )
            }

            is FResult.Error.Local -> {
                LoadResult.Error(LocalErrorException(sourceResult.message, sourceResult.cause))
            }

            is FResult.Error.Remote -> {
                LoadResult.Error(RemoteErrorException(sourceResult.httpCode, sourceResult.message, sourceResult.cause))
            }
        }

    }

    class LocalErrorException(
        override val message: String?,
        override val cause: Throwable?,
    ) : RuntimeException()

    class RemoteErrorException(
        val httpCode: Int,
        override val message: String?,
        override val cause: Throwable?,
    ) : RuntimeException()

}
