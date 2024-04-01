package payback.group.core.network

import payback.group.shared.FResult
import retrofit2.HttpException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): FResult<T> {
    return try {
        val response = apiCall.invoke()
        FResult.Success(response)
    } catch (e: HttpException) {
        FResult.Error.Remote(
            httpCode = e.code(),
            message = e.response()?.errorBody()?.source()?.readUtf8(),
            cause = e
        )
    } catch (e: Exception) {
        FResult.Error.Local(message = e.localizedMessage, cause = e)
    }
}
