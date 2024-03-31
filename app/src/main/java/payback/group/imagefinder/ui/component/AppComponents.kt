package payback.group.imagefinder.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import payback.group.imagefinder.ui.theme.NotFound

@Composable
fun Loader() {
    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(50.dp).padding(10.dp), color = Color.Blue)
    }
}

@Composable
fun HeadingTextComponent(value: String,modifier: Modifier=Modifier) {
    Text(
        modifier = modifier,
        text = value,
        style = TextStyle(fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Bold)
    )
}

@Composable
fun NormalTextComponent(value: String) {
    Text(
        modifier = Modifier,
        text = value,
        style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Light, fontFamily = FontFamily.Monospace, color = Color.Gray)
    )
}

@Composable
fun BeautyTextComponent(value: String,modifier: Modifier=Modifier) {
    Text(
        modifier = modifier,
        text = value,
        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, fontFamily = FontFamily.Serif, color = Color.Black)
    )
}

@Composable
fun EmptyComponent(text:String) {
    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HeadingTextComponent(value = text)
    }
}
