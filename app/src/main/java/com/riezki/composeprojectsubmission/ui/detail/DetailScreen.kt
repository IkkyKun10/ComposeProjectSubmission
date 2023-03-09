package com.riezki.composeprojectsubmission.ui.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riezki.composeprojectsubmission.R
import com.riezki.composeprojectsubmission.injection.Injection
import com.riezki.composeprojectsubmission.model.Anime
import com.riezki.composeprojectsubmission.model.AnimeData
import com.riezki.composeprojectsubmission.ui.ViewModelFactory
import com.riezki.composeprojectsubmission.ui.theme.ComposeProjectSubmissionTheme
import androidx.lifecycle.viewmodel.compose.*
import com.riezki.composeprojectsubmission.ui.common.UiState

@Composable
fun DetailScreen(
    idDetail: String,
    //modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    viewModel: DetailScreenViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getIdAnime(idDetail)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    animeData = data,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    animeData: Anime,
    //modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    //val animeData = AnimeData.listAnime
    LazyColumn {
        item {
            DetailHeader(data = animeData, onBackClick = onBackClick)
            DetailTambahan(animeData)
            AnimeDataContent(animeData)
        }
    }
}

@Composable
fun DetailHeader(
    data: Anime,
    //navController: NavController,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Box(//~~~~~~~~~~
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box {
            Image(
                painter = painterResource(id = data.img!!),
                contentDescription = stringResource(R.string.image_detail),
                modifier = modifier
                    .fillMaxWidth()
                    .height(500.dp),
                contentScale = ContentScale.Crop,
            )

            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 400f,
                        )
                    )
            )

            Box(
                modifier = modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
            ) {

                TopButton(
                    imageVector = Icons.Default.ArrowBack,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp),
                    clickListener = onBackClick
                )

//                TopButton(
//                    imageVector = Icons.Default.FavoriteBorder,
//                    modifier = Modifier
//                        .align(Alignment.TopEnd)
//                        .padding(16.dp)
//                ) {
//
//                }

            }

        }

        /*Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            Card(
                backgroundColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                elevation = 0.dp,
                shape = topShape.large
            ) {
                Box {
                    Column {
                        Text(text = "Nama Anime")
                    }
                }
            }
        }*/
    }
}

@Composable
fun DetailTambahan(
    data: Anime,
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Row {

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Default.Star, contentDescription = "",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(12.dp)
                    .align(CenterVertically),
                tint = Color(0xFFFBC110)
            )

            Text(
                text = "4.8 (2.5k reviews)",
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = data.name!!,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.SemiBold,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = (-0.2).sp
        )

        Divider(
            color = Color(0xFFECECEE),
            modifier = Modifier.padding(8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            AnimeDataItem(
                imageVector = Icons.Default.List,
                title = "Genre",
                subtitle = data.genre.toString()
            )

            AnimeDataItem(
                imageVector = Icons.Default.DateRange,
                title = "Date",
                subtitle = data.date.toString()
            )

            AnimeDataItem(
                imageVector = Icons.Default.CheckCircle,
                title = "Studio",
                subtitle = data.studio.toString()
            )

        }

        Divider(
            color = Color(0xFFECECEE),
            modifier = Modifier.padding(8.dp)
        )

    }
}

@Composable
fun AnimeDataContent(anime: Anime) {

    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Text(
            text = "Overview".uppercase(),
            fontSize = 14.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 0.75.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = anime.overview!!,
            fontSize = 14.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Light,
            lineHeight = 18.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Description".uppercase(),
            fontSize = 14.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 0.75.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = anime.desc!!,
            fontSize = 14.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Light,
            lineHeight = 18.sp
        )

    }

}

@Preview
@Composable
fun AnimeContentPrev() {
    ComposeProjectSubmissionTheme {
        AnimeDataContent(anime = AnimeData.listAnime[0])
    }
}

@Composable
fun AnimeDataItem(imageVector: ImageVector, title: String, subtitle: String) {

    Row {

        Icon(
            modifier = Modifier
                .padding(8.dp)
                .clip(CircleShape)
                .background(Color(0xFFF5F6FF))
                .size(32.dp)
                .padding(8.dp),

            imageVector = imageVector, contentDescription = ""
        )

        Column {

            Text(
                text = title,
                fontSize = 12.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = subtitle,
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal
            )
        }

    }

}

@Composable
fun TopButton(imageVector: ImageVector, modifier: Modifier, clickListener: () -> Unit) {
    Button(
        onClick = { clickListener() },
        border = BorderStroke(2.dp, Color(0xFF1ED3FD)),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xDDF6F9FF),
            contentColor = Color(0xFF3562D7),
        ),
        modifier = modifier.size(48.dp)
    ) {
        Icon(imageVector = imageVector, contentDescription = "")
    }
}


@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    ComposeProjectSubmissionTheme {
        DetailContent(
            animeData = AnimeData.listAnime[0],
            onBackClick = {}
        )
    }
}