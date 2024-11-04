package com.example.commondesign.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.commondesign.theme.GithubRepositoryFinderTheme

@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(color = Color(0xFF292D32), width = 0.5.dp)
            .padding(8.dp),
    ) {
        TextField(
            modifier = Modifier.weight(2f),
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent

            )
        )

        ElevatedButton(
            onClick = {},
            colors = ButtonDefaults.elevatedButtonColors(containerColor = Color(0xFF000000)),
            shape = RoundedCornerShape(3.dp),
            content = {
                Text("Search", color = Color.White, modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp))
            })
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun CustomSearchBarPreview() {
    GithubRepositoryFinderTheme {
        Column(modifier = Modifier.fillMaxSize()) {

            CustomSearchBar(modifier = Modifier.padding(16.dp),
                value = TextFieldValue(text = ""),
                onValueChange = {})
        }
    }
}