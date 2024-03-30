package payback.group.core.network.service

import payback.group.core.network.model.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("api/")
    suspend fun search(
        @Query("q") term: String?=null,
        @Query("id") id: String?=null,
    ): SearchResponse

}