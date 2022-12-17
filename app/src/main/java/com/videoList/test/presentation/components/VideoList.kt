package com.videoList.test.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.videoList.test.domain.model.ItemVideo

@Composable
fun VideoList(
    loading: Boolean,
    videos: List<ItemVideo>,
    onNavigateToRecipeDetailScreen: (ItemVideo) -> Unit,
    testTag: String,
    testTagItem: String
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        val scrollState = rememberLazyListState()

        if (loading && videos.isEmpty()) {
            HorizontalDottedProgressBar()
            LoadingMovieListShimmer(imageHeight = 250.dp)
        } else if (videos.isEmpty()) {
            NothingHere()
        } else {
            LazyColumn(
                modifier = Modifier
                    .testTag(testTag),
                state = scrollState
            ) {
                itemsIndexed(
                    items = videos
                ) { _, video ->
                    MovieCard(
                        video = video,
                        onClick = {
                            onNavigateToRecipeDetailScreen(video)
                        },
                        testTagItem
                    )
                }
            }
        }
    }
}







