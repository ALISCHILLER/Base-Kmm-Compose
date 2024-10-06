package org.msa.basekmm.ui.navigator

import basekmmcompose.composeapp.generated.resources.Res

sealed class  Screen(
     route: String,
     args:Map<String,String> = mapOf()){

    data object oberding1:Screen("oberdinov1")
    data object oberding2:Screen("oberdinov2")
    data object oberding3:Screen("oberdinov3")
    data object oberding4:Screen("oberdinov4")

}