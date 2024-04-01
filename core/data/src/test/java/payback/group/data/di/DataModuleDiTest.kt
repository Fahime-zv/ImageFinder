package payback.group.data.di

import android.content.Context
import io.mockk.mockk
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkKoinModules

class DataModuleDiTest : KoinTest {

    @Test
    fun verifyKoinApp() {
        checkKoinModules(listOf(dataDiModule)) {
            withInstance(mockk<Context>())
        }
    }

}