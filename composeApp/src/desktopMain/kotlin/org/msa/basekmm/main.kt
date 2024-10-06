package org.msa.basekmm

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Base Kmm Compose",
    ) {
        App()
    }
}