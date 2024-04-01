package payback.group.test.kotlintest

import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import kotlin.coroutines.CoroutineContext


interface CoroutineTest {


    @get:Rule
    val dispatcherRule: MainDispatcherRule

    fun dispatchedTest(
        context: CoroutineContext = dispatcherRule.dispatchers.main,
        block: suspend TestScope.() -> Unit
    ) = runTest(context, testBody = block)

}