package org.msa.basekmm

import DarkColor
import LightColor
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import basekmmcompose.composeapp.generated.resources.Res
import basekmmcompose.composeapp.generated.resources.compose_multiplatform
import org.msa.basekmm.ui.navigator.Navigation
import org.msa.basekmm.ui.navigator.NavigationViewModel

@Composable
@Preview
fun App() {
    MaterialTheme(colors = if (isSystemInDarkTheme()) DarkColor() else LightColor()) {
        val coroutineScope = rememberCoroutineScope()
        val viewModel = remember {
            NavigationViewModel(coroutineScope)
        }

        // Call Navigation composable with a Modifier and viewModel
        Navigation(
            modifier = Modifier.fillMaxWidth(), // Example Modifier (customize as needed)
            viewModel = viewModel
        )
    }
}