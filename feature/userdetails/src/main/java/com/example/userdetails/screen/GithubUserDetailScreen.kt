package com.example.userdetails.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.commondesign.component.CustomCircularNetworkImageLoader
import com.example.commondesign.theme.GithubRepositoryFinderTheme
import com.example.userdetails.R
import com.example.userdetails.route.UserDetailScreenRoute

@Composable
fun GithubUserDetailScreen(modifier: Modifier = Modifier, onBackPressed: () -> Unit) {
    val density = LocalDensity.current
    Column(
        modifier = modifier
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .fillMaxSize(),
    ) {

        Spacer(modifier = Modifier.height(25.dp))
        Box(modifier = Modifier.clickable { onBackPressed() }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(R.drawable.ic_arrow_left), "Arrow Back Button")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Users", fontSize = 12.sp, fontWeight = FontWeight.W600)
            }
        }
        Spacer(modifier = Modifier.height(18.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            CustomCircularNetworkImageLoader(
                imageUrl = "userData.imageUrl",
                modifier = Modifier
                    .size(45.dp)
                    .padding(top = 3.dp),
                contentDescription = " Picture",
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(text = "Paige Brown", fontSize = 16.sp, fontWeight = FontWeight.W600)
                Spacer(modifier = Modifier.height(3.dp))
                Text(text = "DynamicWebPaige", fontSize = 14.sp, fontWeight = FontWeight.W500)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "This is a random bio, which will be replace with actual content",
            fontSize = 12.sp,
            fontWeight = FontWeight.W500
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.ic_location),
                contentDescription = "Location Icon"
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Lagos, Nigeria",
                fontSize = 10.sp,
                fontWeight = FontWeight.W500,
                color = Color(0x8D1A1A1A)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Image(
                painter = painterResource(R.drawable.ic_chain_link),
                contentDescription = "Location Icon"
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "http://www.paige.com",
                fontSize = 10.sp,
                fontWeight = FontWeight.W500,
                color = Color(0xFF1A1A1A)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.ic_people),
                contentDescription = "Location Icon"
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "400 followers  .",
                fontSize = 10.sp,
                fontWeight = FontWeight.W500,
                color = Color(0x8D1A1A1A)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "30 following",
                fontSize = 10.sp,
                fontWeight = FontWeight.W500,
                color = Color(0x8D1A1A1A)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Box {
            var lineSize by remember { mutableStateOf(DpSize.Zero) }
            Column {
                Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier.onSizeChanged {
                    lineSize = density.run {
                        DpSize(width = it.width.toDp(), height = it.height.toDp())
                    }
                }) {
                    Text(
                        text = "Repositories",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W500,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        modifier = Modifier.background(
                            color = Color(0xFFF1F1F1),
                            shape = AbsoluteRoundedCornerShape(8.dp)
                        ).padding(vertical = 1.dp, horizontal = 8.dp),
                        text = "200",
                        fontSize = 8.sp,
                        fontWeight = FontWeight.W600,
                    )

                }
                HorizontalDivider(color = Color.Black, modifier = Modifier.width(lineSize.width))
            }
        }
        HorizontalDivider(thickness = 1.dp, color = Color(0xFFF1F1F1))
    }

}

@Preview(showBackground = true)
@Composable
private fun GithubUserDetailScreenPreview() {
    GithubRepositoryFinderTheme {
        GithubUserDetailScreen(onBackPressed = {})
    }
}

fun NavGraphBuilder.userDetails(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    composable<UserDetailScreenRoute> {
        GithubUserDetailScreen(
            modifier = modifier,
            onBackPressed = onBackPressed
        )
    }
}