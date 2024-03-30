package payback.group.data.remote.mapper.responseToModel

import payback.group.core.network.model.response.SearchResponse
import payback.group.model.Search

internal fun SearchResponse.map()=Search(total,totalHits, hits =hits.map { it.map() })


internal  fun SearchResponse.Hit.map()=Search.Hit(id, pageURL, previewURL, largeImageURL, likes, downloads, comments, tags, user)