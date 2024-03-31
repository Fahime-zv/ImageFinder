package payback.group.imagefinder.ui.screen.search


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import payback.group.imagefinder.ui.component.AlertDialog
import payback.group.imagefinder.ui.component.EmptyComponent
import payback.group.imagefinder.ui.component.Loader
import payback.group.imagefinder.ui.navigation.NavigationItem
import payback.group.imagefinder.ui.theme.DialogTitle
import payback.group.imagefinder.ui.theme.ErrorOccured
import payback.group.imagefinder.ui.theme.NotFound
import payback.group.model.Search

@Composable
fun SearchScreen(navController: NavController) {

    val viewModel = koinViewModel<SearchViewModel>()

    var selectedItem by remember { mutableStateOf<Search.Hit?>(null) }

    val openAlertDialog = remember { mutableStateOf(false) }

    val hits: LazyPagingItems<Search.Hit> = viewModel.pagingFlow.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SearchBox(
            onTextChanged = { newText ->
                viewModel.dispatch(SearchAction.DoingSearch(newText))
            },
            onCleared = {
                // if you need you cab add action.
            }
        )

        when (hits.loadState.refresh) {
            LoadState.Loading -> {
                Loader()
            }

            is LoadState.Error -> {
                EmptyComponent(text = ErrorOccured)
            }

            else -> {
                if (hits.itemCount == 0) EmptyComponent(text = NotFound)

                LazyColumn {

                    items(hits.itemCount) {
                        val item = hits[it]
                        if (item != null) {
                            ItemRowComponent(item) { hit ->
                                openAlertDialog.value = true
                                selectedItem = hit
                            }
                        }
                    }
                }
            }
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


