package payback.group.core.network.service

import org.junit.Test
import payback.group.core.network.service.base.BaseServiceTest

class SearchServiceTest :BaseServiceTest<SearchService>(SearchService::class){

    @Test
    fun list() = testResponse("json/address/DynamicAddress.json") {
        service.search()
    }
}