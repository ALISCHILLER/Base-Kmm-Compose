package org.msa.basekmm.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.msa.basekmm.ui.navigator.NavigationViewModel
import org.msa.basekmm.ui.navigator.Screen

@Composable
fun Oberding3(
    modifier: Modifier = Modifier,
    navigationViewModel: NavigationViewModel
) {

    Column(modifier = modifier) {
        Text("screen 3")
        Button(
            onClick = {
                navigationViewModel.navigateTo(screen = Screen.oberding4)
            }
        ){
            Text(text = "Next To Screen 4")
        }

    }


}