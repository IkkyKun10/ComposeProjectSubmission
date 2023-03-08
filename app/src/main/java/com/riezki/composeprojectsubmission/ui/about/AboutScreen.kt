package com.riezki.composeprojectsubmission.ui.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riezki.composeprojectsubmission.R
import com.riezki.composeprojectsubmission.ui.theme.ComposeProjectSubmissionTheme

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        HeaderAbout()
    }
}

@Composable
fun HeaderAbout(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = CircleShape,
            modifier = modifier
                .padding(8.dp)
                .size(300.dp),
        ) {
            Image(
                modifier = modifier,
                painter = painterResource(id = R.drawable.my_photo_profile),
                contentDescription = "About Me",
                contentScale = ContentScale.Crop
            )
        }
        IdentityAbout()
    }
}

@Preview
@Composable
fun HeaderPrev() {
    ComposeProjectSubmissionTheme {
        HeaderAbout()
    }
}

@Composable
fun IdentityAbout(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp),
    ) {
        Text(
            text = stringResource(R.string.my_name),
            modifier = modifier
                .fillMaxWidth(),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.my_email),
            fontSize = 18.sp,
            modifier = modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}