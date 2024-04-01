package payback.group.test.kotlintest

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import payback.group.shared.DispatcherProvider


class TestDispatcherProvider @OptIn(ExperimentalCoroutinesApi::class) constructor(
    mainDispatcher: TestDispatcher,
    defaultDispatcher: TestDispatcher,
    ioDispatcher: TestDispatcher,
) : DispatcherProvider {

    override val main: TestDispatcher = mainDispatcher
    override val default: TestDispatcher = defaultDispatcher
    override val io: TestDispatcher = ioDispatcher


    constructor(dispatcher: TestDispatcher = StandardTestDispatcher()) : this(dispatcher, dispatcher, dispatcher)
}