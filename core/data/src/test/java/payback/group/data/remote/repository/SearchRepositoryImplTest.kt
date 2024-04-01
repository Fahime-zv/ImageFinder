package payback.group.data.remote.repository

import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Test
import payback.group.core.network.model.response.SearchResponse
import payback.group.core.network.service.SearchService
import payback.group.model.Search
import payback.group.shared.FResult
import payback.group.test.kotlintest.CoroutineTest
import payback.group.test.kotlintest.DispatchedCoroutineTest
import kotlin.test.assertEquals

class SearchRepositoryImplTest : CoroutineTest by DispatchedCoroutineTest() {

    private val searchService: SearchService = mockk()
    private val searchRepository: SearchRepository = SearchRepositoryImpl(searchService)

    @Test
    fun `is search return search`() = dispatchedTest {
        coEvery {
            searchService.search(
                term = "term",
                page = 1,
                perPage = 1
            )
        } returns mockResponseSearch()

        assertEquals(
            expected = FResult.Success(mockSearch()),
            actual = searchRepository.search(term = "term", page = 1, perPage = 1)
        )

    }

    @Test
    fun `is search return error local`() = dispatchedTest {
        val exception = RuntimeException("An Exception")
        coEvery { searchService.search(term = "term", page = 1, perPage = 1) } throws exception
        assertEquals(
            expected = FResult.Error.Local("An Exception", exception),
            actual = searchRepository.search(term = "term", page = 1, perPage = 1)
        )
    }

    @Test
    fun `is detail return search`() = dispatchedTest {
        coEvery {
            searchService.search(
                id = "mock_id"
            )
        } returns mockResponseSearch()

        assertEquals(
            expected = FResult.Success(mockHit()),
            actual = searchRepository.detail(id = "mock_id")
        )

    }

    @Test
    fun `is detail return error local`() = dispatchedTest {
        val exception = RuntimeException("An Exception")
        coEvery { searchService.search(id = "mock_id") } throws exception
        assertEquals(
            expected = FResult.Error.Local("An Exception", exception),
            actual = searchRepository.detail(id = "mock_id")
        )
    }

    private fun mockResponseSearch() = SearchResponse(
        total = 1,
        totalHits = 12,
        hits = listOf(mockResponseHit())
    )

    private fun mockResponseHit() = SearchResponse.Hit(
        id = 1,
        pageURL = "Mock_url",
        previewURL = "Mock_url",
        largeImageURL = "Mock_url",
        likes = 12,
        downloads = 12,
        comments = 12,
        tags = "tag,tag,tag",
        user = "Mock_User"
    )

    private fun mockSearch() = Search(
        total = 1,
        totalHits = 12,
        hits = listOf(mockHit())
    )

    private fun mockHit() = Search.Hit(
        id = 1,
        pageURL = "Mock_url",
        previewURL = "Mock_url",
        largeImageURL = "Mock_url",
        likes = 12,
        downloads = 12,
        comments = 12,
        tags = "tag,tag,tag",
        user = "Mock_User"
    )


}