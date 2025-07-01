package com.example.bookrecommendation.Screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import coil3.compose.AsyncImage
import com.example.bookrecommendation.Model.Book
import com.example.bookrecommendation.R
import com.example.bookrecommendation.ViewModel.BookViewModel
import com.example.bookrecommendation.ui.theme.LightBlack
import com.example.bookrecommendation.ui.theme.OffWhite
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun HomeScreen(
   viewModel: BookViewModel
) {
    val books by viewModel.popularBooks.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    var onSearch by remember {
        mutableStateOf(false)
    }

    var search by remember{
        mutableStateOf("")
    }
    var recommendedBooks = viewModel.recommendedBooks.collectAsState()
    var isRecommending = mutableStateOf(false)
    var recommendError = mutableStateOf<String?>(null)

    LaunchedEffect(Unit) {
        viewModel.fetchPopularBooks()
    }


    Box(modifier = Modifier.fillMaxSize()){
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                error != null -> {
                    Text(
                        text = error ?: "Unknown error",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {

                    Column(
                    ){

                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(8.dp)){
                            Row(
                                verticalAlignment = Alignment.Bottom,
                                modifier = Modifier.padding(top=30.dp)
                            ) {
                                Text(
                                    text = "Book Recommender",
                                    fontSize = 30.sp,
                                    color = LightBlack,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Left,
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .weight(1f)
                                )
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(35.dp)
                                        .padding(end = 4.dp)
                                        .clickable {
                                            onSearch = !onSearch
                                        }

                                )
                            }
                        }

                        if(onSearch){
                            Column(modifier = Modifier.fillMaxWidth()){

                                Row() {
                                    TextField(
                                        value = search,
                                        onValueChange = {search = it},
                                        placeholder = { Text("Search Book", color = Color.LightGray) },
                                        colors = TextFieldDefaults.colors(
                                            focusedIndicatorColor = Color.Gray,
                                            unfocusedIndicatorColor = Color.LightGray,
                                            disabledIndicatorColor = Color.Transparent,
                                            focusedContainerColor = Color.Transparent,
                                            unfocusedContainerColor = Color.Transparent,
                                            disabledContainerColor = Color.Transparent,
                                            cursorColor = Color.Black,
                                            unfocusedTextColor = Color.Black,
                                            focusedTextColor = Color.Black,
                                            disabledTextColor = Color.Gray
                                        ),
                                        singleLine = true,
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(horizontal = 16.dp)
                                    )

                                    Button(
                                        onClick = { viewModel.fetchRecommendedBooks(search) },
                                        modifier = Modifier
                                            .padding(horizontal = 16.dp, vertical = 8.dp)
                                            .weight(0.5f)

                                    ) {
                                        Text(text = "Search")
                                    }
                                }

                                if (isRecommending.value) {
                                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                                } else if (recommendedBooks.value.isNotEmpty()) {
                                    Text(
                                        text = "Recommended Books",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(16.dp)
                                    )

                                    LazyVerticalGrid(
                                        columns = GridCells.Fixed(3),
                                        contentPadding = PaddingValues(8.dp),
                                        verticalArrangement = Arrangement.spacedBy(12.dp),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        items(recommendedBooks.value) { book ->
                                            BookCard(book = book)
                                        }
                                    }
                                }
                                else if (recommendError.value != null) {
                                    Text(
                                        text = recommendError.value.toString() ?: "Unknown error",
                                        color = Color.Red,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            }
                        }

                     if(!onSearch)
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(books) { book ->
                            BookCard(book = book)
                        }
                    }
                    }
                }
            }
    }

}

@Composable
fun BookCard(book: Book){
    Card(
        modifier = Modifier
            .padding(6.dp)
            .height(200.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(LightBlack)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(4.dp)
        ){
            AsyncImage(
                model = book.image,
                contentDescription = book.title,
                modifier = Modifier
                    .padding(4.dp)
                    .height(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit,
                error = painterResource(R.drawable.image)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = book.title,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 16.sp
                ),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = OffWhite,
                modifier = Modifier.padding(horizontal = 5.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "by ${book.author}", fontSize = 10.sp, color = Color.Gray)
        }
    }
}
