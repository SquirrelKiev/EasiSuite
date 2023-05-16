package com.ttsgroup.easisuite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class AudioPlayerPage {
    @Composable
    fun ShowAudioPlayerContent() {
        Column(modifier = Modifier.padding(all = 20.dp)) {
            Text(text = "Welcome to the audio player page!")
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