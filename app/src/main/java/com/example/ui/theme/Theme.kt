package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = SaffronLight,
    onPrimary = Color.Black,
    primaryContainer = SaffronDark,
    onPrimaryContainer = Color.White,
    secondary = IndiaGreenLight,
    onSecondary = Color.Black,
    secondaryContainer = IndiaGreenDark,
    tertiary = GoldenStar,
    background = Color(0xFF181512),
    surface = Color(0xFF221F1C),
    onBackground = Color(0xFFF0EAE1),
    onSurface = Color(0xFFF0EAE1)
)

private val LightColorScheme = lightColorScheme(
    primary = SaffronPrimary,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFE0B2),
    onPrimaryContainer = SaffronDark,
    secondary = IndiaGreen,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE8F5E9),
    onSecondaryContainer = IndiaGreenDark,
    tertiary = RoyalNavy,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFE8EAF6),
    onTertiaryContainer = RoyalNavy,
    background = KidBackgroundLight,
    surface = KidCardSurface,
    onBackground = TextPrimaryDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = KidCardSurfaceVariant,
    onSurfaceVariant = TextSecondaryDark,
    error = CoralRed
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // We intentionally keep our consistent colorful India adventure palette
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
