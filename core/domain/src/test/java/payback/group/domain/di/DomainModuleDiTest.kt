package payback.group.domain.di

import android.content.Context
import io.mockk.mockk
import org.koin.test.KoinTest
import org.koin.test.check.checkKoinModules
import kotlin.test.Test

class DomainModuleDiTest : KoinTest {

    @Test
    fun verifyKoinApp() {
        checkKoinModules(listOf(domainDiModule)) {
            withInstance(mockk<Context>())
        }
    }

}