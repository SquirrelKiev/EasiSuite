package com.ttsgroup.easisuite

import android.content.Context
import android.graphics.pdf.PdfDocument.Page
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class DeviceCard(val name: String, val route: String)

class HomePage {
    @Composable
    fun ShowHomeContent(context: Context, navController: NavController) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(all = 20.dp),
        ) {
            this.items(
                listOf<DeviceCard>(
                    DeviceCard(context.resources.getString(Screen.AudioPlayer.resourceId), Screen.AudioPlayer.route),
                    DeviceCard(context.resources.getString(Screen.AudioPlayer.resourceId), Screen.AudioPlayer.route),
                    DeviceCard(context.resources.getString(Screen.AudioPlayer.resourceId), Screen.AudioPlayer.route),
                    DeviceCard(context.resources.getString(Screen.AudioPlayer.resourceId), Screen.AudioPlayer.route),
                ))
            {device: DeviceCard ->
                PageCard(device, navController)
            }
        }

    }

    @Composable
    fun PageCard(device: DeviceCard, navController: NavController) {
        OutlinedCard(
            modifier = Modifier.padding(all = 4.dp),
            onClick = {navController.navigate(device.route)}
        )
        {
            Image(painter = painterResource(id = R.drawable.necoarc), contentDescription = "wudenyu")
            Text(text = device.name, style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(all = 8.dp))
        }
    }
}
