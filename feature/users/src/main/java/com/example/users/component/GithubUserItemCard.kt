package com.example.users.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.commondesign.component.CustomCircularNetworkImageLoader
import com.example.commondesign.theme.GithubRepositoryFinderTheme
import com.example.domain.model.GithubUserProfileData

@Composable
fun GithubUserItemCard(modifier: Modifier = Modifier, userData: GithubUserProfileData) {
    Column(modifier = modifier) {
        Card(
            modifier = modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            shape = RectangleShape,
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
            ) {
                CustomCircularNetworkImageLoader(
                    imageUrl = userData.imageUrl,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(top = 3.dp),
                    contentDescription = "${userData.fullName} Picture",
                )

                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = userData.fullName,
                        color = Color(0xFF408AAA),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600,
                        modifier = Modifier.padding(
                            bottom = 2.dp
                        )
                    )
                    Text(
                        text = userData.userName,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.W400,
                        modifier = Modifier.padding(
                            bottom = 4.dp
                        )
                    )
                    if (userData.bio.isNotEmpty()) {
                        Text(
                            text = userData.bio,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.W500,
                            modifier = Modifier.padding(
                                bottom = 4.dp
                            )
                        )
                    }

                    if (userData.location.isNotEmpty() || userData.emailAddress.isNotEmpty()) {
                        Row {
                            if (userData.location.isNotEmpty()) {
                                Text(
                                    text = userData.location,
                                    fontSize = 8.sp,
                                    color = Color(0xFF727272),
                                    fontWeight = FontWeight.W500,
                                    modifier = Modifier.padding(
                                        bottom = 4.dp
                                    )
                                )

                                Spacer(modifier = Modifier.width(8.dp))
                            }
                            if (userData.emailAddress.isNotEmpty()) {
                                Text(
                                    text = userData.emailAddress,
                                    fontSize = 8.sp,
                                    color = Color(0xFF727272),
                                    fontWeight = FontWeight.W500,
                                    modifier = Modifier.padding(
                                        bottom = 4.dp
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun GithubUserItemCardPreview() {
    GithubRepositoryFinderTheme {
        GithubUserItemCard(
            modifier = Modifier.padding(4.dp),
            userData = GithubUserProfileData(
                userName = "Andry",
                fullName = "Andry Andre",
                bio = "This is a random bio, which will be replace with actual content",
                emailAddress = "andry@andry.com",
                location = "portugal",
                imageUrl = ""
            )
        )
    }
}