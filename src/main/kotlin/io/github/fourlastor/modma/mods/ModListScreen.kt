package io.github.fourlastor.modma.mods

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ModListScreen() {
    Column {
        LazyColumn {
            items(15) { index ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp),
                ) {
                    Switch(
                        checked = index % 2 == 0,
                        onCheckedChange = null,
                    )
                    Text(
                        text = "Mod $index",
                    )
                }
            }
        }
    }
}
