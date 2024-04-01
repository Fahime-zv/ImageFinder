package payback.group.core.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

@OptIn(ExperimentalSerializationApi::class)
internal val jsonFormatter = Json {
    encodeDefaults = false
    explicitNulls = false
    ignoreUnknownKeys = true
}

internal val jsonConverterFactory = jsonFormatter.asConverterFactory("application/json".toMediaType())