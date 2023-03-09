package com.riezki.composeprojectsubmission.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.riezki.composeprojectsubmission.R
import com.riezki.composeprojectsubmission.injection.Injection
import com.riezki.composeprojectsubmission.model.Anime
import com.riezki.composeprojectsubmission.ui.ViewModelFactory
import com.riezki.composeprojectsubmission.ui.common.UiState

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        //Spacer(modifier = Modifier.height(15.dp))
        //ContentList(anime = AnimeData.listAnime)
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getAllAnime()
                }
                is UiState.Success -> {
                    ContentList(
                        modifier = modifier,
                        anime = uiState.data,
                        navigateToDetail = navigateToDetail,
                    )
                }
                is UiState.Error -> {}
            }
        }
    }
}

@Composable
fun ContentList(
    anime: List<Anime>,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn{
        items(anime) { item ->
            ContentCard(
                data = item,
                modifier = modifier.clickable {
                    navigateToDetail(item.id.toString())
                }
            )
        }
    }
}

@Composable
fun ContentCard(
    modifier: Modifier = Modifier,
    data: Anime,
//    navigateToDetail: (String) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
            /*.clickable {
                navigateToDetail(data.id.toString())
            }*/,
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp,
    ) {
        Box(
            modifier = modifier
                .height(200.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = data.img!!),
                    contentDescription = "Anime Image",
                    contentScale = ContentScale.FillWidth
                )
            }
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black,
                            ),
                            startY = 400f
                        )
                    )
            )
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = data.name.toString(),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp
                    ),
                )
            }
        }
    }
}

@Composable
fun MyTopBarApp(
    onMenuClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(stringResource(R.string.anime_name_data))
        },
        actions = {
            IconButton(
                onClick = {
                    onMenuClick()
                },
            ) {
                Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "about_page")
            }
        }
    )
}