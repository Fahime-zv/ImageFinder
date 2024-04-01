package payback.group.test.kotlintest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import payback.group.shared.DispatcherProvider


class MainDispatcherRule(
    val dispatchers: DispatcherProvider = TestDispatcherProvider()
) : TestWatcher() {

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(dispatchers.main)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
    }

}