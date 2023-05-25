package com.ttsgroup.easisuite

import android.content.Intent
import android.os.Parcelable
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.parcelize.Parcelize

@Parcelize
data class DeviceCard(val name: String, val route: String, val productUrl: String) : Parcelable

class HomePage {
    @Composable
    fun ShowHomeContent(navController: NavController?) {
        val context = LocalContext.current
        var openBottomSheet by rememberSaveable { mutableStateOf(false) }
        var activeDeviceCard by rememberSaveable { mutableStateOf<DeviceCard?>(null) }
        val bottomSheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(all = 20.dp),
        ) {
            this.items(
                listOf(
                    DeviceCard(
                        context.resources.getString(Screen.AudioPlayer.resourceId),
                        Screen.AudioPlayer.route,
                        context.resources.getString(R.string.headphones_link)
                    ),
                )
            )
            { device: DeviceCard ->
                PageCard(device, onClick =
                {
                    openBottomSheet = true
                    activeDeviceCard = device
                })
            }
        }

        if (openBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { openBottomSheet = false },
                sheetState = bottomSheetState
            ) {
                DeviceInfo(activeDeviceCard!!, onClick = {
                    navController?.navigate(activeDeviceCard!!.route)
                })
            }
        }
    }

    @Composable
    @Preview
    fun DeviceInfoPreview() {
        Surface(
            color = MaterialTheme.colorScheme.background
        ){
            DeviceInfo(
                activeDeviceCard = DeviceCard
                    (
                    "Device name",
                    "nowhere",
                    "https://example.com"
                ), onClick = {})
        }

    }

    @Composable
    fun DeviceInfo(activeDeviceCard: DeviceCard, onClick: () -> Unit) {
        val uriHandler = LocalUriHandler.current
        val context = LocalContext.current

        val padding = 16.dp

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = padding, start = padding, end = padding),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = activeDeviceCard.name, style = MaterialTheme.typography.headlineSmall)
                IconButton(onClick =
                {
                    uriHandler.openUri(activeDeviceCard.productUrl)
                }
                ) {
                    Icon(Icons.Outlined.ShoppingCart, contentDescription = "Open shop page")
                }
            }
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "farts",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(18.dp))
            )
            Text(
                style = MaterialTheme.typography.bodyLarge,
                text = "Lorem ipsum dolor sit amet Clita invidunt at rebum stet blandit consetetur sit esse. ",
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = onClick
                ) {
                    Text(text = "Open")
                }
                // TODO: better button placement
                OutlinedButton(onClick =
                {
                    context.startActivity(Intent(Settings.ACTION_BLUETOOTH_SETTINGS))
                }
                ) {
                    Text(text = "Connect")
                }
            }
        }
    }

    @Composable
    fun PageCard(device: DeviceCard, onClick: () -> Unit) {
        ElevatedCard(
            modifier = Modifier.padding(all = 4.dp),
            onClick = onClick
        )
        {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "wudenyu",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = device.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(all = 16.dp)
            )
        }
    }

    @Composable
    @Preview
    fun HomePagePreview() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ShowHomeContent(null)
        }
    }
}
