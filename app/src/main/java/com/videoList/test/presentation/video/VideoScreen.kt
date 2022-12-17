package com.videoList.test.presentation.video

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.videoList.test.presentation.components.*
import com.videoList.test.presentation.ui.AppTheme
import com.videoList.test.domain.model.LocalCategory
import com.videoList.test.domain.model.ItemVideo
import androidx.hilt.navigation.compose.hiltViewModel
import com.videoList.test.core.util.TestTags

@Composable
fun VideoScreen(
    onNavigate: (ItemVideo) -> Unit,
    videoViewModel: VideoViewModel = hiltViewModel()
) {
    AppTheme {
        Column {
            Spacer(modifier = Modifier.height(24.dp))
            SetUpCategory(
                categories = videoViewModel.localCategoryState.value.data,
                onSelectedCategoryChanged = {
                    videoViewModel.getVideos(it)
                },
                selectedLocalCategory = videoViewModel.selectedLocalCategory.value,
                loading = videoViewModel.localCategoryState.value.isLoading

            )
            SetUpVideos(
                videos = videoViewModel.videoState.value.data,
                onNavigate = onNavigate,
                loading = videoViewModel.localCategoryState.value.isLoading
            )
        }
    }
}


@Composable
fun SetUpCategory(
    categories: List<LocalCategory>,
    onSelectedCategoryChanged: (LocalCategory) -> Unit,
    selectedLocalCategory: LocalCategory?,
    loading: Boolean
) {
    CategoryList(
        categories = categories,
        onSelectedCategoryChanged = onSelectedCategoryChanged,
        selectedLocalCategory = selectedLocalCategory,
        loading = loading,
        testTag = TestTags.CATEGORY_List
    )
}

@Composable
fun SetUpVideos(
    videos: List<ItemVideo>,
    onNavigate: (ItemVideo) -> Unit,
    loading: Boolean
) {
    VideoList(
        loading = loading,
        videos = videos,
        onNavigateToRecipeDetailScreen = {
            onNavigate(it)
        },
        testTag = TestTags.VIDEO_List,
        testTagItem = TestTags.VIDEO_ITEM
    )
}

