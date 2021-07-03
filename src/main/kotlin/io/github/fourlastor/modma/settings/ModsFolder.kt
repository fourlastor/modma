package io.github.fourlastor.modma.settings

import androidx.compose.desktop.LocalAppWindow
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.awt.FileDialog

@Composable
fun ModsFolder(
    name: String,
    folder: String?,
    onFolderChanged: (path: String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 4.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = name,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            val text = folder ?: "Not selected"
            Text(
                text = text,
                fontSize = 12.sp
            )
        }

        val window = LocalAppWindow.current
        Button(onClick = {
            FileDialog(window.window, "Choose", FileDialog.LOAD)
                .apply {
                    isVisible = true
                    onFolderChanged("$directory$file")
                }
        }) {
            Text("Change")
        }
    }
}
