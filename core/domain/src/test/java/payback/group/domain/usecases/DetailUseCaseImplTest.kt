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

class DetailUseCaseImplTest :
    CoroutineTest by DispatchedCoroutineTest() {

    private val mockSearchRepository: SearchRepository = mockk()
    private val detailUseCase: DetailUseCase = DetailUseCaseImpl(
        searchRepository = mockSearchRepository,
        dispatcherProvider = dispatcherRule.dispatchers
    )

    @Test
    fun `is invoke calls SearchRepository's detail`() = dispatchedTest {
        val expected: FResult<Search.Hit> = mockk()
        coEvery {
            mockSearchRepository.detail(
                id = "mock_id"
            )
        } returns expected
        assertEquals(
            expected = expected,
            actual = detailUseCase.invoke(
                id = "mock_id"
            )
        )
    }
}