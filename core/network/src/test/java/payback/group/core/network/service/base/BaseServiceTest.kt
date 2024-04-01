package payback.group.core.network.service.base

import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import payback.group.core.network.ServiceCreator
import payback.group.core.network.jsonConverterFactory
import payback.group.test.kotlintest.CoroutineTest
import payback.group.test.kotlintest.DispatchedCoroutineTest
import kotlin.reflect.KClass

open class BaseServiceTest<T : Any>(private val clazz: KClass<T>) :
    CoroutineTest by DispatchedCoroutineTest() {

    private lateinit var mockWebServer: MockWebServer
    protected lateinit var service: T

    @Before
    fun before() {
        mockWebServer = MockWebServer()
        service = ServiceCreator.Builder(
            baseUrl = mockWebServer.url("/").toString(),
            converterFactory = jsonConverterFactory
        ).build().create(clazz.java)
    }

    @After
    fun after() {
        mockWebServer.shutdown()
    }

    protected fun testResponse(resourceFile: String, serviceCall: suspend () -> Unit) =
        dispatchedTest {
            val responseString = readJsonFromResources(resourceFile)
            mockWebServer.enqueue(MockResponse().setBody(responseString))
            try {
                serviceCall.invoke()
                assert(true)
            } catch (e: Exception) {
                assert(false) { e.localizedMessage ?: "" }
            }
        }

    protected fun testRequestPath(
        expectedRequestPath: String,
        serviceCall: suspend () -> Unit
    ) = dispatchedTest {
        mockWebServer.enqueue(MockResponse())
        try {
            serviceCall.invoke()
        } catch (e: Exception) {
            // The service call expects a response, but the response is an empty MockResponse, causing an exception.
        }
        assertEquals("/$expectedRequestPath", mockWebServer.takeRequest().path)
    }

    private fun readJsonFromResources(resource: String): String {
        return javaClass.classLoader!!
            .getResource(resource)
            .openStream()
            .bufferedReader()
            .use { it.readText() }
    }
}