package payback.group.imagefinder.ui.screen.detail

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import payback.group.imagefinder.architecture.ViewState
import payback.group.imagefinder.ui.component.HeadingTextComponent
import payback.group.imagefinder.ui.component.Loader

@Composable
fun DetailScreen( navController: NavController,itemId:String?) {

    val viewModel = getViewModel<DetailViewModel>(
        parameters = { parametersOf(itemId) }
    )

    val state by viewModel.viewStateFlow.collectAsState()


    when (state) {
        is ViewState.Loading -> {
            Log.d(ContentValues.TAG, "Loading ")
            Loader()
        }

        is ViewState.Success -> {
            val res = (state as ViewState.Success).model
            Log.d(ContentValues.TAG, "Success${res} ")
            DetailComponent(res)
        }

        is ViewState.Error -> {
            val res = (state as ViewState.Error)
            Log.d(ContentValues.TAG, "Error ${res}")

        }

        is ViewState.Empty ->{
            //Nothing
        }
    }


}

