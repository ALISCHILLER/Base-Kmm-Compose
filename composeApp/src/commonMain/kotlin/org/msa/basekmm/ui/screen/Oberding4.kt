package org.msa.basekmm.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.msa.basekmm.ui.navigator.NavigationViewModel
import org.msa.basekmm.ui.navigator.Screen

@Composable
fun Oberding4(
    modifier: Modifier = Modifier,
    navigationViewModel: NavigationViewModel
) {

    Column(modifier = modifier) {
        Text("screen 4")
        Button(
            onClick = {
                navigationViewModel.navigateTo(screen = Screen.oberding1)
            }
        ){
            Text(text = "come back To Screen 1")
        }

    }


}