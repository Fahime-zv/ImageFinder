package payback.group.core.network.mock

import okhttp3.mockwebserver.MockResponse

const val TEST_BASE_URL: String = "https://pixabay.com/"

private fun baseResponseString(
    code: Int,
    message: String?,
    messageType: Int,
    data: String?
): String {
    return """
        {
            "meta": {
                "code": $code,
                "msg": "$message",
                "msgType": $messageType
            },
            "data": ${data ?: "null"}
        }
    """.trimIndent()
}

fun mockBaseResponseWithoutData(
    code: Int,
    message: String?,
    messageType: Int
): MockResponse {
    return mockBaseResponse(code = code, message = message, messageType = messageType, data = null)
}
private fun mockBaseResponse(
    code: Int,
    message: String?,
    messageType: Int,
    data: String?
): MockResponse {
    return MockResponse()
        .setResponseCode(code)
        .setBody(baseResponseString(code, message, messageType, data))
}