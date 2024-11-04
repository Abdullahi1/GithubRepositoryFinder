package com.example.commondesign.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.commondesign.R
import com.example.commondesign.theme.GithubRepositoryFinderTheme

@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    onSearchClicked: (TextFieldValue) -> Unit,
) {
    var textState by remember { mutableStateOf(TextFieldValue()) }
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                color = Color(0xFF292D32), width = 0.5.dp, shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 4.dp, vertical = 8.dp),
    ) {
        Spacer(modifier = Modifier.width(12.dp))
        Image(painter = painterResource(R.drawable.ic_search), contentDescription = "Search Icon")
        Spacer(modifier = Modifier.width(12.dp))
        BasicTextField(
            modifier = Modifier.weight(2f),
            value = textState,
            onValueChange = {
                textState = it
            },
        )

        ElevatedButton(
            onClick = {
                onSearchClicked(textState)
            },
            colors = ButtonDefaults.elevatedButtonColors(containerColor = Color(0xFF000000)),
            shape = RoundedCornerShape(3.dp),
            content = {
                Text(
                    "Search",
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                )
            },
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun CustomSearchBarPreview() {
    GithubRepositoryFinderTheme {
        Column(modifier = Modifier.fillMaxSize()) {

            CustomSearchBar(modifier = Modifier.padding(16.dp), onSearchClicked = {})
        }
    }
}