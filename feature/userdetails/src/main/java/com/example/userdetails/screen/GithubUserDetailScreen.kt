package com.example.userdetails.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.commondesign.component.CustomCircularNetworkImageLoader
import com.example.commondesign.component.LoadingWheel
import com.example.commondesign.theme.GithubRepositoryFinderTheme
import com.example.userdetails.R
import com.example.userdetails.component.CenteredImage
import com.example.userdetails.component.GithubRepositoryItemCard
import com.example.userdetails.route.UserDetailScreenRoute


@Composable
fun GithubUserDetailScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    username: String,
    viewModel: GithubUserDetailViewModel = hiltViewModel(),
) {

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val latestLifeCycleEvent = remember { mutableStateOf(Lifecycle.Event.ON_ANY) }


    val uiState by viewModel.userDetailsState.collectAsStateWithLifecycle()


    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            latestLifeCycleEvent.value = event
        }

        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    if (latestLifeCycleEvent.value == Lifecycle.Event.ON_START) {
        LaunchedEffect(latestLifeCycleEvent) {
            viewModel.getUserProfile(username = username)
        }
    }

    GithubUserDetailScreenContent(
        modifier = modifier,
        onBackPressed = onBackPressed,
        uiState = uiState
    )
}

@Composable
fun GithubUserDetailScreenContent(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    uiState: UserDetailsUiState = UserDetailsUiState.Empty,
) {
    val density = LocalDensity.current


    LazyColumn(
        modifier = modifier
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .fillMaxSize(),
        verticalArrangement = if (uiState is UserDetailsUiState.Success) Arrangement.Top else Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (uiState) {
            UserDetailsUiState.Empty -> item { }
            is UserDetailsUiState.Error -> item { CenteredImage(text = uiState.message) }
            UserDetailsUiState.Loading -> item { LoadingWheel("") }
            is UserDetailsUiState.Success -> {
                item {
                    Column {
                        Spacer(modifier = Modifier.height(25.dp))
                        Box(modifier = Modifier.clickable { onBackPressed() }) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(R.drawable.ic_arrow_left),
                                    "Arrow Back Button"
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Users", fontSize = 12.sp, fontWeight = FontWeight.W600)
                            }
                        }
                        Spacer(modifier = Modifier.height(18.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            CustomCircularNetworkImageLoader(
                                imageUrl = uiState.user.imageUrl,
                                modifier = Modifier
                                    .size(45.dp)
                                    .padding(top = 3.dp),
                                contentDescription = " Picture",
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(
                                    text = uiState.user.fullName,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W600
                                )
                                Spacer(modifier = Modifier.height(3.dp))
                                Text(
                                    text = uiState.user.userName,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.W500
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = uiState.user.bio, fontSize = 12.sp, fontWeight = FontWeight.W500
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(R.drawable.ic_location),
                                contentDescription = "Location Icon"
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = uiState.user.location,
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
                                text = "",
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
                                Row(verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.onSizeChanged {
                                        lineSize = density.run {
                                            DpSize(
                                                width = it.width.toDp(), height = it.height.toDp()
                                            )
                                        }
                                    }) {
                                    Text(
                                        text = "Repositories",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.W500,
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))

                                    if (uiState.repositories != null) {
                                        Text(
                                            modifier = Modifier
                                                .background(
                                                    color = Color(0xFFF1F1F1),
                                                    shape = AbsoluteRoundedCornerShape(8.dp)
                                                )
                                                .padding(vertical = 1.dp, horizontal = 8.dp),
                                            text = if (uiState.repositories.isEmpty()) "0" else "${uiState.repositories.size}",
                                            fontSize = 8.sp,
                                            fontWeight = FontWeight.W600,
                                        )
                                    }

                                }
                                HorizontalDivider(
                                    color = Color.Black, modifier = Modifier.width(lineSize.width)
                                )
                            }
                        }
                        HorizontalDivider(thickness = 1.dp, color = Color(0xFFF1F1F1))
                    }
                }

                if (uiState.repositories.isNullOrEmpty()) {
                    item {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(80.dp))
                            CenteredImage(text = "We’ve searched the ends of the earth and we’ve not found this user, please try again")
                        }
                    }
                } else {
                    items(
                        uiState.repositories.size,
                        key = { uiState.repositories[it].repositoryId }) { index: Int ->
                        GithubRepositoryItemCard(
                            modifier = Modifier
                                .animateItem()
                                .padding(top = 4.dp),
                            repositoryData = uiState.repositories[index]
                        )
                    }
                }

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun GithubUserDetailScreenPreview() {
    GithubRepositoryFinderTheme {
        GithubUserDetailScreenContent(onBackPressed = {})
    }
}

fun NavGraphBuilder.userDetails(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
) {
    composable<UserDetailScreenRoute> {
        val argument = it.toRoute<UserDetailScreenRoute>()
        GithubUserDetailScreen(
            modifier = modifier, onBackPressed = onBackPressed, username = argument.userName
        )
    }
}