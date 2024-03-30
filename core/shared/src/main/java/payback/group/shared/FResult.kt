package payback.group.shared

sealed class FResult<out T> {

    data class Success<T>(val value: T) : FResult<T>()

    sealed class Error(
        open val message: String?,
        open val cause: Throwable?
    ) : FResult<Nothing>() {

        data class Local(
            override val message: String?,
            override val cause: Throwable?
        ) : Error(message, cause) {
            override fun toString() = "Error.Local(message=$message, cause=$cause)"
        }

        data class Remote(
            val httpCode: Int,
            override val message: String?,
            override val cause: Throwable?,
        ) : Error(message, cause) {
            override fun toString() =
                "Error.Remote(httpCode=$httpCode, message=$message, cause=$cause)"
        }
    }

}


@Suppress("unused")
inline val FResult<*>.isSuccess: Boolean
    get() = this is FResult.Success<*>

@Suppress("unused")
inline val FResult<*>.isError: Boolean
    get() = this is FResult.Error

@Suppress("unused")
inline val FResult<*>.isLocalError: Boolean
    get() = this is FResult.Error.Local


@Suppress("unused")
inline val FResult<*>.isRemoteError: Boolean
    get() = this is FResult.Error.Remote


@Suppress("unused")
inline fun <T> FResult<T>.doOnSuccess(onSuccess: (value: T) -> Unit): FResult<T> {
    if (this is FResult.Success) onSuccess(value)
    return this
}

@Suppress("unused")
inline fun <T> FResult<T>.doOnError(onError: (httpCode: Int?, message: String?, cause: Throwable?) -> Unit): FResult<T> {
    if (this is FResult.Error.Local)
        onError(null, message, cause)
    else if (this is FResult.Error.Remote)
        onError(httpCode, message, cause)
    return this
}

@Suppress("unused")
inline fun <R, T> FResult<T>.fold(
    onSuccess: (value: T) -> R,
    onError: (httpCode: Int?, message: String?, cause: Throwable?) -> R
): R {
    return when (this) {
        is FResult.Success -> onSuccess(value)
        is FResult.Error.Local -> onError(null, message, cause)
        is FResult.Error.Remote -> onError(httpCode, message, cause)
    }
}

@Suppress("unused")
inline fun <R, T> FResult<T>.mapIfSuccess(transform: (value: T) -> R): FResult<R> {
    return when (this) {
        is FResult.Success -> FResult.Success(transform(value))
        is FResult.Error.Local -> FResult.Error.Local(message, cause)
        is FResult.Error.Remote -> FResult.Error.Remote(httpCode, message, cause)
    }
}


@Suppress("unused")
inline fun <R, T : R> FResult<T>.mapIfError(transform: (httpCode: Int?, message: String?, cause: Throwable?) -> R): FResult<R> {
    return when (this) {
        is FResult.Success -> this
        is FResult.Error.Local -> FResult.Success(transform(null, message, cause))
        is FResult.Error.Remote -> FResult.Success(transform(httpCode, message, cause))
    }
}


@Suppress("unused")
fun <T> FResult<T>.getOrNull(): T? = (this as? FResult.Success)?.value


@Suppress("unused")
fun <T> FResult<T>.getOrThrow(): T {
    (this as? FResult.Error)?.cause?.run { throw this }
    return (this as FResult.Success).value
}


@Suppress("unused")
fun <R, T : R> FResult<T>.getOrDefault(defaultValue: R): R {
    return when (this) {
        is FResult.Success -> value
        is FResult.Error -> defaultValue
    }
}


@Suppress("unused")
inline fun <R, T : R> FResult<T>.getOrElse(onError: (httpCode: Int?, message: String?, cause: Throwable?) -> R): R {
    return when (this) {
        is FResult.Success -> value
        is FResult.Error.Local -> onError(null, message, cause)
        is FResult.Error.Remote -> onError(httpCode, message, cause)
    }
}


@Suppress("unused")
fun FResult<*>.exceptionOrNull(): Throwable? = (this as? FResult.Error)?.cause
