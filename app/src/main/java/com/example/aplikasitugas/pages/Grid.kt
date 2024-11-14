package com.example.aplikasitugas.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.navigation.NavController
import com.example.aplikasitugas.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun Grid(navController: NavController) {

    var searchQuery by remember { mutableStateOf("") }

    val itemList = listOf(
        FruitItem("Duku", R.drawable.duku, "Vitamin C"),
        FruitItem("Kiwi", R.drawable.kiwi, "Vitamin K"),
        FruitItem("Pepaya", R.drawable.pepaya, "Vitamin A"),
        FruitItem("Jagung", R.drawable.jagung, "Vitamin C"),
        FruitItem("Jeruk", R.drawable.jeruk, "Vitamin C"),
        FruitItem("Kelapa", R.drawable.kelapa, "Vitamin E"),
        FruitItem("Mangga", R.drawable.mangga, "Vitamin C"),
        FruitItem("Manggis", R.drawable.manggis, "Vitamin C"),
        FruitItem("Nanas", R.drawable.nanas, "Vitamin C"),
        FruitItem("Pisang", R.drawable.pisang, "Vitamin B6")
    )

    val filteredList = if (searchQuery.isEmpty()) itemList else itemList.filter {
        it.name.contains(searchQuery, ignoreCase = true)
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
                    text = "Semua Buah Segar",
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
                        .padding(horizontal = 8.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                )
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .background(Color(0xFFF1F3FF))
                    .padding(bottom = 60.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(30.dp),
                    verticalArrangement = Arrangement.spacedBy(40.dp),
                    horizontalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    items(filteredList.size) { index ->
                        val item = filteredList[index]
                        FruitGridItem(navController = navController, item = item)
                    }
                }
            }
        }
    )
}

@Composable
fun FruitGridItem(navController: NavController, item: FruitItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(8.dp)
            .clickable {
                navController.navigate("detail/${item.name}/${item.imageResId}/${item.description}")
            }

    ) {
        Image(
            painter = painterResource(id = item.imageResId),
            contentDescription = item.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                .background(Color.White, CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = item.description,
            fontSize = 12.sp,
            color = Color.Gray,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
