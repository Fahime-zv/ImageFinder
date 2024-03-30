package payback.group.imagefinder.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import payback.group.imagefinder.ui.component.HeadingTextComponent
import payback.group.model.Search

@Composable
fun SearchList(list: List<Search.Hit>, onItemCLickListener: (Search.Hit) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(list.size) { index ->
            SearchItemRowComponent(hit = list[index], onItemCLickListener = onItemCLickListener)
        }
    }

}


@Composable
fun SearchItemRowComponent(hit: Search.Hit, onItemCLickListener: (Search.Hit) -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .border(1.dp, Color.Black, shape = MaterialTheme.shapes.medium)
            .background(Color.Blue)
            .clickable {
                onItemCLickListener.invoke(hit)
            },
    ) {
        AsyncImage(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp),
            model = hit.previewURL,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )

//        Spacer(modifier = Modifier.size(20.dp))

        HeadingTextComponent(value ="UserName: ${hit.user}")

//        Spacer(modifier = Modifier.size(10.dp))
//
//        AuthorDetailsComponent("Tags", hit.tags)

    }
}

private fun debounceSearch(query: String, debounceTime: Long, onSearch: (String) -> Unit): Job {
    return CoroutineScope(Dispatchers.Default).launch {
        delay(debounceTime) // Adjust the delay time as needed
        onSearch(query)
    }
}


@Composable
fun SearchBox(
    onTextChanged: (String) -> Unit,
    debounceTime: Long = 500
) {

    var job by remember { mutableStateOf<Job?>(null) }
    var searchText by remember { mutableStateOf("") }

    TextField(
        value = searchText,
        onValueChange = {
            searchText = it
            job?.cancel()
            job = debounceSearch(it, debounceTime, onTextChanged)
        },
        label = { Text("Search...") },
        singleLine = true,
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .border(
                color = Color.Gray,
                width = 1.dp,
                shape = RoundedCornerShape(12.dp)
            )
    )

}
