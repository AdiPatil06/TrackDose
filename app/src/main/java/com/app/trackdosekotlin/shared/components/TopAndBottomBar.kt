package com.app.trackdosekotlin.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.trackdosekotlin.R
import com.app.trackdosekotlin.main.MasterNavigationScreens
import com.app.trackdosekotlin.main.app

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    fontSize: TextUnit = 30.sp,
    isBackArrowRequired: Boolean = false,
    onBackClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                title, style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = fontSize
                )
            )
        },
        navigationIcon = {
            if (isBackArrowRequired) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            onBackClick()
                        }
                )
            }
        }
    )
}

@Composable
fun BottomBar() {
    data class NavigationItem(
        val title: String,
        val icon: Int,
        val route: String
    )

    val navigationItems = listOf(
        NavigationItem(
            title = MasterNavigationScreens.Medicine.route,
            icon = R.drawable.bar_medicine,
            route = ""
        ),
        NavigationItem(
            title = MasterNavigationScreens.Health.route,
            icon = R.drawable.bar_medicine,
            route = ""
        ),
    )

    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(
            navigationItems.indexOf(
                navigationItems.find { it.title == app.sharedViewModel.selectedNavTab.value }
            )
        )
    }

    NavigationBar(
        containerColor = Color.White
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                onClick = {
                    selectedNavigationIndex.intValue = index
                    app.sharedViewModel.selectedNavTab.value = item.title
                    app.sharedViewModel.masterNavController.navigate(item.title)
                },
                icon = {
                    Image(
                        painterResource(item.icon),
                        contentDescription = item.title,
                        modifier = Modifier
                            .size(30.dp)
                            .padding(if (index == selectedNavigationIndex.intValue) 5.dp else 3.dp),
                        colorFilter = ColorFilter.tint(color = if (index == selectedNavigationIndex.intValue) Color.White else Color.Black)
                    )
                },
                label = {
                    Text(
                        item.title,
                        color = if (index == selectedNavigationIndex.intValue)
                            Color.Black
                        else Color.Gray,
                        style = MaterialTheme.typography.titleSmall
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}