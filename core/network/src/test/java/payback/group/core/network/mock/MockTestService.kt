package payback.group.core.network.mock

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MockTestService {

    @GET(MOCK_TEST_SERVICE_LIST_URL)
    suspend fun list(): List<ResponseModel>

    @POST(MOCK_TEST_SERVICE_DETAIL_URL)
    suspend fun detail(@Body requestModel: RequestModel): ResponseModel

    @GET(MOCK_TEST_SERVICE_BASE_RESPONSE_URL)
    suspend fun testBase(): ResponseModel

    @kotlinx.serialization.Serializable
    data class ResponseModel(
        @SerialName("id") val id: Int,
        @SerialName("name") val name: String
    )

    @Serializable
    data class RequestModel(@SerialName("id") val id: Int)

}

const val MOCK_TEST_SERVICE_LIST_URL = "list"
const val MOCK_TEST_SERVICE_DETAIL_URL = "detail"
const val MOCK_TEST_SERVICE_BASE_RESPONSE_URL = "base/response"

val TEST_API_SINGLE_RESPONSE_JSON = """
    {
        "id": 1,
        "name": "Parham"
    }
""".trimIndent()

val TEST_API_LIST_RESPONSE_JSON = """
    [
        {
          "id": 1,
          "name": "Parham"
        },
        {
          "id": 2,
          "name": "Saedeh"
        },
        {
          "id": 3,
          "name": "Bardia"
        }
    ]
""".trimIndent()

