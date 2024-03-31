package payback.group.core.network

import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import payback.group.core.network.mock.MockTestService
import kotlin.test.Test

class ServiceProviderTest {

    private lateinit var serviceProvider: ServiceProvider

    @Before
    fun before() {
        serviceProvider = ServiceProvider()
    }

    @Test
    fun `is service instance not null`() {
        assertNotNull(serviceProvider.provide(MockTestService::class))
    }

}