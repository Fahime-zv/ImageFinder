package payback.group.shared.kotlin

import payback.group.shared.FResult
import kotlin.coroutines.cancellation.CancellationException

suspend fun <T> suspendSafeDataResult(block: suspend () -> FResult<T>): FResult<T> {
    return try {
        block()
    } catch (e: CancellationException) {
        // Propagate cancellation exceptions to maintain structured concurrency.
        throw e
    } catch (e: Exception) {
        FResult.Error.Local(e.localizedMessage, cause = e)
    }
}