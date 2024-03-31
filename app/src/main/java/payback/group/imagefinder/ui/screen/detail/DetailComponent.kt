package payback.group.imagefinder.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import payback.group.imagefinder.R
import payback.group.imagefinder.ui.component.BeautyTextComponent
import payback.group.imagefinder.ui.component.HeadingTextComponent
import payback.group.imagefinder.ui.component.NormalTextComponent
import payback.group.imagefinder.ui.theme.MainColor
import payback.group.model.Search

@Composable
fun DetailComponent(hit: Search.Hit) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight(0.5f).fillMaxWidth()
                .clip(RoundedCornerShape(0, 0, 10, 10)),
            model = hit.largeImageURL,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            placeholder =painterResource(id = R.drawable.ic_launcher_foreground)
        )

        HeadingTextComponent(
            value = "UserName: ${hit.user}", modifier = Modifier
                .wrapContentSize()
                .padding(20.dp,20.dp,20.dp,0.dp)
        )

        BeautyTextComponent(
            value = "Tags: ** ${hit.tags} **",
            modifier = Modifier
                .wrapContentSize()
                .padding(20.dp)
        )
        NormalTextComponent(value = "Likes:${hit.likes}")
        Spacer(modifier = Modifier.size(20.dp))
        NormalTextComponent(value = "Downloads:${hit.downloads}")
        Spacer(modifier = Modifier.size(20.dp))
        NormalTextComponent(value = "Comments:${hit.comments}")

    }
}

@Composable
fun MyTopAppBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MainColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onBackClick.invoke() }
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Detail Page", color = Color.Black)
    }
}
