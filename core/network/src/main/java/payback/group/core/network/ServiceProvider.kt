package payback.group.core.network

import okhttp3.logging.HttpLoggingInterceptor
import payback.group.core.network.ServiceCreator.Builder.Companion.buildDefaultServiceCreator
import payback.group.core.network.service.SearchService
import kotlin.reflect.KClass

internal class ServiceProvider{

    private val serviceCreator: ServiceCreator

    init {
        val logLevel = HttpLoggingInterceptor.Level.BODY
        val requestInterceptor = RequestInterceptor()
        serviceCreator = buildDefaultServiceCreator(logLevel,requestInterceptor)
    }

    fun <T:Any> provide(service: KClass<T>):T=serviceCreator.create(service.java)
}
