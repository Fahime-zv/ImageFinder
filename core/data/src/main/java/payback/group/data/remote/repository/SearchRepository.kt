package payback.group.data.remote.repository

import payback.group.core.network.safeApiCall
import payback.group.core.network.service.SearchService
import payback.group.data.remote.mapper.responseToModel.map
import payback.group.model.Search
import payback.group.shared.FResult
import payback.group.shared.mapIfSuccess

interface SearchRepository {
    suspend fun search(term: String, page: Int, perPage: Int): FResult<Search>
    suspend fun detail(id: String): FResult<Search.Hit>
}

internal class SearchRepositoryImpl(
    private val searchService: SearchService,
) : SearchRepository {

    override suspend fun search(term: String, page: Int, perPage: Int): FResult<Search> {
        return safeApiCall { searchService.search(term = term, page = page, perPage = perPage) }.mapIfSuccess { it.map() }
    }

    override suspend fun detail(id: String): FResult<Search.Hit> {
        return safeApiCall { searchService.search(id = id) }.mapIfSuccess { it.map().hits.first() }
    }

}
