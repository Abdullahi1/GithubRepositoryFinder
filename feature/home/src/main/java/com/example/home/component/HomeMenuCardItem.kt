package com.example.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.commondesign.theme.GithubRepositoryFinderTheme

@Composable
fun HomeMenuCardItem(
    modifier: Modifier = Modifier,
    menuItem: HomeMenuItem,
    onClick: (HomeMenuItem) -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxWidth().clickable {
                onClick(menuItem)
            }, color = menuItem.backgroundColor, shape = MaterialTheme.shapes.medium.copy(all = CornerSize(0.dp))
    ) {
        Column(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 16.dp),
        ) {
            Image(
                modifier = Modifier
                    .size(width = 35.dp, height = 35.dp)
                    .background(
                        color = Color.White, shape = MaterialTheme.shapes.medium.copy(all = CornerSize(0.dp))
                    )
                    .padding(4.dp),
                painter = painterResource(id = menuItem.icon),
                contentDescription = menuItem.iconContentDescription,
            )
            Spacer(modifier = Modifier.height(41.dp))
            Text(
                text = menuItem.title,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.W700, fontSize = 16.sp),
                maxLines = 1,
                color = Color.Black,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeMenuCardItemPreview(modifier: Modifier = Modifier) {
    GithubRepositoryFinderTheme {
        Column (modifier = modifier.padding(16.dp).fillMaxSize()){
            HomeMenuCardItem(menuItem = HomeMenuItem.homeMenuList.random(), onClick = {})
        }
    }
}