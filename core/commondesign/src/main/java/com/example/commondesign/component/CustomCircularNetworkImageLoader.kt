package com.example.commondesign.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.commondesign.R
import com.example.commondesign.theme.GithubRepositoryFinderTheme

@Composable
fun CustomCircularNetworkImageLoader(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String,
    placeholder: Painter? = null,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(imageUrl).build(),
        placeholder = placeholder ?: painterResource(
            R.drawable.ic_default_network_image
        ),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(CircleShape)
    )
}


@Preview(showBackground = true)
@Composable
private fun CustomCircularNetworkImageLoaderPreview() {
    GithubRepositoryFinderTheme {
        CustomCircularNetworkImageLoader(
            modifier = Modifier.size(200.dp),
            imageUrl = "",
            contentDescription = "",
            placeholder = painterResource(
                R.drawable.ic_default_network_image
            )
        )
    }
}