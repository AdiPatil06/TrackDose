package com.app.trackdosekotlin.mainUi.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.trackdosekotlin.R

val latoRegularFontFamily = FontFamily(
    Font(R.font.lato_regular),
)

val latoBoldFontFamily = FontFamily(
    Font(R.font.lato_bold),
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = latoRegularFontFamily,
        fontSize = 30.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    displayMedium = TextStyle(
        fontFamily = latoRegularFontFamily,
        fontSize = 22.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    displaySmall = TextStyle(
        fontFamily = latoRegularFontFamily,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    headlineLarge = TextStyle(
        fontFamily = latoRegularFontFamily,
        fontSize = 30.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    headlineMedium = TextStyle(
        fontFamily = latoRegularFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    headlineSmall = TextStyle(
        fontFamily = latoRegularFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    titleLarge = TextStyle(
        fontFamily = latoRegularFontFamily,
        fontSize = 30.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    titleMedium = TextStyle(
        fontFamily = latoRegularFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    titleSmall = TextStyle(
        fontFamily = latoRegularFontFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    bodyLarge = TextStyle(
        fontFamily = latoRegularFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    bodyMedium = TextStyle(
        fontFamily = latoRegularFontFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    bodySmall = TextStyle(
        fontFamily = latoRegularFontFamily,
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    labelLarge = TextStyle(
        fontFamily = latoRegularFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    labelMedium = TextStyle(
        fontFamily = latoRegularFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    labelSmall = TextStyle(
        fontFamily = latoRegularFontFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
)