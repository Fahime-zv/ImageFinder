package payback.group.core.network

import payback.group.core.network.mock.MockTestService
import payback.group.core.network.mock.TEST_BASE_URL
import kotlin.test.Test
import kotlin.test.assertEquals


class ServiceCreatorTest {

    private val serviceCreator = ServiceCreator.Builder(
        baseUrl = TEST_BASE_URL,
        converterFactory = jsonConverterFactory
    ).build()

    @Test
    fun `is baseUrl equals to builder baseUrl`() {
        assertEquals(
            expected = TEST_BASE_URL,
            actual = serviceCreator.baseUrl
        )
    }

    @Test
    fun `is service object create correctly`() {
        try {
            serviceCreator.create(MockTestService::class.java)
            assert(true)
        } catch (e: Exception){
            assert(false) { e.localizedMessage ?: "" }
        }
    }

}