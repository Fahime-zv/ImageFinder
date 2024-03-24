package payback.group.core.network

import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor

internal class BeautifyLogger : HttpLoggingInterceptor.Logger {

    private val beatifyFormatter = Json { prettyPrint = true }

    override fun log(message: String) {
        Platform.get().log(message.toJsonString())
    }

    private fun String.toJsonString(): String {
        return try {
            beatifyFormatter.encodeToString(
                value = beatifyFormatter.parseToJsonElement(this)
            )
        } catch (e: SerializationException) {
            this
        } catch (e: IllegalArgumentException) {
            this
        }
    }

}