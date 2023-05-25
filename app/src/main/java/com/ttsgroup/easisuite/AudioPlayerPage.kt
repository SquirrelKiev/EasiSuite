package com.ttsgroup.easisuite

import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class AudioPlayerPage {
    @Composable
    fun ShowAudioPlayerContent() {
        Column(modifier = Modifier.padding(all = 40.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "farts",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .aspectRatio(ratio = 1f, matchHeightConstraintsFirst = true)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
            )

            Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))

            Text(
                text = "A really really cool song",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "A really cool author",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))

            Slider(
                value = .25f,
                onValueChange = { /* TODO */ },
                modifier = Modifier.height(20.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "0:57")
                Text(text = "3:48")
            }

            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                val iconSize = 54
                ResizableIconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.requiredSize(iconSize.dp)
                ) {
                    Icon(
                        Icons.Filled.SkipPrevious, "Skip to previous song",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                ResizableIconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.requiredSize((iconSize * 1.25).dp)
                ) {
                    Icon(
                        Icons.Filled.PauseCircle, "Pause/Play song",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                ResizableIconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.requiredSize(iconSize.dp)
                ) {
                    Icon(
                        Icons.Filled.SkipNext, "Skip to next song",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }

    @Composable
    fun ResizableIconButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            CompositionLocalProvider(
                LocalIndication provides rememberRipple(bounded = true)
            ) {
                ProvideTextStyle(
                    value = LocalTextStyle.current.copy(fontFamily = FontFamily.Default)
                ) {
                    content()
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clip(CircleShape)
                            .clickable(
                                onClick = onClick,
                                interactionSource = interactionSource,
                                indication = rememberRipple(bounded = true)
                            )
                    ) {}
                }
            }
        }
    }

    @Preview
    @Composable
    fun AudioPlayerPreview() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ShowAudioPlayerContent()
        }
    }

}