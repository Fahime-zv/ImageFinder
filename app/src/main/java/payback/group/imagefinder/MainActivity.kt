package payback.group.imagefinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import payback.group.imagefinder.ui.navigation.AppNavigationGraph
import payback.group.imagefinder.ui.theme.ImageFinderTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ImageFinderTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    ImageFinderAppEntryPoint()
                }
            }
        }

    }

    @Composable
    fun ImageFinderAppEntryPoint() {
        AppNavigationGraph()
    }
}

