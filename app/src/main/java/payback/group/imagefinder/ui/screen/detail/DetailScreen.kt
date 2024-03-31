package payback.group.imagefinder.ui.screen.detail

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import payback.group.imagefinder.architecture.ViewState
import payback.group.imagefinder.ui.component.HeadingTextComponent
import payback.group.imagefinder.ui.component.Loader
import payback.group.imagefinder.ui.theme.ErrorOccured

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavController, itemId: String?) {

    val viewModel = getViewModel<DetailViewModel>(
        parameters = { parametersOf(itemId) }
    )

    val state by viewModel.viewStateFlow.collectAsState()

    Scaffold(
        topBar = { MyTopAppBar(onBackClick = { navController.navigateUp() }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            when (state) {
                is ViewState.Loading -> {
                    Log.d(ContentValues.TAG, "Loading ")
                    Loader()
                }

                is ViewState.Success -> {
                    val res = (state as ViewState.Success).model
                    Log.d(ContentValues.TAG, "Success $res ")
                    DetailComponent(res)
                }

                is ViewState.Error -> {
                    val res = (state as ViewState.Error)
                    Log.d(ContentValues.TAG, "Error $res")
                    HeadingTextComponent(value = ErrorOccured)
                }

                is ViewState.Empty -> {
                    //Nothing
                }
            }
        }
    }
}

