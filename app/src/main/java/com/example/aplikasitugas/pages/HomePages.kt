package com.example.aplikasitugas.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.aplikasitugas.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

data class FruitItem(val name: String, val imageResId: Int, val description: String)

@Composable
fun HomePage(navController: NavController, modifier: Modifier = Modifier) {
    var searchQuery by remember { mutableStateOf("") }

    val itemList = listOf(
        FruitItem("Duku", R.drawable.duku, "Buah yang mengandung vitamin C"),
        FruitItem("Kiwi", R.drawable.kiwi, "Buah yang mengandung vitamin K"),
        FruitItem("Pepaya", R.drawable.pepaya, "Buah yang mengandung vitamin A"),
        FruitItem("Jagung", R.drawable.jagung, "Buah yang mengandung vitamin C"),
        FruitItem("Jeruk", R.drawable.jeruk, "Buah yang mengandung vitamin C"),
        FruitItem("Kelapa", R.drawable.kelapa, "Buah yang mengandung vitamin E"),
        FruitItem("Mangga", R.drawable.mangga, "Buah yang mengandung vitamin C"),
        FruitItem("Manggis", R.drawable.manggis, "Buah yang mengandung vitamin C"),
        FruitItem("Nanas", R.drawable.nanas, "Buah yang mengandung vitamin C"),
        FruitItem("Pisang", R.drawable.pisang, "Buah yang mengandung vitamin B6")
    )

    val filteredList = if (searchQuery.isEmpty()) {
        itemList
    } else {
        itemList.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colorScheme.primary

    val topBarColor = colorResource(id = R.color.teal_700)

    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = topBarColor
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                    .background(colorResource(id = R.color.teal_700))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Daftar Buah Segar",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp)
                )

                Spacer(modifier = Modifier.padding(top = 8.dp))

                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Cari buah...", color = Color.Gray) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Color.Gray
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 10.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                )
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF1F3FF))
                    .padding(innerPadding)
            ) {
                FruitListRow(navController = navController)

                if (filteredList.isEmpty()) {
                    Text(
                        text = "Pencarian Tidak Ditemukan",
                        color = Color.Gray,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 20.dp)
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(filteredList) { item ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)
                                    .clickable {
                                        navController.navigate("detail/${item.name}/${item.imageResId}/${item.description}")
                                    }
                            ) {
                                Image(
                                    painter = painterResource(id = item.imageResId),
                                    contentDescription = item.name,
                                    modifier = Modifier
                                        .size(70.dp)
                                        .clip(CircleShape)
                                        .border(3.dp, MaterialTheme.colorScheme.primary, CircleShape)
                                        .padding(2.dp)
                                        .background(Color.White, CircleShape)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = item.name,
                                        fontSize = 15.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Medium
                                    )
                                    Text(
                                        text = item.description,
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun FruitListRow(navController: NavController, modifier: Modifier = Modifier) {
    val itemList = listOf(
        FruitItem("Duku", R.drawable.duku, "Buah yang mengandung vitamin C"),
        FruitItem("Kiwi", R.drawable.kiwi, "Buah yang mengandung vitamin K"),
        FruitItem("Pepaya", R.drawable.pepaya, "Buah yang mengandung vitamin A"),
        FruitItem("Jagung", R.drawable.jagung, "Buah yang mengandung vitamin C"),
        FruitItem("Jeruk", R.drawable.jeruk, "Buah yang mengandung vitamin C"),
        FruitItem("Kelapa", R.drawable.kelapa, "Buah yang mengandung vitamin E"),
        FruitItem("Mangga", R.drawable.mangga, "Buah yang mengandung vitamin C"),
        FruitItem("Manggis", R.drawable.manggis, "Buah yang mengandung vitamin C"),
        FruitItem("Nanas", R.drawable.nanas, "Buah yang mengandung vitamin C"),
        FruitItem("Pisang", R.drawable.pisang, "Buah yang mengandung vitamin B6")
    )
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(itemList) { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .clickable {
                        navController.navigate("detail/${item.name}/${item.imageResId}/${item.description}")
                    }
            ) {
                Image(
                    painter = painterResource(id = item.imageResId),
                    contentDescription = item.name,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .border(3.dp, MaterialTheme.colorScheme.primary, CircleShape)
                        .background(Color.White, CircleShape)
                        .padding(4.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.name,
                    fontSize = 15.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
