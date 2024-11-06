package com.example.repositories.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.commondesign.component.CustomCircularNetworkImageLoader
import com.example.commondesign.theme.GithubRepositoryFinderTheme
import com.example.domain.model.GithubRepositoryData

@Composable
fun GithubRepositoryItemCard(modifier: Modifier = Modifier, repositoryData: GithubRepositoryData) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(2f)) {
                    CustomCircularNetworkImageLoader(
                        imageUrl = repositoryData.userImageUrl,
                        modifier = Modifier.size(20.dp),
                        contentDescription = "${repositoryData.fullName} Picture",
                    )

                    Spacer(modifier = Modifier.width(6.dp))


                    val formattedName = buildAnnotatedString {
                        val names = repositoryData.fullName.split("/")
                        if (names.size > 1) {

                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.W400,
                                    fontSize = 12.sp,
                                    color = Color(0xFF62006A)
                                )
                            ) {
                                append("${names[0]}/")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.W600,
                                    color = Color.Black,
                                    fontSize = 12.sp
                                )
                            ) {
                                append(names[1])
                            }

                        } else {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.W400,
                                    fontSize = 12.sp,
                                    color = Color(0xFF62006A)
                                )
                            ) {
                                append(repositoryData.fullName)
                            }
                        }
                    }
                    Text(formattedName)

                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {

                    Image(
                        painter = painterResource(com.example.commondesign.R.drawable.ic_star),
                        contentDescription = "Watcher Count Image",
                        modifier = Modifier.size(12.dp)
                    )

                    Spacer(modifier = Modifier.width(3.dp))

                    Text(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.Black,
                        text = repositoryData.starGazersCount.toString()
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    if (repositoryData.language.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF51FD00))
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.W400,
                            color = Color.Black,
                            text = repositoryData.language,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                color = Color.Black,
                text = repositoryData.repositoryDescription
            )

            Spacer(modifier = Modifier.height(16.dp))


            LazyRow {
                items(repositoryData.topics.size) { index ->
                    TopicsTagItem(
                        modifier = Modifier.padding(6.dp), topic = repositoryData.topics[index]
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun GithubRepositoryItemCardPreview() {
    GithubRepositoryFinderTheme {
        GithubRepositoryItemCard(
            modifier = Modifier.padding(10.dp), repositoryData = GithubRepositoryData(
                repositoryId = 100,
                fullName = "ibrahim/google-maps-locate-search-address",
                githubUrl = "",
                topics = listOf(
                    "Design system",
                    "Component- misc",
                    "Status- New",
                    "Html",
                    "Css",
                    "JS",
                    "Html",
                    "Css",
                    "JS"
                ),
                language = "Python",
                repositoryDescription = "These are random words that will be replaced in due time. Config files for my github profile",
                starGazersCount = 10,
                userUrl = "",
                userImageUrl = ""
            )
        )
    }
}