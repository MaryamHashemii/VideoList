package com.videoList.test.presentation.video_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.videoList.test.core.util.TestTags.VIDEO_DETAIL
import com.videoList.test.presentation.ui.Grey1
import com.videoList.test.presentation.video.VideoViewModel


@Composable
fun VideoDetailScreen(
    videoViewModel: VideoViewModel = hiltViewModel()
) {
    CreateView(
        imageUrl = videoViewModel.videoDetailState.value.images,
        title = videoViewModel.videoDetailState.value.name,
        countOfView = videoViewModel.videoDetailState.value.visit_cnt.toString()
    )
}


@Composable
private fun CreateView(
    imageUrl: String,
    title: String,
    countOfView: String
) {
    Column(
        modifier = Modifier
            .background(Grey1)
            .fillMaxHeight()
            .testTag(VIDEO_DETAIL)
    ) {

        Image(
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = "",
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = title,
            modifier = Modifier
                .padding(all = 8.dp)
                .align(Alignment.End)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "تعداد بازدید: $countOfView",
            modifier = Modifier
                .padding(all = 8.dp)
                .align(Alignment.End)
        )
    }
}

