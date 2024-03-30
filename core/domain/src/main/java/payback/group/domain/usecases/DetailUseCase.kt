package payback.group.domain.usecases

import kotlinx.coroutines.withContext
import payback.group.data.remote.repository.SearchRepository
import payback.group.model.Search
import payback.group.shared.DispatcherProvider
import payback.group.shared.FResult
import payback.group.shared.kotlin.suspendSafeDataResult

interface DetailUseCase {
    suspend operator fun invoke(id: String): FResult<Search.Hit>
}

internal class DetailUseCaseImpl(
    private val searchRepository: SearchRepository,
    private val dispatcherProvider: DispatcherProvider
) : DetailUseCase{

    override suspend fun invoke(id: String): FResult<Search.Hit> {
        return withContext(dispatcherProvider.io) {
            suspendSafeDataResult {
                searchRepository.detail(id=id)
            }
        }
    }

}
