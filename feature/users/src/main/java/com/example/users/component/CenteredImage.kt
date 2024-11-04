package com.example.users.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.commondesign.theme.GithubRepositoryFinderTheme
import com.example.users.R

@Composable
fun CenteredImage(modifier: Modifier = Modifier, text: String) {
    Column(
        modifier = modifier.padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(R.drawable.ic_empty_search), contentDescription = "")
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = text,
            fontWeight = FontWeight.W500,
            fontSize = 12.sp,
            color = Color(0xFF727272),
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun CenteredImagePreview() {
    GithubRepositoryFinderTheme {
        CenteredImage(
            text = "We’ve searched the ends of the earth, repository not found, please try again"
        )
    }
}