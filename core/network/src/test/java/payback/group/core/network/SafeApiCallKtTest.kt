package payback.group.core.network

import io.mockk.coEvery
import io.mockk.mockk
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import payback.group.core.network.mock.MockTestService
import payback.group.core.network.mock.mockBaseResponseWithoutData
import payback.group.shared.FResult
import payback.group.test.kotlintest.CoroutineTest
import payback.group.test.kotlintest.DispatchedCoroutineTest
import retrofit2.HttpException
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNull

class SafeApiCallTest : CoroutineTest by DispatchedCoroutineTest() {

    private val mockWebServer = MockWebServer()
    private lateinit var testService: MockTestService

    @Before
    fun before() {
        testService = ServiceCreator.Builder(
            baseUrl = mockWebServer.url("/").toString(),
            converterFactory = jsonConverterFactory
        ).build().create(MockTestService::class.java)
    }

    @After
    fun after() {
        mockWebServer.shutdown()
    }

    @Test
    fun `is return Success`() = dispatchedTest {
        val expectedResponse = MockTestService.ResponseModel(id = 1,image="http://url")
        val mockService: MockTestService = mockk()
        coEvery { mockService.testBase() } returns expectedResponse
        val actual = safeApiCall { mockService.testBase() }
        assertEquals(expected = FResult.Success(expectedResponse), actual = actual)
    }

    @Test
    fun `is return ErrorLocal when response not received`() {
        dispatchedTest {
            val mockService: MockTestService = mockk()
            coEvery { mockService.testBase() } throws RuntimeException("An Exception")
            val actual = safeApiCall { mockService.testBase() }
            assertIs<FResult.Error.Local>(actual)
        }
    }

    @Test
    fun `is return ErrorLocal with correct message when response not received`() = dispatchedTest {
        val mockService: MockTestService = mockk()
        coEvery { mockService.testBase() } throws RuntimeException("An Exception")
        val actual = safeApiCall { mockService.testBase() }
        assertEquals(
            expected = "An Exception",
            actual = (actual as? FResult.Error.Local)?.message
        )
    }

    @Test
    fun `is return ErrorLocal with correct cause when response not received`() = dispatchedTest {
        val expectedCause = RuntimeException("An Exception")
        val mockService: MockTestService = mockk()
        coEvery { mockService.testBase() } throws expectedCause
        val actual = safeApiCall { mockService.testBase() }
        assertEquals(
            expected = expectedCause,
            actual = (actual as? FResult.Error.Local)?.cause
        )
    }

    // Creating an instance of HttpException not easy, so here the MockWebServer used to generate HttpException
    @Test
    fun `is return ErrorRemote when response is not 200 and contains valid errorBody`() {
        dispatchedTest {
            mockWebServer.enqueue(errorResponse(message = null))
            val actual = safeApiCall { testService.testBase() }
            assertIs<FResult.Error.Remote>(actual)
        }
    }

    @Test
    fun `is return ErrorRemote with correct httpCode when response is not 200 and contains valid errorBody`() =
        dispatchedTest {
            mockWebServer.enqueue(errorResponse(message = null))
            val actual = safeApiCall { testService.testBase() }
            assertEquals(expected = 500, actual = (actual as? FResult.Error.Remote)?.httpCode)
        }

    @Test
    fun `is return ErrorRemote with correct cause response is not 200 and contains valid errorBody`() {
        dispatchedTest {
            mockWebServer.enqueue(errorResponse(message = null))
            val actual = safeApiCall { testService.testBase() }
            assertIs<HttpException>((actual as? FResult.Error.Remote)?.cause)
        }
    }

    @Test
    fun `is return ErrorRemote with correct httpCode when response is not 200 and contains invalid errorBody`() =
        dispatchedTest {
            mockWebServer.enqueue(MockResponse().setResponseCode(500))
            val actual = safeApiCall { testService.testBase() }
            assertEquals(expected = 500, actual = (actual as? FResult.Error.Remote)?.httpCode)
        }



    @Test
    fun `is return ErrorRemote with correct cause response is not 200 and contains invalid errorBody`() {
        dispatchedTest {
            mockWebServer.enqueue(MockResponse().setResponseCode(500))
            val actual = safeApiCall { testService.testBase() }
            assertIs<HttpException>((actual as? FResult.Error.Remote)?.cause)
        }
    }


    private fun errorResponse(
        code: Int = 500,
        message: String?,
        messageType: Int = 1
    ): MockResponse {
        return mockBaseResponseWithoutData(
            code = code,
            message = message,
            messageType = messageType
        )
    }

}

