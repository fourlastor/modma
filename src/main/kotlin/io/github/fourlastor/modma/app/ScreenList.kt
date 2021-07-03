package io.github.fourlastor.modma.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.vectorXmlResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScreenList(
    currentScreen: Screen,
    onRoute: (Screen) -> Unit,
    modifier: Modifier,
) {
    Surface(elevation = 2.dp) {
        LazyColumn(
            modifier = modifier
        ) {
            items(Screens.values()) { data ->
                val background = if (currentScreen == data.screen) Color.LightGray else Color(0x00000000)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(2.dp)
                        .background(background)
                        .clickable { onRoute(data.screen) }
                        .fillParentMaxWidth()
                        .padding(4.dp)
                ) {
                    Icon(
                        imageVector = vectorXmlResource(data.screenIcon),
                        contentDescription = data.screenName,
                        modifier = Modifier.size(32.dp).padding(4.dp),
                    )
                    Text(
                        text = data.screenName,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
