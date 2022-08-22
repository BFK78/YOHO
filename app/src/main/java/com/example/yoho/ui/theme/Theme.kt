package com.example.yoho.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.yoho.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    error = error
)

private val LightColorPalette = lightColors(
    primary = ApplicationBlue,
    primaryVariant = Purple700,
    secondary = Teal200,
    error = error,
    surface = White,
    background = White

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

val rubik = FontFamily(
    listOf(
        Font(R.font.figtreeblack, FontWeight.Black ),
        Font(R.font.figtreebold, FontWeight.Bold),
        Font(R.font.figtreeextrabold, FontWeight.ExtraBold),
        Font(R.font.figtreelight, FontWeight.Light),
        Font(R.font.figtreemedium, FontWeight.Medium),
        Font(R.font.figtreesemibold, FontWeight.SemiBold),
        Font(R.font.figtreeregular, FontWeight.Normal)
    )
)

@Composable
fun YOHOTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val systemUiController = rememberSystemUiController()

    val colors = if (darkTheme) {
        systemUiController.setStatusBarColor(
            color = Color.Black
        )
        DarkColorPalette
    } else {
        systemUiController.setStatusBarColor(
            color = Color.White
        )
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}