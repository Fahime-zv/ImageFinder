package payback.group.imagefinder.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import payback.group.imagefinder.ui.component.BeautyTextComponent
import payback.group.imagefinder.ui.component.HeadingTextComponent
import payback.group.imagefinder.ui.component.NormalTextComponent
import payback.group.imagefinder.ui.theme.MainColor
import payback.group.imagefinder.ui.theme.SearcHint
import payback.group.model.Search


@Composable
fun SearchList(list: List<Search.Hit>, onItemCLickListener: (Search.Hit) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(list.size) { index ->
            ItemRowComponent(hit = list[index], onItemCLickListener = onItemCLickListener)
        }
    }

}

@Composable
fun ItemRowComponent(hit: Search.Hit, onItemCLickListener: (Search.Hit) -> Unit) {
    Column(
        modifier = Modifier
            .padding(20.dp,8.dp,20.dp,8.dp)
            .fillMaxWidth()
            .background(color = MainColor, shape = RoundedCornerShape(10))
            .clickable { onItemCLickListener.invoke(hit) },

        ) {

        Row {

            AsyncImage(
                modifier = Modifier
                    .padding(20.dp)
                    .clip(RoundedCornerShape(50))
                    .height(50.dp)
                    .width(50.dp),
                model = hit.previewURL,
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )

            Column {

                HeadingTextComponent(
                    value = hit.user,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(0.dp, 25.dp, 0.dp, 0.dp)
                )

                NormalTextComponent(value = "Comments:${hit.comments}")
            }
        }

        BeautyTextComponent(
            value = "Tags: ** ${hit.tags} **",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(20.dp, 0.dp, 20.dp, 20.dp)
        )

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
    onCleared:()->Unit,
    debounceTime: Long = 500,
) {
    var job by remember { mutableStateOf<Job?>(null) }
    var searchText by remember { mutableStateOf("fruits") }

    TextField(
        value = searchText,
        onValueChange = {
            searchText = it
            job?.cancel()
            job = debounceSearch(it, debounceTime, onTextChanged)
        },
        singleLine = true,
        label = { Text(SearcHint) },
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(30.dp),
        trailingIcon = {
            if (searchText.isNotBlank()) {
                IconButton(
                    onClick = {
                        onCleared.invoke()
                        searchText = ""
                    }) {
                    Icon(imageVector = Icons.Rounded.Clear, tint = Color.Black, contentDescription = "", modifier = Modifier.padding(10.dp))
                }
            }
        },
        leadingIcon = {
            Icon(modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp), imageVector = Icons.Filled.Search, contentDescription = null, tint = Color.Black)
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = MainColor,
            unfocusedContainerColor = MainColor,
        )
    )

}
