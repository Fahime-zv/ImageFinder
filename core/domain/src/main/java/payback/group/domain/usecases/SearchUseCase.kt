package payback.group.domain.usecases

import kotlinx.coroutines.withContext
import payback.group.data.remote.repository.SearchRepository
import payback.group.model.Search
import payback.group.shared.DispatcherProvider
import payback.group.shared.FResult
import payback.group.shared.kotlin.suspendSafeDataResult

interface SearchImageUseCase {
    suspend operator fun invoke(term: String, page: Int, perPage: Int): FResult<Search>
}

internal class SearchImageUseCaseImpl(
    private val searchRepository: SearchRepository,
    private val dispatcherProvider: DispatcherProvider,
) : SearchImageUseCase {

    override suspend fun invoke(term: String, page: Int, perPage: Int): FResult<Search> {
        return withContext(dispatcherProvider.io) {
            suspendSafeDataResult {
                searchRepository.search(term = term, page = page, perPage = perPage)
            }
        }
    }

}
