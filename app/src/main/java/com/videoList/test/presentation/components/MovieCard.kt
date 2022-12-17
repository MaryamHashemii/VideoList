package com.videoList.test.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.videoList.test.core.util.TestTags.VIDEO_ITEM
import com.videoList.test.domain.model.ItemVideo

@Composable
fun MovieCard(
    video: ItemVideo,
    onClick: () -> Unit,
    testTag:String
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
            )
            .fillMaxWidth()
            .testTag(testTag)
            .clickable(onClick = onClick),

        ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(model = video.images),
                contentDescription = "Video Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(225.dp),
                contentScale = ContentScale.Crop,
            )

            Text(
                text = video .name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
                    .align(Alignment.Start),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}


