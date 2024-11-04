package com.example.repositories.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.commondesign.theme.GithubRepositoryFinderTheme

@Composable
fun TopicsTagItem(modifier: Modifier = Modifier, topic: String) {
    Row(
        modifier = modifier
            .height(22.dp)
            .wrapContentSize()
            .background(color = Color(0xFFE8F9FF), shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp),
    ) {
        Text(
            modifier = Modifier,
            text = topic,
            color = Color(0xFF408AAA),
            fontSize = 10.sp,
            fontWeight = FontWeight.W600
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TopicsTagItemPreview() {
    GithubRepositoryFinderTheme {
        TopicsTagItem(modifier = Modifier.padding(6.dp), topic = "Food")
    }
}