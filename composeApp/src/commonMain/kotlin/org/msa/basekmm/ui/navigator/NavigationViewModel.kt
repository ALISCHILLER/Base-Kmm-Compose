package org.msa.basekmm.ui.navigator

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NavigationViewModel(
   private val scope: CoroutineScope
){

    private val  _currrentScreen = MutableStateFlow<Screen>(Screen.oberding1)
    val currentScreen = _currrentScreen.asStateFlow()

    fun navigateTo(screen: Screen){
        scope.launch {
            _currrentScreen.update {screen  }
        }
    }
}
