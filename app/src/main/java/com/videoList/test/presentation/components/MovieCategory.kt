package com.videoList.test.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.videoList.test.core.util.TestTags.CATEGORY_ITEM
import com.videoList.test.domain.model.LocalCategory

@Composable
fun MovieCategory(
    localCategory: LocalCategory,
    isSelected: Boolean = false,
    onSelectedCategoryChanged: (LocalCategory) -> Unit,
) {
    Surface(
        modifier = Modifier.padding(end = 8.dp),
        shadowElevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) Color.LightGray else MaterialTheme.colorScheme.primary
    ) {
        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectedCategoryChanged(localCategory)
                }
            )
        ) {
            Text(
                modifier = Modifier.padding(8.dp).testTag(CATEGORY_ITEM),
                text = localCategory.name,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
            )
        }
    }
}










