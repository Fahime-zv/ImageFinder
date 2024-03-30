package payback.group.imagefinder.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetDialogWithButtons(
    onDismiss: () -> Unit,
    onYesClicked: () -> Unit,
    onNoClicked: () -> Unit,
) {

    val modalBottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if (modalBottomSheetState.isVisible.not()) {//<-- add this condition to add sheet only when it is visible
        ModalBottomSheet(
            onDismissRequest = { onDismiss()
                scope.launch { modalBottomSheetState.show() }            },
            sheetState = modalBottomSheetState,
            dragHandle = { BottomSheetDefaults.DragHandle() },
        ) {
            Text(
                text = "Do you to  see details?",
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(modifier = Modifier.padding(20.dp), onClick = {
                    onYesClicked()
                    scope.launch {
                        modalBottomSheetState.hide()
                    }
                }) {
                    Text(text = "Yes")
                }
                Button(modifier = Modifier.padding(20.dp), onClick = {
                    onNoClicked()
                    scope.launch {
                        modalBottomSheetState.hide()

                    }
                }) {
                    Text(text = "No")
                }
            }
        }
    }
}

