package payback.group.test.kotlintest

import payback.group.shared.DispatcherProvider


class DispatchedCoroutineTest(
    dispatchers: DispatcherProvider = TestDispatcherProvider()
) : CoroutineTest {

    override val dispatcherRule: MainDispatcherRule = MainDispatcherRule(dispatchers)
}