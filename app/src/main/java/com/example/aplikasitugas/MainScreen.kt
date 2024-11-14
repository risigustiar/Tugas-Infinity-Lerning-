package com.example.aplikasitugas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplikasitugas.pages.About
import com.example.aplikasitugas.pages.DetailPage
import com.example.aplikasitugas.pages.FruitItem
import com.example.aplikasitugas.pages.Grid
import com.example.aplikasitugas.pages.HomePage

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavigationHost(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navItemList = listOf(
        NavItem("List", R.drawable.list),
        NavItem("Grid", R.drawable.grid),
        NavItem("About", R.drawable.about),
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    NavigationBar {
        navItemList.forEachIndexed { index, navItem ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    val route = when (index) {
                        0 -> "home"
                        1 -> "grid"
                        2 -> "about"
                        else -> "home"
                    }
                    navController.navigate(route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = navItem.iconResId),
                        contentDescription = navItem.label,
                        modifier = Modifier.size(25.dp)
                    )
                },
                label = { Text(text = navItem.label) }
            )
        }
    }
}

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomePage(navController) }
        composable("grid") { Grid(navController) }
        composable("about") { About() }
        composable("detail/{name}/{imageResId}/{description}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val imageResId = backStackEntry.arguments?.getString("imageResId")?.toIntOrNull() ?: 0
            val description = backStackEntry.arguments?.getString("description") ?: ""
            DetailPage(navController, FruitItem(name, imageResId, description))
        }
    }
}
