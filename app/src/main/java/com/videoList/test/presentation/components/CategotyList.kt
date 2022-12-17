package com.videoList.test.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.videoList.test.domain.model.LocalCategory

@Composable
fun CategoryList(
    categories: List<LocalCategory>,
    onSelectedCategoryChanged: (LocalCategory) -> Unit,
    selectedLocalCategory: LocalCategory?,
    loading: Boolean,
    testTag: String
) {
    val scrollState = rememberLazyListState()
    if (loading && categories.isEmpty()) {
        HorizontalDottedProgressBar()
        LoadingMovieListShimmer(imageHeight = 250.dp)
    } else if (categories.isEmpty()) {
        NothingHere()
    } else {
        LazyRow(
            modifier = Modifier
                .padding(start = 8.dp, bottom = 8.dp)
                .testTag(testTag),
            state = scrollState,
            reverseLayout = true
        ) {
            itemsIndexed(items = categories) { _, category ->
                MovieCategory(
                    localCategory = category,
                    isSelected = selectedLocalCategory == category,
                    onSelectedCategoryChanged = {
                        onSelectedCategoryChanged(it)

                    }
                )
            }
        }
    }
}