package payback.group.imagefinder.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import payback.group.imagefinder.ui.component.HeadingTextComponent
import payback.group.model.Search

@Composable
fun DetailComponent(hit: Search.Hit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = hit.largeImageURL,
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