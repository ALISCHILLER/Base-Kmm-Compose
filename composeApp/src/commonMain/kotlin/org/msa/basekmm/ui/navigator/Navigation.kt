package org.msa.basekmm.ui.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.rememberTextMeasurer
import org.msa.basekmm.ui.screen.*

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    viewModel: NavigationViewModel
    ) {


    when(viewModel.currentScreen.collectAsState().value) {
        is Screen.oberding1 -> Oberding1(navigationViewModel = viewModel)
        Screen.oberding2 -> Oberding2(navigationViewModel = viewModel)
        Screen.oberding3 -> Oberding3(navigationViewModel = viewModel)
        Screen.oberding4 -> Oberding4(navigationViewModel = viewModel)
    }
}


