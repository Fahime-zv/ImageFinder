package payback.group.domain.usecases

import io.mockk.coEvery
import io.mockk.mockk
import payback.group.data.remote.repository.SearchRepository
import payback.group.model.Search
import payback.group.shared.FResult
import payback.group.test.kotlintest.CoroutineTest
import payback.group.test.kotlintest.DispatchedCoroutineTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SearchUseCaseImplTest :
    CoroutineTest by DispatchedCoroutineTest() {

    private val mockSearchRepository: SearchRepository = mockk()

    private val searchImageUseCase: SearchImageUseCase = SearchImageUseCaseImpl(
        searchRepository = mockSearchRepository,
        dispatcherProvider = dispatcherRule.dispatchers
    )

    @Test
    fun `is invoke calls SearchRepository's search`() = dispatchedTest {
        val expected: FResult<Search> = mockk()
        coEvery {
            mockSearchRepository.search(
                term = "mock_term",
                page = 1,
                perPage = 1
            )

        } returns expected
        assertEquals(
            expected = expected,
            actual = searchImageUseCase.invoke(
                term = "mock_term",
                page = 1,
                perPage = 1
            )
        )
    }
}