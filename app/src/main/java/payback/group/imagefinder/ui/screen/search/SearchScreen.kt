package payback.group.imagefinder.ui.screen.search


import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import payback.group.imagefinder.architecture.ViewState
import payback.group.imagefinder.ui.component.AlertDialog
import payback.group.imagefinder.ui.component.EmptyComponent
import payback.group.imagefinder.ui.component.Loader
import payback.group.imagefinder.ui.navigation.NavigationItem
import payback.group.imagefinder.ui.theme.DialogTitle
import payback.group.imagefinder.ui.theme.NotFound
import payback.group.model.Search

@Composable
fun SearchScreen(navController: NavController) {

    val viewModel = koinViewModel<SearchViewModel>()

    val searchResult by viewModel.viewStateFlow.collectAsState()

    var selectedItem by remember { mutableStateOf<Search.Hit?>(null) }

    val openAlertDialog = remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        SearchBox(
            onTextChanged = { newText ->
                viewModel.dispatch(SearchAction.DoingSearch(newText))
            },
            onCleared = {
                // if you need you cab add action.
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
                    openAlertDialog.value = true
                    selectedItem = it
                }
            }

            is ViewState.Error -> {
                val res = (searchResult as ViewState.Error)
                Log.d(TAG, "Error ${res}")

            }

            is ViewState.Empty -> EmptyComponent(NotFound)
        }


        if (openAlertDialog.value) {
            AlertDialog(
                dialogText = DialogTitle,
                onDismissRequest = {
                    openAlertDialog.value = false
                },
                onConfirmation = {
                    openAlertDialog.value = false
                    selectedItem?.let {
                        navController.navigate("${NavigationItem.Detail.route}/${it.id}") {
                            launchSingleTop = true
                        }
                    }

                },
            )
        }
    }

}


