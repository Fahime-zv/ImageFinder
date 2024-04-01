package payback.group.core.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.*
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


const val BASE_URL: String = "https://pixabay.com"


internal class ServiceCreator private constructor(private val builder: Builder) {

    private var retrofit: Retrofit
    val baseUrl: String
        get() = retrofit.baseUrl().toString()


    init {
        val okHttpClient = OkHttpClient.Builder().apply {
            followRedirects(builder.followRedirects)
            followSslRedirects(builder.followRedirects)
            retryOnConnectionFailure(builder.retryOnConnectionFailure)
            connectTimeout(builder.timeout, TimeUnit.SECONDS)
            writeTimeout(builder.timeout, TimeUnit.SECONDS)
            readTimeout(builder.timeout, TimeUnit.SECONDS)
            builder.loggingInterceptor?.let { addNetworkInterceptor(it) }
            builder.interceptors.forEach { addInterceptor(it) }
        }.build()

        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(builder.converterFactory)
            .baseUrl(builder.baseUrl)
            .build()
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }


    class Builder(
        val baseUrl: String,
        val converterFactory: Converter.Factory,
    ) {
        var followRedirects = true
            private set
        var retryOnConnectionFailure = true
            private set
        var timeout = 30L
            private set
        var loggingInterceptor: HttpLoggingInterceptor? = null
            private set
        var interceptors = mutableListOf<Interceptor>()
            private set

        fun followRedirects(follow: Boolean) = apply {
            followRedirects = follow
        }

        fun retryOnConnectionFailure(retry: Boolean) = apply {
            retryOnConnectionFailure = retry
        }

        fun timeout(seconds: Long) = apply {
            timeout = seconds
        }

        fun loggingInterceptor(interceptor: HttpLoggingInterceptor) = apply {
            loggingInterceptor = interceptor
        }

        fun addInterceptor(interceptor: Interceptor) = apply {
            interceptors.add(interceptor)
        }

        fun build() = ServiceCreator(this)

        companion object {

            internal fun buildDefaultServiceCreator(
                logLevel: Level,
                vararg interceptors: Interceptor
            ): ServiceCreator {
                val builder = Builder(
                    baseUrl = BASE_URL,
                    converterFactory = jsonConverterFactory
                )
                builder.followRedirects(true)
                builder.retryOnConnectionFailure(true)
                builder.timeout(seconds = 30000)
                builder.loggingInterceptor(
                    HttpLoggingInterceptor(logger = BeautifyLogger()).apply { level = logLevel }
                )
                interceptors.forEach { builder.addInterceptor(it) }
                return builder.build()
            }
        }
    }

}