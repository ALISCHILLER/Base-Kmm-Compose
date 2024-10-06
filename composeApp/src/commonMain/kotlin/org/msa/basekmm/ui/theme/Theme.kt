import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.msa.basekmm.ui.theme.*

// Light theme colors
// Light theme colors
@Composable
fun LightColor(): Colors {
    return lightColors(
        primary = Blue500,
        primaryVariant = Blue700,
        secondary = Teal500,
        background = White,
        surface = White,
        onPrimary = White,
        onSecondary = Black,
        onBackground = Black,
        onSurface = Black,
        error = Red700,
        onError = White
    )
}

// Dark theme colors
@Composable
fun DarkColor(): Colors {
    return darkColors(
        primary = Blue200,
        primaryVariant = Blue300,
        secondary = Teal200,
        background = Black,
        surface = Color(0xFF121212),
        onPrimary = Black,
        onSecondary = White,
        onBackground = White,
        onSurface = White,
        error = Red300,
        onError = Black
    )
}