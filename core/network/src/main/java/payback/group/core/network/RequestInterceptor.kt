package payback.group.core.network

import okhttp3.Interceptor
import okhttp3.Response

internal open class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest = request
            .newBuilder()
            .url(
                request
                    .url
                    .newBuilder()
                    .addQueryParameter(name = "key", value = "42961148-9108cebfd5ae919a4db90fadc")
                    .build()
            )
            .build()

        return chain.proceed(newRequest)
    }

}