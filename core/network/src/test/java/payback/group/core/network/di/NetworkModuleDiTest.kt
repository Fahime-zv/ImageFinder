package payback.group.core.network.di

import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkKoinModules

class NetworkModuleDiTest : KoinTest {

    @Test
    fun verifyKoinApp() {
        checkKoinModules(listOf(networkDiModule))
    }

}