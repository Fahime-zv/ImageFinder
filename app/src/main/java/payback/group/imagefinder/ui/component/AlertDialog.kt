package payback.group.imagefinder.ui.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import payback.group.imagefinder.ui.theme.NoButton
import payback.group.imagefinder.ui.theme.YesButton

@Composable
fun AlertDialog(
    dialogText: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        text = { Text(text = dialogText) },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(onClick = { onConfirmation() }) {
                Text(YesButton)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(NoButton)
            }
        }
    )
}
