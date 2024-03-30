package payback.group.imagefinder.ui.screen.search


import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import payback.group.imagefinder.architecture.ViewState
import payback.group.imagefinder.ui.component.HeadingTextComponent
import payback.group.imagefinder.ui.component.Loader
import payback.group.imagefinder.ui.navigation.NavigationItem
import payback.group.model.Search

@Composable
fun SearchScreen(navController: NavController) {

    val viewModel = koinViewModel<SearchViewModel>()

    val searchResult by viewModel.viewStateFlow.collectAsState()

    var selectedItem by remember { mutableStateOf<Search.Hit?>(null) }


    Column(modifier = Modifier.fillMaxSize()) {
        SearchBox(
            onTextChanged = { newText ->
                viewModel.dispatch(SearchAction.DoingSearch(newText))
            }
        )

        when (searchResult) {

            is ViewState.Loading -> {
                Log.d(TAG, "Loading ")
                Loader()
            }

            is ViewState.Success -> {
                val res = (searchResult as ViewState.Success).model
                Log.d(TAG, "Success${res.total} ")
                SearchList(list = res.hits) {
                    selectedItem = it
                }
            }

            is ViewState.Error -> {
                val res = (searchResult as ViewState.Error)
                Log.d(TAG, "Error ${res}")

            }

            is ViewState.Empty ->

                HeadingTextComponent(value = "Nothingg...")
        }

        selectedItem?.let {
//            BottomSheetDialogWithButtons(onDismiss = {}, onNoClicked = {}, onYesClicked = {})
            navController.navigate("${NavigationItem.Detail.route}/${it.id}") {
                launchSingleTop = true
                // Pass model as argument
//                putParcelable("model", model)
            }
        }

    }


}


