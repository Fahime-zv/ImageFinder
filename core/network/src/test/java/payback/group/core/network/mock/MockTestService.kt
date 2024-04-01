package payback.group.core.network.mock

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET

interface MockTestService {

    @GET(MOCK_TEST_SERVICE_BASE_RESPONSE_URL)
    suspend fun testBase(): ResponseModel

    @Serializable
    data class ResponseModel(
        @SerialName("id") val id: Int,
        @SerialName("Image") val image: String
    )

}

const val MOCK_TEST_SERVICE_BASE_RESPONSE_URL = "base/response"

